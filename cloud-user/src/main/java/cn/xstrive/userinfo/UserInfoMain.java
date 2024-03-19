package cn.xstrive.userinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.xstrive.userinfo.mapper")
public class UserInfoMain {
    public static void main(String[] args) {
        SpringApplication.run(UserInfoMain.class,args);
    }
}
