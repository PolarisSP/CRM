package xin.spgraceme.dao;

import org.apache.ibatis.annotations.Param;
import xin.spgraceme.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsernameAndPassword(User user);

    User selectByUsername(String username);

    int insertUserRole(@Param("urid") String urid,@Param("uid") String uid,@Param("rid") String rid);

    int deleteUserRole(@Param("uid") String uid,@Param("rid") String rid);

    List<User> selectAllSales();

}