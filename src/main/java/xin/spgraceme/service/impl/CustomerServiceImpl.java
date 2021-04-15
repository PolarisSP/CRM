package xin.spgraceme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.spgraceme.dao.CustomerMapper;
import xin.spgraceme.dao.IndustryMapper;
import xin.spgraceme.dao.SourceMapper;
import xin.spgraceme.dao.UserMapper;
import xin.spgraceme.entity.Customer;
import xin.spgraceme.entity.Industry;
import xin.spgraceme.entity.User;
import xin.spgraceme.service.CustomerService;
import xin.spgraceme.utils.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:26
 */
@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private IndustryMapper industryMapper;

    @Autowired
    private SourceMapper sourceMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean insertCustomer(Customer customer, HttpSession session) {
        customer.setCid(StringUtil.uuid());
        customer.setCreatedate(new Date());
        User user = (User) session.getAttribute("CUR_USER");
        customer.setUid(user.getUid());
        return customerMapper.insertSelective(customer) > 0 ;
    }

    @Override
    public List<Customer> selectAll() {
        List<Customer> customers = customerMapper.selectAll();
        for (Customer customer : customers) {
            customer.setUser(userMapper.selectByPrimaryKey(customer.getUid()));
            customer.setIndustry(industryMapper.selectByPrimaryKey(customer.getIid()));
            customer.setSource(sourceMapper.selectByPrimaryKey(customer.getSid()));

        }
        return customers;
    }

    @Override
    public Customer selectCustomerById(String cid) {

        return customerMapper.selectByPrimaryKey(cid);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerMapper.insertSelective(customer);
    }

    @Override
    public List<Customer> selectAllCustomer(Map map) {
        map.put("enterprise",map.get("enterprise").equals("none") ? null : "%"+map.get("enterprise")+"%");
        map.put("contacts",map.get("contacts").equals("none") ? null : "%"+map.get("contacts")+"%");
        map.put("telPhone",map.get("telPhone").equals("none") ? null : map.get("telPhone"));
        map.put("iid",map.get("iid").equals("none") ? null : map.get("iid"));
        map.put("sid",map.get("sid").equals("none") ? null : map.get("sid"));
        List<Customer> customers = customerMapper.selectAllCustomer(map);
        for(Customer customer : customers){
            customer.setSource(sourceMapper.selectByPrimaryKey(customer.getSid()));
            customer.setIndustry(industryMapper.selectByPrimaryKey(customer.getIid()));
            customer.setUser(userMapper.selectByPrimaryKey(customer.getUid()));
        }
        return customers;
    }
}
