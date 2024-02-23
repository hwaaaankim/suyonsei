package com.dev.SuYonsei.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.SuYonsei.model.Member;
import com.dev.SuYonsei.model.PrincipalDetails;
import com.dev.SuYonsei.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	// 시큐리티 session -> Authentication -> UserDetails
	// 시큐리티 세션(내부 Authentication(내부 UserDetails(PrincipalDetails)))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailService.loadUserByUsername");
		log.info("PrincipalDetailService.loadUserByUsername");
		
		Optional<Member> member = memberRepository.findByUsername(username);
		if (!member.isPresent()) {
			throw new UsernameNotFoundException(username);
		}

		return new PrincipalDetails(member.get());
	}
}
