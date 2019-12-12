package org.javaboy.ego.manager.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.javaboy.ego.manager")
@MapperScan(basePackages = "org.javaboy.ego.manager.mapper")
public class EgoManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgoManagerWebApplication.class, args);
    }

}
