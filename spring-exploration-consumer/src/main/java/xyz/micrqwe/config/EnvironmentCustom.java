package xyz.micrqwe.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * description TODO
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-08-25 15:45
 **/
public class EnvironmentCustom  implements
        ApplicationContextInitializer<ConfigurableApplicationContext> ,  EnvironmentPostProcessor, Ordered {
    static {
        System.out.println("哈哈哈我这里在初始化");
    }
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("1111111111111111111");
    }

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("222222222222222");
    }
}
