package com.giggal.initask.config.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class CachedServletInputStream extends ServletInputStream {
	private final ByteArrayInputStream input;

	public CachedServletInputStream(byte[] input) {
		this.input = new ByteArrayInputStream(input);
	}

	@Override
	public boolean isFinished() {
		return input.available() == 0;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener listener) {
	}

	@Override
	public int read() throws IOException {
		return input.read();
	}
}
