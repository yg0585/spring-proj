package com.giggal.initask.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.giggal.initask.member.model.enums.Role;
import com.giggal.initask.member.request.SignUpRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	public Member(SignUpRequest request, BCryptPasswordEncoder bCryptPasswordEncoder) {
		email = request.getEmail();
		password = bCryptPasswordEncoder.encode(request.getPassword());
		role = Role.ROLE_USER;
	}
}
