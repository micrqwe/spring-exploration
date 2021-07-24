package xyz.micrqwe.config;

import org.springframework.stereotype.Service;

//@Configuration
//@Service
public class XmlBaiduConfig {
    public String getBaiFaCoe() {
        return baiFaCoe;
    }

    public void setBaiFaCoe(String baiFaCoe) {
        this.baiFaCoe = baiFaCoe;
    }

    //    @Value("${coe.baiFaCoe:300}")
    private String baiFaCoe;

    public void init() {
        System.out.println("xml======================");
        System.out.println(baiFaCoe);
    }
}
