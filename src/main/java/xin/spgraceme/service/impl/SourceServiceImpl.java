package xin.spgraceme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.spgraceme.dao.SourceMapper;
import xin.spgraceme.entity.Source;
import xin.spgraceme.service.SourceService;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:16
 */
@Service
public class SourceServiceImpl implements SourceService {


    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public List<Source> selectAll() {
        return sourceMapper.selectAll();
    }

    @Override
    public Source selectSourceBySic(String sid) {
        return sourceMapper.selectByPrimaryKey(sid);
    }

    @Override
    public List<Map<String, String>> selectSourceData() {

        return sourceMapper.selectSourceData();
    }


}
