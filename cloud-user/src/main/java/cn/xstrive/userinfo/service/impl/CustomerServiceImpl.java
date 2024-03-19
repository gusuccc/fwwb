package cn.xstrive.userinfo.service.impl;

import cn.xstrive.entity.Customer;
import cn.xstrive.userinfo.mapper.CustomerMapper;
import cn.xstrive.userinfo.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 顾客信息表 服务实现类
 * </p>
 *
 * @author xqj
 * @since 2021-02-17
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
