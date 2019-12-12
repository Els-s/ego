package org.javaboy.egosso;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-11-18 15:37
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy")
                .password("$2a$10$FGl7HbjSHverVJSMzKTBw.PrehUtQCVbf3W0tJhmovWDoA54/mNs6")
                .roles("user")
                .and()
                .withUser("admin")
                .password("$2a$10$FGl7HbjSHverVJSMzKTBw.PrehUtQCVbf3W0tJhmovWDoA54/mNs6")
                .roles("admin", "user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello")
                .hasAnyRole("user", "admin")
                .antMatchers("/admin")
                .hasRole("admin")
                .antMatchers(HttpMethod.POST,"/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter("/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CheckTokenFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
