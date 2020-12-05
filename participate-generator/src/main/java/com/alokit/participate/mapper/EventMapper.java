package com.alokit.participate.mapper;

import com.alokit.participate.model.Event;
import com.alokit.participate.model.EventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventMapper {
    long countByExample(EventExample example);

    int deleteByExample(EventExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Event record);

    int insertSelective(Event record);

    List<Event> selectByExampleWithBLOBs(EventExample example);

    List<Event> selectByExample(EventExample example);

    Event selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Event record, @Param("example") EventExample example);

    int updateByExampleWithBLOBs(@Param("record") Event record, @Param("example") EventExample example);

    int updateByExample(@Param("record") Event record, @Param("example") EventExample example);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKeyWithBLOBs(Event record);

    int updateByPrimaryKey(Event record);
}