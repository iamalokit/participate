package com.alokit.participate.mapper;

import com.alokit.participate.model.EventLocation;
import com.alokit.participate.model.EventLocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventLocationMapper {
    long countByExample(EventLocationExample example);

    int deleteByExample(EventLocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EventLocation record);

    int insertSelective(EventLocation record);

    List<EventLocation> selectByExample(EventLocationExample example);

    EventLocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EventLocation record, @Param("example") EventLocationExample example);

    int updateByExample(@Param("record") EventLocation record, @Param("example") EventLocationExample example);

    int updateByPrimaryKeySelective(EventLocation record);

    int updateByPrimaryKey(EventLocation record);
}