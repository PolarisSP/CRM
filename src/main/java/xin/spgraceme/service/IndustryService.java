package xin.spgraceme.service;

import xin.spgraceme.entity.Industry;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:09
 */
public interface IndustryService {


    List<Industry> selectAll();

    Industry selectIndustryById(String iid);

    List<Map<String, String>> selectIndustryData();

}
