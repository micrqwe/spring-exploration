package xyz.micrqwe.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.micrqwe.dao.CityMapper;
import xyz.micrqwe.model.User;

import java.util.HashSet;
import java.util.Set;

//@Service
public class CustomUserDetailsService implements UserDetailsService {
    //    @Autowired  //数据库服务类
    private CityMapper suserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SUser中的email作为用户名:
        User user = null;
//
//        if (user == null) {
//            throw new UsernameNotFoundException("UserName " + userName + " not found");
//        }
        System.out.println("1111111111111");

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        roleService.getRoles(login.getId()).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));

        return new org.springframework.security.core.userdetails.User(
                "test", "test",
                true,//是否可用
                true,//是否过期
                true,//证书不过期为true
                true,//账户未锁定为true
                authorities);
    }

}