package xyz.micrqwe.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes=Application.class)
public class RandomPortExampleTests {

    @Test
    public void objectMapper() throws Exception {
        Map map = new HashMap();
        map.put("123", "1243");
        ObjectMapper o = new ObjectMapper();
        System.out.println(o.writeValueAsString(map));
    }

    @Test
    public void testExample() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {
    }
}