package com.example.v1.semojo.security;

import com.example.v1.semojo.security.Handler.LoginFailureHandler;
import com.example.v1.semojo.security.Handler.LoginSuccessHandler;
import com.example.v1.semojo.security.filter.JwtAuthFilter;
import com.example.v1.semojo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("SUPER_ADMIN > ROLE_ADMIN > ROLE_CONTRIBUTOR > ROLE_CUSTOMER");
        return roleHierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .and()
                .inMemoryAuthentication()
                .withUser("ZhaoZhixiang")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("SUPER_ADMIN")
                .and()
                .withUser("XuPinshen")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN")
                .and()
                .withUser("ChengQianfan")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("CONTRIBUTOR")
                .and()
                .withUser("ZhuFang")
                .password(new BCryptPasswordEncoder().encode("123456")).roles("CUSTOMER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/img/**", "/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/contributor/**").hasRole("CONTRIBUTOR")
                .antMatchers("/customer/**").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/hello","/products/**").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();
//                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/index.html").permitAll()
                .loginProcessingUrl("/login")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
//                .exceptionHandling().accessDeniedHandler(new AccessDeniedHanderImpl())
//                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
