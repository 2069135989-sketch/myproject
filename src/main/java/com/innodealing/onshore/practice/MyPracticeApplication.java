package com.innodealing.onshore.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 练习项目
 */
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@EnableScheduling
@MapperScan("com.innodealing.onshore.practice.mapper")
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
