package com.giggal.initask.config.filter.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.slf4j.MDC;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giggal.initask.config.request.CustomHttpRequest;

public class MDCUtil {

	public static final String HTTP_HEADER = "http_header";
	public static final String HTTP_BODY = "http_body";
	public static final String REQ_URL = "url";
	public static final String CLIENT_INFO = "client_info";

	public static final String REMOTE_IP = "remote_ip";
	public static final String REMOTE_HOST = "remote_host";
	public static final String REMOTE_PORT = "remote_port";

	public static final String SERVER_INFO = "server_info";
	public static final String SERVER_IP = "server_ip";
	public static final String SERVER_NAME = "server_name";
	public static final String SERVER_PORT = "server_port";

	public static void put(CustomHttpRequest customHttpRequest) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> maps = new HashMap<>();
		customHttpRequest.getHeaderNames()
			.asIterator()
			.forEachRemaining(headerName -> maps.put(headerName, customHttpRequest.getHeader(headerName)));

		MDC.put(HTTP_HEADER, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(maps));
		MDC.put(HTTP_BODY, mapper.writeValueAsString(
			mapper.readTree(StreamUtils.copyToString(customHttpRequest.getInputStream(), StandardCharsets.UTF_8))
		));

		MDC.put(REQ_URL, customHttpRequest.getRequestURI());

		HashMap<String, String> map = new HashMap<>();
		map.put(REMOTE_IP, customHttpRequest.getRemoteAddr());
		map.put(REMOTE_HOST, customHttpRequest.getRemoteHost());
		map.put(REMOTE_PORT, Integer.toString(customHttpRequest.getRemotePort()));
		MDC.put(CLIENT_INFO, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));

		map = new HashMap<>();
		map.put(SERVER_IP, customHttpRequest.getLocalAddr());
		map.put(SERVER_NAME, customHttpRequest.getLocalName());
		map.put(SERVER_PORT, Integer.toString(customHttpRequest.getServerPort()));
		MDC.put(SERVER_INFO, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));
	}
}
