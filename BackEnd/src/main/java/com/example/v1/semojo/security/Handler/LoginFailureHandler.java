package com.example.v1.semojo.security.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        AuthenticationException e) throws IOException, ServletException {
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = resp.getWriter();
        resp.setStatus(401);
        Map<String, Object> map = new HashMap<>();
        if (e instanceof LockedException){
            map.put("msg", "Account was locked, login failure.");
        } else if (e instanceof BadCredentialsException){
            map.put("msg", "Bad Credentials");
        } else if (e instanceof DisabledException){
            map.put("msg", "Account was banned, login failure");
        } else if (e instanceof AccountExpiredException){
            map.put("msg", "Account was out of date, login failure.");
        } else if (e instanceof CredentialsExpiredException){
            map.put("msg", "Password was out of date, login failure.");
        } else {
            map.put("msg", "login failure");
        }
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
