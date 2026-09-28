package com.innodealing.onshore.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 练习项目
 */
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@EnableScheduling
public class MyPracticeApplication {

    /**
     * 主方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(MyPracticeApplication.class, args);
    }
}
