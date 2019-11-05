package com.crady.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author :Crady
 * date :2019/10/22 15:44
 * desc :
 **/
@FeignClient(name = "ms-order",path = "order")
public interface OrderService {
    @RequestMapping("addDetail")
    String addMoneyDetail(@RequestParam("userId") Integer userId,@RequestParam("money") Integer money,@RequestParam("type") Integer type);
}
