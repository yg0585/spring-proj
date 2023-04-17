package com.giggal.initask.webhook.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebhookController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/test")
	public String test() throws IOException {
		String s = null;
		s.charAt(0);
		return ":/";
	}
}
