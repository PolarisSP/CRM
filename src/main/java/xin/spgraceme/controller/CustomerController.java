package xin.spgraceme.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xin.spgraceme.dto.Result;
import xin.spgraceme.entity.Customer;
import xin.spgraceme.listener.CustomerReadListener;
import xin.spgraceme.service.CustomerService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:22
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private CustomerReadListener customerReadListener;

    @RequestMapping("insertCustomer")
    @RequiresAuthentication
    @ResponseBody
    public Result insertCustomer(Customer customer, HttpSession session){
        Result result = new Result();
        Boolean isSuccess = customerService.insertCustomer(customer,session);
        if (isSuccess){
            result.setState(Result.OK);
            result.setMsg("添加成功");
        }else {
            result.setState(Result.ERROR);
            result.setMsg("添加失败");
        }
        return result;
    }


    @RequestMapping("upload")
    public void Excelupload(MultipartFile file) throws IOException {
        ExcelReaderBuilder workBook = EasyExcel.read(file.getInputStream(), Customer.class, customerReadListener);
        workBook.sheet().doRead();
    }



    @RequestMapping("download")
    public void Exceldownload(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");
        List<Customer> customers = customerService.selectAll();

        ExcelWriterBuilder workBook = EasyExcel.write(response.getOutputStream(), Customer.class);

        ExcelWriterSheetBuilder sheet = workBook.sheet("模板");

        sheet.doWrite(customers);

//        ServletOutputStream outputStream = response.getOutputStream();
//        ExcelWriterBuilder workBook = EasyExcel.write(outputStream, Customer.class);
//        ExcelWriterSheetBuilder sheet= workBook.sheet();
//        sheet.doWrite(customers);
//        EasyExcel.write(response.getOutputStream(), Customer.class).sheet("模板").doWrite(customers);

    }

    @RequestMapping("selectCustomer")
    @ResponseBody
    @RequiresAuthentication
    public Result selectCustomer(@RequestParam(value="page",defaultValue = "1",required = false) int pageNum,
                                 @RequestParam(value="limit",defaultValue = "5",required = false) int pageSize,
                                 @RequestParam(value="enterprise",defaultValue = "none",required = false) String enterprise,
                                 @RequestParam(value="contacts",defaultValue = "none",required = false) String contacts,
                                 @RequestParam(value="telPhone",defaultValue = "none",required = false) String telPhone,
                                 @RequestParam(value="iid",defaultValue = "none",required = false) String iid,
                                 @RequestParam(value="sid",defaultValue = "none",required = false) String sid
    ){

        PageHelper.startPage(pageNum,pageSize);
        Map<String,String> map = new HashMap<>();
        map.put("enterprise",enterprise);
        map.put("contacts",contacts);
        map.put("telPhone",telPhone);
        map.put("iid",iid);
        map.put("sid",sid);
        System.out.println(map);
        List<Customer> customers = customerService.selectAllCustomer(map);
        Result<PageInfo<Customer>> result = new Result<>();
        result.setState(0);
        result.setMsg("查询成功");
        result.setData(new PageInfo<>(customers));
        return result;
    }
}
