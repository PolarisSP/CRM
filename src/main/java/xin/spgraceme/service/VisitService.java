package xin.spgraceme.service;

import xin.spgraceme.entity.Visit;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/6/1 -15:05
 */
public interface VisitService {
    List<Visit> selectAll(Map<String, String> map);

    boolean insertVisit(Visit visit);
}
