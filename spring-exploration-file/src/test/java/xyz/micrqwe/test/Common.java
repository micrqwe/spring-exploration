package xyz.micrqwe.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.micrqwe.FileApplication;

/**
 * Created by shaowenxing on 2018/4/20.
 */
@SpringBootTest(classes = FileApplication.class)
public class Common {
    public String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            System.out.println("错误");
        }
        return s;
    }
}
