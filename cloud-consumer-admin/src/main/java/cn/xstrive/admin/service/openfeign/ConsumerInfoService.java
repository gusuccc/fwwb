package cn.xstrive.admin.service.openfeign;

import cn.xstrive.entity.ComplaintEntity;
import cn.xstrive.entity.Customer;
import cn.xstrive.entity.Saler;
import cn.xstrive.entity.SysNotice;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-info-provider-xqj",fallback = FallbackFactory.Default.class)    //只能注册一个，否则要在配置文件里打开配置
public interface ConsumerInfoService {
    //=========================以下是consumer部分
    @GetMapping("/customer/list")
    public List<Customer> list();
    @GetMapping("/consumer/move/{id}")
    public boolean moveconsumer(@PathVariable("id") Long id);
    @PostMapping("/upconsumer")
    public void updatebyid(@RequestBody Customer customer);
    @PostMapping("/addconsumer")
    public void addconsumer(@RequestBody Customer customer);

    //=========================以下是saler的部分
    @GetMapping("/saler/list")
    public List<Saler> salerList();
    @PostMapping("/addsaler")
    public void addsaler(@RequestBody Saler saler);
    @PostMapping("/upsaler")
    public void upsalerbyid(@RequestBody Saler saler);

}
