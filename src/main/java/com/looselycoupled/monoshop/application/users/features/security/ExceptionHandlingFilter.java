package com.looselycoupled.monoshop.application.users.features.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.looselycoupled.monoshop.web.dtos.ErrorResponseBody;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
                                                                                                                       ServletException,
                                                                                                                       IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (SignatureException ex){
            log.error("exception exception handler filter");
            setErrorResponse(request, response, ex);
        } catch (RuntimeException ex){
            log.error("runtime exception exception handler filter");
        }
    }
    
    public void setErrorResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) throws
                                                                                                                JsonProcessingException {
        response.setStatus(403);
        response.setContentType("application/json");
        String body = ErrorResponseBody.forbiddenOf(exception, exception.getMessage(), request.getRequestURI()).asJson();
        try{
            response.getWriter().write(body);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
