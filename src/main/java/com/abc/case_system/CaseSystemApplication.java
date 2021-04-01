package com.abc.case_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.abc.case_system.dao")
@SpringBootApplication
public class CaseSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaseSystemApplication.class, args);
    }

}
