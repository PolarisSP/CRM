package xin.spgraceme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.spgraceme.dto.Result;
import xin.spgraceme.entity.Source;
import xin.spgraceme.service.SourceService;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:14
 */
@Controller
@RequestMapping("source")
public class SourceController {

    @Autowired
    private SourceService sourceService;


    @RequestMapping("selectSource")
    @ResponseBody
    public Result<List<Source>> selectSource(){
        Result result = new Result();
        List<Source> sourceList = sourceService.selectAll();
        if (sourceList!=null&&sourceList.size()!=0){
            result.setState(Result.OK);
            result.setMsg("查询成功");
            result.setData(sourceList);
        }else {
            result.setState(Result.ERROR);
            result.setMsg("查询失败");
        }
        return  result;
    }


    @RequestMapping("selectSourceData")
    @ResponseBody
    public Result<List<Map<String,String>>> selectSourceData(){
        Result<List<Map<String,String>>> result = new Result<>();
        List<Map<String,String>> sourceList = sourceService.selectSourceData();
        if (sourceList!=null&&sourceList.size()!=0){
            result.setState(Result.OK);
            result.setMsg("查询成功");
            result.setData(sourceList);
        }else {
            result.setState(Result.ERROR);
            result.setMsg("查询失败");
        }
        return  result;
    }


}
