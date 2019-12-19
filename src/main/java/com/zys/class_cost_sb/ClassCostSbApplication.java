package com.zys.class_cost_sb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.zys.class_cost_sb.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class ClassCostSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassCostSbApplication.class, args);
    }

}
