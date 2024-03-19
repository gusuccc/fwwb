package cn.xstrive.notcom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.xstrive.notcom.mapper")
public class NoticeComplaintMain {
    public static void main(String[] args) {
        SpringApplication.run(NoticeComplaintMain.class,args);
    }
}
