package xyz.micrqwe.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.micrqwe.ExplorationApplication;

/**
 * Created by shaowenxing on 2018/4/20.
 */
@SpringBootTest(classes = ExplorationApplication.class)
public class Common {
    public String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(o);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("错误");
        }
        return s;
    }
}
