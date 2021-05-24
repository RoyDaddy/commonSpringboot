package com.neok.commonSpringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.neok.commonSpringboot.service.SecurityService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityService securityService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/assets/**",
				"/css/**",
				"/js/**",
				"/images/**",
				"/lib/**",
				"/plugins/**",
				"/bootstrap/**",
				"/jquery/**",
				"/img/**",
				"/vendor/**",
				"/scss/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http.authorizeRequests()
        		.antMatchers("/login", "/signup", "/main").permitAll()	// 언제나허용
				.antMatchers("/admin/**").access("ROLE_TEMP_1")	// /admin/** 경로는 ROLE_TEMP_1 권한이있어야 사용가능
				.antMatchers("/**").authenticated() // /** 모든경로는 로그인시에 사용가능
            .and() // 로그인 설정
                .formLogin()                
                .loginPage("/login")	//로그인 뷰페이지 path설정 username과 password 를 param으로 받아야함
                .defaultSuccessUrl("/")	//로그인성공시 redirect path
                .successHandler(authenticationSuccessHandler())	//로그인성공시 처리될 핸들러
            .and() // 로그아웃 설정
	            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	//로그아웃 path
                .logoutSuccessUrl("/login")	//로그아웃성공시 redirect경로
                .invalidateHttpSession(true) //인증정보삭제 세션무효화
            .and()
                .exceptionHandling()	// 인증예외처리 
//                .accessDeniedPage("/content/temp")	// redirect 뷰페이지
                .accessDeniedHandler(accessDeniedHandler()) // 처리될 핸들러
        	.and()
        		.rememberMe().key("temp");	//자동로그인처리
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(passwordEncoder());
    }
}