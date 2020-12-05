package com.alokit.participate.mapper;

import com.alokit.participate.model.EventCategory;
import com.alokit.participate.model.EventCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventCategoryMapper {
    long countByExample(EventCategoryExample example);

    int deleteByExample(EventCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EventCategory record);

    int insertSelective(EventCategory record);

    List<EventCategory> selectByExampleWithBLOBs(EventCategoryExample example);

    List<EventCategory> selectByExample(EventCategoryExample example);

    EventCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EventCategory record, @Param("example") EventCategoryExample example);

    int updateByExampleWithBLOBs(@Param("record") EventCategory record, @Param("example") EventCategoryExample example);

    int updateByExample(@Param("record") EventCategory record, @Param("example") EventCategoryExample example);

    int updateByPrimaryKeySelective(EventCategory record);

    int updateByPrimaryKeyWithBLOBs(EventCategory record);

    int updateByPrimaryKey(EventCategory record);
}