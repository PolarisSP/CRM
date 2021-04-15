package xin.spgraceme.service;

import xin.spgraceme.entity.Source;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:16
 */
public interface SourceService {


    List<Source> selectAll();

    Source selectSourceBySic(String sid);

    List<Map<String, String>> selectSourceData();

}
