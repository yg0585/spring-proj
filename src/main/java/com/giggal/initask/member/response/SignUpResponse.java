package com.giggal.initask.member.response;

import com.giggal.initask.member.Member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpResponse {
	private Long id;

	public SignUpResponse(Member member) {
		id = member.getId();
	}
}
