package org.javaboy.egosearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.javaboy.ego.manager.mapper")
public class EgoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgoSearchApplication.class, args);
    }

}
