package xin.spgraceme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.spgraceme.dto.Result;
import xin.spgraceme.entity.Industry;
import xin.spgraceme.service.IndustryService;

import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:07
 */
@Controller
@RequestMapping("industry")
public class IndustryController {

    @Autowired
    private IndustryService IndustryServiceImpl;

    @RequestMapping("selectIndustry")
    @ResponseBody
    public Result<List<Industry>>  selectIndustry(){
        Result result = new Result();
        List<Industry> industryList = IndustryServiceImpl.selectAll();
        if (industryList!=null){
            result.setState(Result.OK);
            result.setMsg("查询成功");
            result.setData(industryList);
        }else {
            result.setState(Result.ERROR);
            result.setMsg("查询失败");
        }
        return result;
    }



    @RequestMapping("selectIndustryData")
    @ResponseBody
    public Result<List<Map<String,String>>> selectIndustryData(){
        Result<List<Map<String,String>>> result = new Result<>();
        List<Map<String,String>> industryList = IndustryServiceImpl.selectIndustryData();
        if (industryList!=null&&industryList.size()!=0){
            result.setState(Result.OK);
            result.setMsg("查询成功");
            result.setData(industryList);
        }else {
            result.setState(Result.ERROR);
            result.setMsg("查询失败");
        }
        return  result;
    }


}
