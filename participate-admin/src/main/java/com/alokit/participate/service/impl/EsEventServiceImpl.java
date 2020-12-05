package com.alokit.participate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alokit.participate.model.Event;
import com.alokit.participate.repository.EsEventRepository;
import com.alokit.participate.service.EsEventService;
import com.alokit.participate.service.EventService;

@Service
public class EsEventServiceImpl implements EsEventService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EsEventServiceImpl.class);
	
	@Autowired
    private EventService eventService;
	@Autowired
	private EsEventRepository esEventRepository;
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Override
	public int importAll() {
//		List<Event> esEventList = eventService.findAll();
//		Iterable<Event> esEventIterable = esEventRepository.saveAll(esEventList);
//		Iterator<Event> iterator = esEventIterable.iterator();
		int result = 0;
//		while (iterator.hasNext()) {
//			result++;
//			iterator.next();
//		}
		return result;
	}

	@Override
	public void delete(String eventId) {
//		esEventRepository.deleteById(eventId);
	}

	@Override
	public Event create(String eventId) {
		Event esEvent = null;
//		Event event = eventRepository.findByEventId(eventId);
//		if (event != null) {
//			esEvent = esEventRepository.save(event);
//		}
		return esEvent;
	}

//	@Override
//	public void delete(List<Long> eventIds) {
//		if (!CollectionUtils.isEmpty(eventIds)) {
//			List<Event> esEventList = new ArrayList<>();
//			for (Long eventId : eventIds) {
//				Event esEvent = new Event();
//				esEvent.setId(eventId);
//				esEventList.add(esEvent);
//			}
//			esEventRepository.deleteAll(esEventList);
//		}
//	}

//	@Override
//	public Page<Event> search(String keyword, Integer pageNum, Integer pageSize) {
//		Pageable pageable = PageRequest.of(pageNum, pageSize);
//		return esEventRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
//	}

	@Override
	public Page<Event> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,
			Integer sort) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

		nativeSearchQueryBuilder.withPageable(pageable);
		if (brandId != null || productCategoryId != null) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			if (brandId != null) {
				boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
			}
			if (productCategoryId != null) {
				boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
			}
			nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
		}

		if (StringUtils.isEmpty(keyword)) {
			nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
		} else {
			List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("name", keyword), ScoreFunctionBuilders.weightFactorFunction(10)));
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("subTitle", keyword), ScoreFunctionBuilders.weightFactorFunction(5)));
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("keywords", keyword), ScoreFunctionBuilders.weightFactorFunction(2)));
			FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders
					.size()];
			filterFunctionBuilders.toArray(builders);
			FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
					.scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(2);
			nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
		}
		if (sort == 1) {
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
		} else if (sort == 2) {
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
		} else if (sort == 3) {
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
		} else if (sort == 4) {
			nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
		} else {
			nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
		}
		nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
		NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
		LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
		SearchHits<Event> searchHits = elasticsearchRestTemplate.search(searchQuery, Event.class);
		if (searchHits.getTotalHits() <= 0) {
			return new PageImpl<>(null, pageable, 0);
		}
		List<Event> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
		return new PageImpl<>(searchProductList, pageable, searchHits.getTotalHits());
	}

//	@Override
//	public Page<Event> recommend(String eventId, Integer pageNum, Integer pageSize) {
//		Pageable pageable = PageRequest.of(pageNum, pageSize);
//		List<Event> esEventList = eventRepository.getAllEventList(eventId);
//		if (esProductList.size() > 0) {
//			Event esProduct = esProductList.get(0);
//			String keyword = esProduct.getName();
//			Long brandId = esProduct.getBrandId();
//			Long productCategoryId = esProduct.getProductCategoryId();
//			// 根据商品标题、品牌、分类进行搜索
//			List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
//			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//					QueryBuilders.matchQuery("name", keyword), ScoreFunctionBuilders.weightFactorFunction(8)));
//			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//					QueryBuilders.matchQuery("subTitle", keyword), ScoreFunctionBuilders.weightFactorFunction(2)));
//			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//					QueryBuilders.matchQuery("keywords", keyword), ScoreFunctionBuilders.weightFactorFunction(2)));
//			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//					QueryBuilders.matchQuery("brandId", brandId), ScoreFunctionBuilders.weightFactorFunction(5)));
//			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//					QueryBuilders.matchQuery("productCategoryId", productCategoryId),
//					ScoreFunctionBuilders.weightFactorFunction(3)));
//			FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders
//					.size()];
//			filterFunctionBuilders.toArray(builders);
//			FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
//					.scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(2);
//			BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//			boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", id));
//			NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//			builder.withQuery(functionScoreQueryBuilder);
//			builder.withFilter(boolQueryBuilder);
//			builder.withPageable(pageable);
//			NativeSearchQuery searchQuery = builder.build();
//			LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
//			SearchHits<Event> searchHits = elasticsearchRestTemplate.search(searchQuery, Event.class);
//			if (searchHits.getTotalHits() <= 0) {
//				return new PageImpl<>(null, pageable, 0);
//			}
//			List<Event> searchProductList = searchHits.stream().map(SearchHit::getContent)
//					.collect(Collectors.toList());
//			return new PageImpl<>(searchProductList, pageable, searchHits.getTotalHits());
//		}
//		return new PageImpl<>(null);
//	}

	@Override
	public Page<Event> search(String keyword, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Event> recommend(Long id, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<String> eventIds) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public EsProductRelatedInfo searchRelatedInfo(String keyword) {
//		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//		// 搜索条件
//		if (StringUtils.isEmpty(keyword)) {
//			builder.withQuery(QueryBuilders.matchAllQuery());
//		} else {
//			builder.withQuery(QueryBuilders.multiMatchQuery(keyword, "name", "subTitle", "keywords"));
//		}
//		// 聚合搜索品牌名称
//		builder.addAggregation(AggregationBuilders.terms("brandNames").field("brandName"));
//		// 集合搜索分类名称
//		builder.addAggregation(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));
//		// 聚合搜索商品属性，去除type=1的属性
//		AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues", "attrValueList")
//				.subAggregation(AggregationBuilders
//						.filter("productAttrs", QueryBuilders.termQuery("attrValueList.type", 1))
//						.subAggregation(AggregationBuilders.terms("attrIds").field("attrValueList.productAttributeId")
//								.subAggregation(AggregationBuilders.terms("attrValues").field("attrValueList.value"))
//								.subAggregation(AggregationBuilders.terms("attrNames").field("attrValueList.name"))));
//		builder.addAggregation(aggregationBuilder);
//		NativeSearchQuery searchQuery = builder.build();
//		SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
//		return convertProductRelatedInfo(searchHits);
//	}

//	private EsProductRelatedInfo convertProductRelatedInfo(SearchHits<EsProduct> response) {
//		EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();
//		Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
//		// 设置品牌
//		Aggregation brandNames = aggregationMap.get("brandNames");
//		List<String> brandNameList = new ArrayList<>();
//		for (int i = 0; i < ((Terms) brandNames).getBuckets().size(); i++) {
//			brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
//		}
//		productRelatedInfo.setBrandNames(brandNameList);
//		// 设置分类
//		Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
//		List<String> productCategoryNameList = new ArrayList<>();
//		for (int i = 0; i < ((Terms) productCategoryNames).getBuckets().size(); i++) {
//			productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
//		}
//		productRelatedInfo.setProductCategoryNames(productCategoryNameList);
//		// 设置参数
//		Aggregation productAttrs = aggregationMap.get("allAttrValues");
//		List<? extends Terms.Bucket> attrIds = ((ParsedLongTerms) ((ParsedFilter) ((ParsedNested) productAttrs)
//				.getAggregations().get("productAttrs")).getAggregations().get("attrIds")).getBuckets();
//		List<EsProductRelatedInfo.ProductAttr> attrList = new ArrayList<>();
//		for (Terms.Bucket attrId : attrIds) {
//			EsProductRelatedInfo.ProductAttr attr = new EsProductRelatedInfo.ProductAttr();
//			attr.setAttrId((Long) attrId.getKey());
//			List<String> attrValueList = new ArrayList<>();
//			List<? extends Terms.Bucket> attrValues = ((ParsedStringTerms) attrId.getAggregations().get("attrValues"))
//					.getBuckets();
//			List<? extends Terms.Bucket> attrNames = ((ParsedStringTerms) attrId.getAggregations().get("attrNames"))
//					.getBuckets();
//			for (Terms.Bucket attrValue : attrValues) {
//				attrValueList.add(attrValue.getKeyAsString());
//			}
//			attr.setAttrValues(attrValueList);
//			if (!CollectionUtils.isEmpty(attrNames)) {
//				String attrName = attrNames.get(0).getKeyAsString();
//				attr.setAttrName(attrName);
//			}
//			attrList.add(attr);
//		}
//		productRelatedInfo.setProductAttrs(attrList);
//		return productRelatedInfo;
//	}
}
