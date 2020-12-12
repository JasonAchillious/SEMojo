package com.example.v1.semojo.security.filter;

import com.example.v1.semojo.security.util.JwtUtil;
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
import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JwtAuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String jwtToken = req.getHeader("authorization");
        boolean doFilterFlag = true;
        //System.out.println(jwtToken);
        System.out.println(jwtToken);
        if (jwtToken != null && jwtToken.trim().length() > 0) {
            try {
                Claims claims = JwtUtil.parseJWT(jwtToken);
//                        Jwts.parser()
//                        .setSigningKey("zzx@11711621")
//                        .parseClaimsJws(jwtToken.replace("Bearer", ""))
//                        .getBody();
                String username = claims.getSubject();
                List<GrantedAuthority> authorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                        null,
                        authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (SignatureException | MalformedJwtException e){
                PrintWriter out = response.getWriter();
                out.write("{ \"error_msg\":" + "token parsing error" + "}");
                out.flush();
                out.close();
                doFilterFlag = false;
            } catch (ExpiredJwtException e){
                PrintWriter out = response.getWriter();
                out.write("{ \"error_msg\":" + "token has expired" + "}");
                out.flush();
                out.close();
                doFilterFlag = false;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // token empty will redirect to login page
//        else {
//            response.getWriter().write("{ \"error_msg\":" + "token is empty" + "}");
//        }
        if (doFilterFlag)
            chain.doFilter(req, response);
    }
}
