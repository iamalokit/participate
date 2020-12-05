package com.alokit.participate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.alokit.participate.model.Event;


public interface EsEventRepository extends ElasticsearchRepository<Event, Long> {

//	Page<Event> findByNameOrSubTitleOrKeywords(String name, String[] keywords, Pageable page);

}
