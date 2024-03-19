package cn.xstrive.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients        //激活feign的功能
@MapperScan("cn.xstrive.admin.mapper")
public class AdminConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(AdminConsumerMain.class,args);
    }
}
