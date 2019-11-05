package com.crady.controller;

import com.crady.pojo.MoneyDetail;
import com.crady.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author :Crady
 * date :2019/10/22 15:23
 * desc :
 **/
@RestController
@RequestMapping("order")
@Slf4j
public class MoneyDetailController {

    @Autowired
    OrderService moneyDetailService;

    @RequestMapping("addDetail")
    public String addMoneyDetail(Integer userId,Integer money,boolean type){
        if(userId == null){
            throw new IllegalArgumentException("用户ID不能为空");
        }
        if(money < 1){//由于是整数，所以<1即可
            throw new IllegalArgumentException("操作金额必须大于0");
        }
        MoneyDetail moneyDetail = new MoneyDetail();
        moneyDetail.setAmount(money);
        moneyDetail.setUserId(userId);
        moneyDetail.setType(type);
        moneyDetail.setCrtTime(new Date());
        int n = moneyDetailService.addMondyDetail(moneyDetail);
        if(n > 0){
            log.info("add success {}",moneyDetail);
            return "ok";
        }else{
            log.info("add fauiler {}",moneyDetail);
            return "error";
        }
    }
}
