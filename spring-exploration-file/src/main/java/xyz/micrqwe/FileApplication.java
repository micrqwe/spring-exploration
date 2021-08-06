package xyz.micrqwe;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class FileApplication {
    public static void main(String[] args) throws Exception {
//        Destroy method 'close' on bean with name 'nacosServiceRegistry' threw an exception: java.lang.NullPointerException
        SpringApplication.run(FileApplication.class, args);
    }
}