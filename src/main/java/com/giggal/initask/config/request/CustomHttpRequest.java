package com.giggal.initask.config.request;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

public class CustomHttpRequest extends HttpServletRequestWrapper {

	private final byte[] body;

	/**
	 * Create a new {@code HttpRequest} wrapping the given request object.
	 *
	 * @param request the request object to be wrapped
	 */
	public CustomHttpRequest(HttpServletRequest request) throws IOException {
		super(request);
		InputStream inputStream = request.getInputStream();
		this.body = StreamUtils.copyToByteArray(inputStream);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CachedServletInputStream(body);
	}
}