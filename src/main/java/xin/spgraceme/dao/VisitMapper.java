package xin.spgraceme.dao;

import xin.spgraceme.entity.Visit;

import java.util.List;
import java.util.Map;

public interface VisitMapper {
    int deleteByPrimaryKey(String vid);

    int insert(Visit record);

    int insertSelective(Visit record);

    Visit selectByPrimaryKey(String vid);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);

    List<Visit> selectAll(Map<String, String> map);

}