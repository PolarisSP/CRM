package xin.spgraceme.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.spgraceme.dto.Result;
import xin.spgraceme.entity.Visit;
import xin.spgraceme.service.VisitService;
import xin.spgraceme.utils.DateConvert;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/6/1 -15:02
 */
@Controller
@RequestMapping("visit")
public class VisitController {

    @Autowired
    private VisitService visitService;


    @RequestMapping("selectVisit")
    @ResponseBody
    public Result<PageInfo<Visit>> selectVisit(@RequestParam(value="page",defaultValue = "1",required = false) int pageNum,
                                               @RequestParam(value="limit",defaultValue = "5",required = false) int pageSize,
                                               @RequestParam(value="fullname",defaultValue = "none",required = false) String fullname,
                                               @RequestParam(value="contacts",defaultValue = "none",required = false) String contacts
    ){
        Map<String,String> map = new HashMap<>();
        map.put("fullname",fullname);
        map.put("contacts",contacts);
        PageHelper.startPage(pageNum,pageSize);
        List<Visit> visits = visitService.selectAll(map);
        Result<PageInfo<Visit>> result = new Result<>();
        result.setState(0);
        result.setMsg("查询成功");
        result.setData(new PageInfo<Visit>(visits));
        return result;
    }


    @RequestMapping("addVisit")
    @ResponseBody
    public Result addVisit(Visit visit, HttpServletRequest request){
        String visitTime = request.getParameter("visitTime");
        String endTime = request.getParameter("endTime");
        DateConvert dateConvert = new DateConvert();
        Date visittime1 = dateConvert.convert(visitTime);
        Date endtime1 = dateConvert.convert(endTime);
        visit.setVisittime(visittime1);
        visit.setEndtime(endtime1);
        Result result = new Result();
        if(visitService.insertVisit(visit)){
            result.setMsg("添加拜访记录成功");
            result.setState(0);
        }else{
            result.setMsg("添加拜访记录失败");
            result.setState(1);
        }
        return result;
    }

}
