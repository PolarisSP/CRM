package xin.spgraceme.dao;

import xin.spgraceme.entity.Industry;

import java.util.List;
import java.util.Map;

public interface IndustryMapper {
    int deleteByPrimaryKey(String iid);

    int insert(Industry record);

    int insertSelective(Industry record);

    Industry selectByPrimaryKey(String iid);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);

    List<Industry> selectAll();

    List<Map<String, String>> selectIndustryData();

}