package com.sathvik.userapi.web;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        try {
            MDC.put("correlationId", UUID.randomUUID().toString());
            chain.doFilter(req, res);
        } finally {
            MDC.clear();
        }
    }
}