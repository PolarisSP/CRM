package xin.spgraceme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import xin.spgraceme.dao.IndustryMapper;
import xin.spgraceme.entity.Industry;
import xin.spgraceme.service.IndustryService;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:09
 */
@Service
public class IndustryServiceImpl implements IndustryService {


    @Autowired
    private  IndustryMapper  industryMapper;



    @Override
    public List<Industry> selectAll() {
        return industryMapper.selectAll();
    }

    @Override
    public Industry selectIndustryById(String iid) {
        return industryMapper.selectByPrimaryKey(iid);
    }

    @Override
    public List<Map<String, String>> selectIndustryData() {

        return industryMapper.selectIndustryData();
    }
}
