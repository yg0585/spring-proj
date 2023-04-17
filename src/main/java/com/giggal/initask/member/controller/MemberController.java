package com.giggal.initask.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giggal.initask.jwt.JwtToken;
import com.giggal.initask.member.request.SignInRequest;
import com.giggal.initask.member.request.SignUpRequest;
import com.giggal.initask.member.response.SignUpResponse;
import com.giggal.initask.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
	private final MemberService memberService;

	// @PostMapping("/signin")
	// public ResponseEntity<JwtToken> login()

	@PostMapping("/signup")
	public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
		return new ResponseEntity<>(memberService.signUp(request), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtToken> signIn(@RequestBody SignInRequest request) {
		return new ResponseEntity<>(memberService.signIn(request), HttpStatus.OK);
	}
}
