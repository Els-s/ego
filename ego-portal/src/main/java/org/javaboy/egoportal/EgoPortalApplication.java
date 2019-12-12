package org.javaboy.egoportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EgoPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgoPortalApplication.class, args);
    }

}
