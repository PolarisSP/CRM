package xin.spgraceme.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Customer implements Serializable {
    @ExcelIgnore
    private String cid;

    @ExcelProperty("企业名")
    private String enterprise;
    @ExcelProperty("联系人")
    private String contacts;
    @ExcelProperty("电话")
    private String telphone;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("行业代码")
    private String iid;
    @ExcelProperty("来源代码")
    private String sid;
    @ExcelProperty("经手人代码")
    private String uid;
        @ExcelProperty("创建时间")
    private Date createdate;

    @ExcelIgnore
    private User user;
    @ExcelIgnore
    private Industry industry;
    @ExcelIgnore
    private Source source;


}