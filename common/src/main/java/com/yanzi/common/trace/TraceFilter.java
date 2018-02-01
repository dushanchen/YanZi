package com.yanzi.common.trace;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class TraceFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        boolean trace = false;
        String debug = servletRequest.getParameter("debugonline");
        String contentType = servletRequest.getParameter("content_type");

        Trace.closeTrace();
        if (null != debug
                && debug.equals("true")
                && (contentType == null || contentType.equals("json") || contentType
                        .equals("json_simple"))) {
            trace = true;
            Trace.openTrace();
            response.getOutputStream().write("{\"info\":".getBytes());
        }

        try {
            chain.doFilter(request, response);
            if (trace) {
                response.getOutputStream().write(
                        (",\"trace\":" + Trace.getTraceInfo().toJSONString() + "}").getBytes());
            }
        } catch (IOException e) {
            throw e;
        } catch (ServletException e) {
            throw e;
        } finally {
            Trace.closeTrace();
        }
    }
}
