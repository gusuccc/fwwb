package cn.xstrive.admin.service.openfeign;

import cn.xstrive.entity.ComplaintEntity;
import cn.xstrive.entity.SysNotice;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="notice-complaint-provider",fallback = FallbackFactory.Default.class)
public interface NoticeComplainService {
    //=========================以下是SysNotice的部分
    @GetMapping("/sysNotice/list")
    public List<SysNotice> SysNoticelist();

    @GetMapping("/sysNotice/{id}")
    public void moveSysNotice(@PathVariable("id") Long id);

    @PostMapping("/upsysNotice")
    public void updateSysNoticebyid(@RequestBody SysNotice sysNotice);

    @PostMapping("/addsysNotice")
    public void addSysNotice(@RequestBody SysNotice sysNotice);
    //=========================以下是ComplaintEntity的部分
    @GetMapping("/ComplaintEntity/list")
    public List<ComplaintEntity> ComplaintEntitylist();

    @GetMapping("/ComplaintEntity/{id}")
    public void moveComplaintEntity(@PathVariable("id") Long id);

    @PostMapping("/upComplaintEntity")
    public void updateCEbyid(@RequestBody ComplaintEntity complaintEntity);

    @PostMapping("/addComplaintEntity")
    public void addComplaintEntity(@RequestBody ComplaintEntity complaintEntity);
}
