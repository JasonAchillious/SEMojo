package com.example.v1.semojo.security.Handler;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHanderImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e)
            throws IOException, ServletException {
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        resp.getWriter().write("{\"code\": \"401\", \"msg\": \"Login out of date or unlogin\"}");
    }
}
