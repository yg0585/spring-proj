package com.giggal.initask.member.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giggal.initask.member.Member;
import com.giggal.initask.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByEmail(username)
			.map(this::createUserDetails)
			.orElseThrow(() -> new RuntimeException());
	}

	public UserDetails createUserDetails(Member member) {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(member.getRole().toString());
		return new User(member.getEmail(), member.getPassword(), Collections.singletonList(simpleGrantedAuthority));
	}
}
