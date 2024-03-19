package cn.xstrive.miniprogram.service.openfeign;

import cn.xstrive.entity.Customer;
import cn.xstrive.entity.Flat;
import cn.xstrive.entity.Saler;
import cn.xstrive.entity.Shopcar;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-info-provider-xqj",fallback= FallbackFactory.Default.class)
public interface UserInfoProviderService {
    @GetMapping("/getsalerbyid/{id}")
    public Saler getsalerbyid(@PathVariable("id") String id);

    @GetMapping("/wxisexist")
    public Customer wxisexist(@RequestParam(value = "openid",required = false) String opneid);

    @PostMapping("/savainfo")
    public void saveinfo(@RequestBody Customer customer);

    @PostMapping("/updateaddrinfo")
    public void updateaddrinfo(@RequestBody Customer customer);

    @PostMapping("/getaddrinfo")
    public Customer getaddrinfo(@RequestBody Customer customer);
    @PostMapping
    public Long getmoneynum(@RequestBody String opneid);
    @PostMapping("/rechargemoney")
    public void rechargemoney(@RequestBody Customer customer);
    @PostMapping("/getuseroneinfo")
    public Customer getuseroneinfo(@RequestBody String openid);
}
