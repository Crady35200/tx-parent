package com.crady.controller;

import com.crady.pojo.User;
import com.crady.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author :Crady
 * date :2019/10/22 15:52
 * desc :
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("buy")
    public String buy(@RequestParam(value = "id",required = true) Integer id,
                      @RequestParam(value = "amount",required = true) Integer amount,
                      @RequestParam(value = "type",required = true) Integer type,
                      @RequestParam(value = "exFlag",required = true) boolean exFlag){
        int n = userService.buy(id,amount,type,exFlag);
        return n > 0 ? "处理成功" : "处理失败";
    }

    @RequestMapping("addUser/{name}")
    public String addUser(@PathVariable(value = "name",required = true) String name){
        User user = new User();
        user.setName(name);
        Date date = new Date();
        user.setCrtTime(date);
        user.setUpdTime(date);
        int n = userService.insertUser(user);
        return n > 0 ? "add success " + user : "add fauiler";
    }
}
