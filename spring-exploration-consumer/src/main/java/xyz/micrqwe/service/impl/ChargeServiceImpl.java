package xyz.micrqwe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import xyz.micrqwe.dao.ChargeMapper;
import xyz.micrqwe.dao.CityMapper;
import xyz.micrqwe.service.HelloService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by le on 2017/5/5.
 */
@Service
public class ChargeServiceImpl {
    @Autowired
    private ChargeMapper chargeMapper;


    public long schCount() {
        Long m = chargeMapper.queryMaxSchId();
        if (m == null) {
            m = 0L;
        }
        m++;
        chargeMapper.insrtSwx(m, 1);
        return m;
    }


}
