package com.crady.service;

import com.crady.pojo.User;

/**
 * @author :Crady
 * date :2019/10/22 15:42
 * desc :
 **/
public interface UserService {

    int insertUser(User user);

    int updateUser(User user);

    int buy(Integer id,Integer Money,Integer type,boolean exFlag);

    User queryUserById(Integer id);
}
