package com.neok.commonSpringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neok.commonSpringboot.domain.Role;
import com.neok.commonSpringboot.mapper.SecurityMapper;

@Service
public class SecurityService implements UserDetailsService{
	@Autowired
	SecurityMapper securityMapper;
	

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SimpleGrantedAuthority auth = null;
            List<GrantedAuthority> authorities = new ArrayList<>();
            BCryptPasswordEncoder tempEncoder = new BCryptPasswordEncoder(); //임시
            
            //DB에서 id로 매칭해서 가져온 user정보
            Map<String, Object> findUser = securityMapper.findById(username);
            if(findUser == null) {
            	throw new UsernameNotFoundException(username);
            }
            
            switch((String)findUser.get("auth")) {
            case "ROLE_TEMP_1": 
            	auth = new SimpleGrantedAuthority(Role.ROLE_1.getValue()); break;
            	
            case "ROLE_TEMP_2": 
            	auth = new SimpleGrantedAuthority(Role.ROLE_2.getValue()); break;
            	
            case "ROLE_TEMP_3": 
            	auth = new SimpleGrantedAuthority(Role.ROLE_3.getValue()); break;
            	
            default: 
            	throw new UsernameNotFoundException(username);
            }
            
            authorities.add(auth);
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_LOGIN.getValue()));
            
            return User.builder()
            	.username((String)findUser.get("username"))
            	.password(tempEncoder.encode((String)findUser.get("password"))) //임시
            	.authorities(authorities)
            	.build();      
        } catch (Exception e) {
        	e.printStackTrace();
            throw new UsernameNotFoundException(username);
        }
    }
}
