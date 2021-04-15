package xin.spgraceme.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 沈鹏
 * @date 2020/5/20 -21:12
 */
@Controller
public class PageController {


    /**
     * 转发到指定页面
     * @param pageName
     * @return
     */
    @RequestMapping("/page/{pagename}.html")
    public String show(@PathVariable("pagename") String pageName){
        System.out.println("进入测试user:"+pageName);
        return "page/"+pageName;
    }


    @RequestMapping("/page/table/{pagename}.html")
    public String showTable(@PathVariable("pagename") String pageName){
        System.out.println("进入:"+pageName);
        return "page/table/"+pageName;
    }


    @RequestMapping("/index")
    @RequiresAuthentication//需要认证才能进来
    public String main(){
        return "index";
    }
}
