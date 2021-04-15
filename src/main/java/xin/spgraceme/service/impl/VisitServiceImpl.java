package xin.spgraceme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.spgraceme.dao.CustomerMapper;
import xin.spgraceme.dao.UserMapper;
import xin.spgraceme.dao.VisitMapper;
import xin.spgraceme.entity.Visit;
import xin.spgraceme.service.VisitService;
import xin.spgraceme.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/6/1 -15:06
 */
@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitMapper visitMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Visit> selectAll(Map<String, String> map) {
        map.put("fullname",map.get("fullname").equals("none") ? null : "%"+map.get("fullname")+"%");
        map.put("contacts",map.get("contacts").equals("none") ? null : "%"+map.get("contacts")+"%");
        List<Visit> visitList = visitMapper.selectAll(map);
        for(Visit visit : visitList){
            visit.setUser(userMapper.selectByPrimaryKey(visit.getUid()));
            visit.setCustomer(customerMapper.selectByPrimaryKey(visit.getCid()));
        }
        return visitList;
    }

    @Override
    public boolean insertVisit(Visit visit) {
        visit.setVid(StringUtil.uuid());
        return visitMapper.insertSelective(visit) > 0;
    }
}
