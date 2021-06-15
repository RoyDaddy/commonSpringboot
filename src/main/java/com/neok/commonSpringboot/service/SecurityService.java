package com.neok.commonSpringboot.service;

import com.neok.commonSpringboot.domain.Role;
import com.neok.commonSpringboot.entity.Admin;
import com.neok.commonSpringboot.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService{
	final private AdminRepo adminRepo;
	private PasswordEncoder encoder = new BCryptPasswordEncoder();


	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SimpleGrantedAuthority auth = null;
            List<GrantedAuthority> authorities = new ArrayList<>();
            String enc = encoder.encode("test");

            //DB에서 id로 매칭해서 가져온 user정보
            Admin findAdmin = adminRepo.findOneById(username);
            if(findAdmin == null) {
            	throw new UsernameNotFoundException(username);
            }
            
//            switch((String)findAdmin.get("auth")) {
//            case "ROLE_TEMP_1":
//            	auth = new SimpleGrantedAuthority(Role.ROLE_1.getValue()); break;
//
//            case "ROLE_TEMP_2":
//            	auth = new SimpleGrantedAuthority(Role.ROLE_2.getValue()); break;
//
//            case "ROLE_TEMP_3":
//            	auth = new SimpleGrantedAuthority(Role.ROLE_3.getValue()); break;
//
//            default:
//            	throw new UsernameNotFoundException(username);
//            }
//
//            authorities.add(auth);
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_LOGIN.getValue()));
            
            return User.builder()
            	.username(findAdmin.getId())
            	.password(findAdmin.getPw())
            	.authorities(authorities)
            	.build();      
        } catch (Exception e) {
        	e.printStackTrace();
            throw new UsernameNotFoundException(username);
        }
    }
}
