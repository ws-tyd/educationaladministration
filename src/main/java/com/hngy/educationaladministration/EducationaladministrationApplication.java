package com.hngy.educationaladministration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hngy.educationaladministration.mapper")
@SpringBootApplication
public class EducationaladministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationaladministrationApplication.class, args);
    }

}
