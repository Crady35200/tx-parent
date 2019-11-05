package com.crady.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.crady.mapper.UserMapper;
import com.crady.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author :Crady
 * date :2019/10/22 15:43
 * desc :
 **/
@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderService orderService;

    @Transactional
    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Transactional
    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @LcnTransaction
    @Override
    public int buy(Integer id, Integer money, Integer type,boolean exFlag) {
        User u = queryUserById(id);
        if(type == 0 && money > u.getBalance()){
            throw new IllegalArgumentException("余额不足，当前余额:" + u.getBalance());
        }
        User user = new User();
        user.setId(id);
        user.setBalance(type == 0 ? u.getBalance() - money : u.getBalance() + money);
        user.setUpdTime(new Date());
        int n = userMapper.updateByPrimaryKeySelective(user);

        String result = orderService.addMoneyDetail(id,money,type);
        boolean flag = "ok".equals(result) ? true : false;
        if(exFlag || !flag){
            throw new IllegalArgumentException("模拟抛错，让事物回滚");
        }

        return n > 0 && flag ? 1 : 0;

    }

    @Override
    public User queryUserById(Integer id) {
        if(id == null){
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return userMapper.selectByPrimaryKey(id);
    }
}
