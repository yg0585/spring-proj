package com.giggal.initask.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.giggal.initask.config.filter.util.MDCUtil;
import com.giggal.initask.config.request.CustomHttpRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class HttpFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger(HttpFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		CustomHttpRequest customHttpRequest = new CustomHttpRequest(request);
		MDCUtil.put(customHttpRequest);
		filterChain.doFilter(customHttpRequest, response);
		MDC.clear();
	}
}
