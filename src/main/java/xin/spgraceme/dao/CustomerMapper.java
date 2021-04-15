package xin.spgraceme.dao;

import xin.spgraceme.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<Customer> selectAll();

    List<Customer> selectAllCustomer(Map map);
}