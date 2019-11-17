package com.jcj.miniweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *自定义安全配置类，访问任何url之前进行验证
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
{
    @Bean
    UserDetailsService UDS(){return new MyUserDetailsService();}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        /**
         * 配置认证管理器AuthenticationManager
         */
        auth.userDetailsService(UDS()).passwordEncoder(new BCryptPasswordEncoder());
    }

    /*  重要说明
       首先要重点重点说明的是，Spring Security中完全没有角色一说，所有的权限比较都是根据权限的名字来进行的，和URL无关
       1.首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应方法即可。
       2.我们在这里注册CustomUserService的Bean，然后通过重写configure方法添加我们自定义的认证方式。
       3.在configure(HttpSecurity http)方法中，我们设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求，注销请求也是任何人都可以访问的。
       4.permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
       5.这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，我可以这样来写：.antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也可以设置admin文件夹下的文件可以有多个角色来访问，写法如下：.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
       6.可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的资源，写法如下.antMatchers("/admin/**").hasIpAddress("210.210.210.210")

       更多的权限控制方式参看下表：
          1)access(String)         SpringEL表达式结果为true时可以访问
          2)anonymous()            匿名可访问
          3)denyAll()              用户不可访问
          4)fullyAuthenticated     用户完全认证可访问（非remember me下自动登陆）
          5)hasAnyAuthority(String ...) 参数中任意权限的用户可访问
          6)hasAnyRole(String ...) 参数中任意角色的用户可访问
          7)hasAuthority(String)    某一权限的用户可访问
          8)hasRole(String)         不建议用，不要被名字迷惑，除了要求数据库存储的权限的名称前要加ROLE_前缀外，和hasAuthority功能一样
          9)permitAll()             所有用户可访问
          10)rememberMe()           允许通过remember me登陆的用户访问
          11)authenticated()        用户登陆后可访问
          12)haslpAddress(String)   用户来自参数中的IP时可访问
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        /**
         * 配置安全过滤器HttpSecurity
         */
        http.csrf().disable();// 关闭csrf防护
        http
                .authorizeRequests()
                    .antMatchers("/jscustom/**","/jslib/**","/druid/**").permitAll()     // 允许访问的静态资源，注意这里的根目录是指public/static/templates
                    .anyRequest().authenticated()
                    .and()
                .headers().frameOptions().disable()//允许iframe嵌套（springSecurty使用X-Frame-Options防止网页被Frame，默认是deny，拒绝iframe嵌套）
                .and()
                .formLogin()
                    .loginPage("/login")       // 设置登录页面（这里不是页面，而是登录控制类中相应的路径，下同）
                    .successForwardUrl("/index") //登录成功后forward到参数指定的url
                    .failureUrl("/login")      //登录失败后就forward到参数指定的url
                    .permitAll()
                    .and() // 登录成功跳转路径url
                .logout()
                    .permitAll();

        http.sessionManagement()//配置session的功能实现单点登录
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)//强制让之前登录的同一账号被挤出去
                .expiredUrl("/login");//session过期后跳转的url
        http.headers().contentTypeOptions().disable();//设置允许加载静态资源文件,如css

    }
}
