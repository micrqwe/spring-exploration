package xyz.micrqwe.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaowenxing on 2018/4/20.
 * 自定义sql获取
 */
@Service
public class SqlCustomerServiceImpl {
    //    @Autowired
//    private SqlCustomerMapper customerMapper;


    public Map querySql(String sql) {
        List<Map<String, Object>> list = null;
        try {
//            list = customerMapper.query(sql);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("当前sql数据错误");
        }
        if (list == null) {
            return null;
        }
        Map result = new HashMap();
        // 主键
        List<String> key = new ArrayList<String>();
        List<List<Object>> value = new ArrayList();
        int i = 1;
        for (Map<String, Object> tmp : list) {
            List<Object> tmpList = new ArrayList<Object>();
            // 第一次加入
            if (i == 1) {
                for (Map.Entry<String, Object> entry : tmp.entrySet()) {
                    key.add(entry.getKey());
                    tmpList.add(entry.getValue());
                }
            } else {
                for (String k : key) {
                    tmpList.add(tmp.get(k));
                }
            }
            if (i == 1) {
                result.put("key", key);
            }
            value.add(tmpList);
            i++;
        }
        result.put("value", value);
        return result;
    }
}
