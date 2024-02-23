package com.dev.SuYonsei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.SuYonsei.model.Member;
import com.dev.SuYonsei.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Service
@Slf4j
public class MemberService {

	@Bean(name = "saveBean")
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private MemberRepository memberRepository;
	
	public Member insertAdmin(Member member) {
		String encodedPassword = passwordEncoder().encode(member.getPassword());
		member.setPassword(encodedPassword);
		member.setMemberRole("ROLE_ADMIN");
		
		log.info("NEW ADMIN");
		
		return memberRepository.save(member);

	}
}
