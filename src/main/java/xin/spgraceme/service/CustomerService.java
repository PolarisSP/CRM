package xin.spgraceme.service;

import xin.spgraceme.entity.Customer;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author 沈鹏
 * @date 2020/5/25 -19:25
 */
public interface CustomerService {

    Boolean insertCustomer(Customer customer, HttpSession session);

    List<Customer> selectAll();

    Customer selectCustomerById(String cid);

    List<Customer> selectAllCustomer(Map<String, String> map);

    void insertCustomer(Customer customer);
}
