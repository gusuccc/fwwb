package cn.xstrive.admin.service.impl;

import cn.xstrive.admin.domain.Admin;
import cn.xstrive.admin.mapper.AdminMapper;
import cn.xstrive.admin.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.zip.Adler32;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author xqj
 * @since 2021-02-16
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin adminlogin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("ausername", username));       //这里查询使用的是数据库字段名
        if (adminlogin == null)
            throw new UsernameNotFoundException("用户不存在！");
        List<GrantedAuthority> ga = AuthorityUtils.commaSeparatedStringToAuthorityList(adminlogin.getAroles());
        log.info("loadUserByUsername被调用了！！！");
        String apassword = adminlogin.getApassword();
        apassword = passwordEncoder.encode(apassword);
        return new User(adminlogin.getAusername(),apassword,true,true,true,true,ga);
    }
}
