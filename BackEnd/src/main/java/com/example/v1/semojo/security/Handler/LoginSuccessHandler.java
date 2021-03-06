package com.example.v1.semojo.security.Handler;

import com.example.v1.semojo.api.model.LoginRespModel;
import com.example.v1.semojo.security.util.JwtUtil;
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
import java.util.*;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        Authentication authentication)
            throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        StringBuffer roleStrBuf = new StringBuffer();
        for (GrantedAuthority authority: authentication.getAuthorities()){
            roleStrBuf.append(authority.getAuthority()).append(",");
        }
        String issuer = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), issuer,
                authentication.getName(),2*60*60*1000, roleStrBuf.toString());

        resp.setContentType("application/json;charset=utf-8");
        LoginRespModel respModel = new LoginRespModel(authentication.getName(), roleStrBuf.toString(), jwt);
        out.write("{\"code\": \"200\", "
                + "\"msg\": \"login successfully\", "
                + "\"username\": " + "\""+  authentication.getName() + "\""
                + ", \"role\": " + "\"" + roleStrBuf + "\""
                + ", \"token\": " + "\"" + jwt + "\""
                + " }"
        );
        out.flush();
        out.close();
        //resp.sendRedirect("/");
    }
}
