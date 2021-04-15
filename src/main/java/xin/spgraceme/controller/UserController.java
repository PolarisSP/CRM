package xin.spgraceme.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.spgraceme.dto.InitMenu;
import xin.spgraceme.dto.Result;
import xin.spgraceme.entity.Permission;
import xin.spgraceme.entity.User;
import xin.spgraceme.entity.Visit;
import xin.spgraceme.service.UserService;
import xin.spgraceme.utils.PermissionConvert;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @author 沈鹏
 * @date 2020/5/18 -16:06
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired(required=true)
    private Producer captchaProducer;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("reg")
    public String reg(){
        return "reg";
    }


    /**
     * 登录
     * @param username
     * @param password
     * @param captcha
     * @param session
     * @return
     */
    @RequestMapping(value = "loginDo",method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username, String password, String captcha, HttpSession session){
        Result result = new Result();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(code)){
            result.setMsg("验证码错误");
            result.setState(Result.ERROR);
            return result;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            result.setState(Result.ERROR);
            result.setMsg("账号或者密码错误");
            return result;
        }
        subject.isPermitted("customer:*:*");
        result.setState(Result.OK);
        result.setMsg("登录成功");
        return result;
    }


    @RequestMapping("getMenu")
    @RequiresUser
    @ResponseBody
    public InitMenu getMenu(){
        List<Permission> permissions = (List<Permission>) SecurityUtils.getSubject().getSession().getAttribute("permissions");
        return PermissionConvert.convert(permissions);
    }


    @RequestMapping("selectUser")
    @ResponseBody
    public Result<PageInfo<User>> selectUser(@RequestParam(value="page",defaultValue = "1",required = false) int pageNum, @RequestParam(value="limit",defaultValue = "5",required = false) int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.selectAllUser();
        Result<PageInfo<User>> result = new Result<>();
        result.setState(0);
        result.setMsg("查询成功");
        result.setData(new PageInfo<User>(users));
        return result;
    }



    @RequestMapping("selectSales")
    @ResponseBody
    public Result<PageInfo<User>> selectSales(@RequestParam(value="page",defaultValue = "1",required = false) int pageNum, @RequestParam(value="limit",defaultValue = "5",required = false) int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.selectAllSales();
        Result<PageInfo<User>> result = new Result<>();
        result.setState(0);
        result.setMsg("查询业务员成功");
        result.setData(new PageInfo<User>(users));
        System.out.println("selectSales:"+result);
        return result;
    }



    @RequestMapping("selectUserByUid")
    @ResponseBody
    public Result selectUserByUid(String uid){
        Result result = new Result();
        User user = userService.selectByUid(uid);
        if (user !=null){
            result.setMsg("查询成功");
            result.setState(Result.OK);
            result.setData(user);
        }else {
            result.setMsg("查询失败");
            result.setState(Result.ERROR);
        }

        return result;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "regDo",method = RequestMethod.POST)
    @ResponseBody
    public Result reg(User user){
        Result result = new Result();
        if (userService.insertUser(user)){
            result.setMsg("注册成功");
            result.setState(Result.OK);

        }else {
            result.setMsg("注册失败");
            result.setState(Result.ERROR);
        }

        return result;
    }


    @RequestMapping("updateDo")
    @ResponseBody
    @RequiresUser
    public Result updateDo(User user){
        Result result = new Result();
        if ("".equals(user.getPassword())){
            user.setPassword(null);
        }
        Boolean isSuccess = userService.updateByUid(user);
        if (isSuccess){
            result.setState(Result.OK);
            result.setMsg("修改成功");
        }else {
            result.setState(Result.ERROR);
            result.setMsg("修改失败");
        }
        return result;
    }


    @RequestMapping("updateUserRole")
    @RequiresUser
    @RequiresRoles("系统管理员")
    @ResponseBody
    public Result updateUserRole(String uid,String rid,Boolean checked){
        Result result = new Result();
        Boolean isSuccess = userService.updateUserRole(uid,rid,checked);
        result.setState(isSuccess?0:1);
        return result;
    }



    /**
     * 验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/val")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置浏览器不去缓存图片
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");
        //IE识别
        response.setDateHeader("Expries",0);

        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();

        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try
        {
            out.flush();
        }
        finally
        {
            out.close();
        }
    }
}
