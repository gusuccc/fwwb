package cn.xstrive.wxinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.swing.*;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("cn.xstrive.wxinfo.mapper")
public class Wxinfoapplication {
    public static void main(String[] args) {
        SpringApplication.run(Wxinfoapplication.class,args);
    }
}
