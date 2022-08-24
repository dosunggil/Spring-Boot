package com.cho.book.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*
    @Configuration
    지금부터 이 클래스는 Config 설정을 하는 클래스다.
    *-context.xml 파일의 설정을 대신하는 클래스 선언
 */
@Slf4j
@Configuration
public class SecurityConfig {

    // 비밀번호 암호화 도구
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    /*
         @Bean :
              프로젝트가 시작될 때 자동으로 실행하라

        User 의 Request 를 Security 를 통해 이루어지도록 하는 Bean
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/user/login").permitAll() // user/login 아무나
                .antMatchers("/user/join").permitAll() // user/join 아무나
                .antMatchers("/").permitAll() // root 접속 아무나
                .antMatchers("/book/**").authenticated() // book/** 으로 시작되는 모든 요청은 login 후에
                .anyRequest().authenticated() // 위에서 지정한 이외의 패턴일 경우
                .and()
                .formLogin()
                .loginPage("/user/login")
                .and()
                .logout();
        return http.build();
    }

    /*
        이 Bean 은 생성될 때 세개의 객체 매개변수를 주입받는다.
        이 주입받은 객체를 사용하여 Security 인증을 수행한다.
        HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService

     */
    public AuthenticationManager authenticationManager(
            HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
     return   http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /*
        /static/, /js/, /css/, /upload/, /files/ 
        이러한 폴더는 controller 를 향하지 않고 바로 response 를 하도록 선언하는 부분
        legacy 에서 resources mapping 으로 설정하는 부분을 여기에서 선언.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        
        // 아래에 지정된 경로 (pattern) 으로 요청이 들어오면
        // security 를 거치지 말고 바로 통과시켜라.
        return (web) ->web.ignoring()
                .antMatchers("/static/**","/js/**","/css/**","/upload/**");
    }

}
