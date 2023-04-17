package com.giggal.initask.member.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giggal.initask.jwt.JwtToken;
import com.giggal.initask.jwt.JwtTokenProvider;
import com.giggal.initask.member.Member;
import com.giggal.initask.member.repository.MemberRepository;
import com.giggal.initask.member.request.SignInRequest;
import com.giggal.initask.member.request.SignUpRequest;
import com.giggal.initask.member.response.SignUpResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public SignUpResponse signUp(SignUpRequest request) {
		Member member = new Member(request, bCryptPasswordEncoder);
		return new SignUpResponse(memberRepository.save(member));
	}

	public JwtToken signIn(SignInRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			request.getEmail(), request.getPassword());

		Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		return jwtTokenProvider.generateToken(authenticate);
	}
}
