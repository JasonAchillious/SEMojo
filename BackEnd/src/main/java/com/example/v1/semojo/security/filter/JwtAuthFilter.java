package com.example.v1.semojo.security.filter;

import io.jsonwebtoken.*;
import org.apache.catalina.core.Constants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String jwtToken = req.getHeader("authorization");
        System.out.println(jwtToken);
        if (jwtToken != null && jwtToken.trim().length() > 0) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey("zzx@11711621")
                        .parseClaimsJws(jwtToken.replace("Bearer", ""))
                        .getBody();
                String username = claims.getSubject();
                List<GrantedAuthority> authorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                        null,
                        authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (SignatureException | MalformedJwtException e){
                response.getWriter().write("{ \"error_msg\":" + "token parsing error" + "}");
            } catch (ExpiredJwtException e){
                response.getWriter().write("{ \"error_msg\":" + "token has expired" + "}");
            }
        }
        // token empty will redirect to login page
//        else {
//            response.getWriter().write("{ \"error_msg\":" + "token is empty" + "}");
//        }
        chain.doFilter(req, response);
    }
}
