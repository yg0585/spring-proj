package com.giggal.initask.member.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SignUpRequest {

	@Email
	@NotNull
	private String email;

	@NotNull
	private String password;
}
