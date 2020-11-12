package com.example.v1.semojo.security.Handler;

import com.example.v1.semojo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        Authentication authentication)
            throws IOException, ServletException {
        StringBuffer roleStrBuf = new StringBuffer();
        for (GrantedAuthority authority: authentication.getAuthorities()){
            roleStrBuf.append(authority.getAuthority()).append(",");
        }
        String jwt = Jwts.builder()
                .claim("authorities", roleStrBuf)
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*1000))
                .signWith(SignatureAlgorithm.HS512, "zzx@11711621")
                .compact();
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write("{\"code\": \"200\", "
                + "\"msg\": \"login successfully\", "
                + "\"username\": " +  authentication.getName()
                + ", \"role\": " +  roleStrBuf
                + ", \"token\": " + jwt
                + " }"    );
    }
}
