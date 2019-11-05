package com.crady.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.crady.mapper.MoneyDetailMapper;
import com.crady.pojo.MoneyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author :Crady
 * date :2019/10/22 15:27
 * desc :
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    MoneyDetailMapper moneyDetailMapper;

    @Transactional
    @LcnTransaction
    @Override
    public int addMondyDetail(MoneyDetail moneyDetail) {
        return moneyDetailMapper.insert(moneyDetail);
    }
}
