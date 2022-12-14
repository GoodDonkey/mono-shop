package com.looselycoupled.monoshop.users.features.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.looselycoupled.monoshop.common.web.dtos.ErrorResponseBody;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
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
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature: {}", ex.getMessage());
            setErrorResponse(request, response, ex);
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT: {}", ex.getMessage());
            setErrorResponse(request, response, ex);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT: {}", ex.getMessage());
            setErrorResponse(request, response, ex);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT: {}", ex.getMessage());
            setErrorResponse(request, response, ex);
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
