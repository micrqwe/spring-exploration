package xyz.micrqwe.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.micrqwe.GatewayApplication;

/**
 * Created by shaowenxing on 2018/4/20.
 */
@SpringBootTest(classes = GatewayApplication.class)
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
    @Test
    public void concatMapAndNExt() throws InterruptedException {
        Flux.fromArray("abcdefghi".split(""))
                .concatMap(this::convertByCondition)
                .next()
                .subscribe(c -> System.out.println("\nresult = " + c));
        Thread.sleep(2000);

    }

    private Mono<Character> convertByCondition(String s) {
        System.out.print("->" + s);
        char c = s.toCharArray()[0];
        if (c < 'e') {
            return Mono.empty();
        }
        return Mono.just(c);
    }
}
