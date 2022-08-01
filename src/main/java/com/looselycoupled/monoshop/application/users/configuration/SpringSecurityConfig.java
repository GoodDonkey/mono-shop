package com.looselycoupled.monoshop.application.users.configuration;

import com.looselycoupled.monoshop.application.users.features.security.ExceptionHandlingFilter;
import com.looselycoupled.monoshop.application.users.features.security.JwtAuthenticationFilter;
import com.looselycoupled.monoshop.application.users.features.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    
    private final JwtManager jwtManager;
    private final UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(new JwtAuthenticationFilter(jwtManager, userDetailsService), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new ExceptionHandlingFilter(), JwtAuthenticationFilter.class)
                .httpBasic().disable()
                .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/login", "/signup", "/api/v1/signup", "/api/v1/login").permitAll()
                .anyRequest().authenticated()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .logout()
                .logoutUrl("/api/v1/logout")
                .logoutSuccessUrl("/");
        return http.build();
    }
    
    @Bean
    protected PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
}
