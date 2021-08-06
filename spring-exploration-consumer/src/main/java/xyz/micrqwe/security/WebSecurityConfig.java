package xyz.micrqwe.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.micrqwe.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers("/home/**", "/miao").permitAll()
//                .mvcMatchers("/mypath")
//                .hasAnyRole("SUPERUSER")
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
//                .loginPage("/login")
                .loginProcessingUrl("/login/londing")
//                .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello"
                .successHandler(new QhAuthenticationSuccessHandler())
                .failureHandler(new QhAuthenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
//                .logoutSuccessUrl("/loginerror")//退出登录后的默认url是"/home"
                // 自定义登出之后的地址
//                .logoutSuccessHandler(new LogoutHandlerCustom())
                .permitAll();
        http.csrf().disable();
        // 自定义未登录时候的操作。可以了防止进行302跳转
        http.exceptionHandling().authenticationEntryPoint(new NoLoginUrl());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
//        auth
//                .inMemoryAuthentication()
//                .withUser("test").password("test").roles("USER");

    }

    /**
     * 设置用户密码的加密方式为MD5加密
     *
     * @return
     */
//    public AuthenticationSuccessHandler successHandler() {
//        return new QhAuthenticationSuccessHandler();
//
//    }

    /**
     * 设置用户密码的加密方式为MD5加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);

    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     *
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }
}