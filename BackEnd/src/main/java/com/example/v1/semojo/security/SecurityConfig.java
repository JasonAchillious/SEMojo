package com.example.v1.semojo.security;

import com.example.v1.semojo.security.filter.JwtFilter;
import com.example.v1.semojo.security.filter.JwtLoginFilter;
import com.example.v1.semojo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_CONTRIBUTOR > ROLE_CUSTOMER");
        return roleHierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("**/admin/**").hasRole("ADMIN")
                .antMatchers("**/contributor/**").hasRole("CONTRIBUTOR")
                .antMatchers("**/customer/**").hasRole("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((req, resp, authentication) -> {
                    StringBuffer roleStrBuf = new StringBuffer();
                    roleStrBuf.append(" ");
                    for (GrantedAuthority authority: authentication.getAuthorities()){
                        roleStrBuf.append(authority.getAuthority()).append(" ");
                    }
                    String jwt = Jwts.builder()
                            .claim("authorities", roleStrBuf)
                            .setSubject(authentication.getName())
                            .setExpiration(new Date(System.currentTimeMillis() + 60*1000))
                            .signWith(SignatureAlgorithm.HS512, "zzx@11711621")
                            .compact();
                    resp.setContentType("application/json;charset=utf-8");
                    Map<String, String> successMsg = new HashMap<>();
                    successMsg.put("Principal" , authentication.getPrincipal().toString());
                    successMsg.put("token", jwt);
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(successMsg));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, exception) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(exception.getMessage()));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
//                .addFilterBefore(new JwtLoginFilter("/lohin",
//                        authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)

                .csrf().disable();;
    }
}
