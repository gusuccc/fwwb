package cn.xstrive.admin.service;

import cn.xstrive.admin.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author xqj
 * @since 2021-02-16
 */
@Component
public interface AdminService extends IService<Admin>, UserDetailsService {

}
