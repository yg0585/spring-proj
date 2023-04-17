package com.giggal.initask.member.request;

import lombok.Data;

@Data
public class SignInRequest {
	private final String email;
	private final String password;
}
