package com.giggal.initask.config.appender.telegram;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.MDC;

import com.giggal.initask.config.filter.util.MDCUtil;

import ch.qos.logback.classic.spi.IThrowableProxy;

public class TelegramMessage {

	public static String generateMessage(IThrowableProxy throwableProxy) {
		StringBuilder sb = new StringBuilder();

		bold("에러 내용", sb);
		body(throwableProxy.getClassName() + ": " + throwableProxy.getMessage(), sb);

		bold("발생 시간", sb);
		body(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), sb);

		bold("Http Header 정보", sb);
		body(MDC.get(MDCUtil.HTTP_HEADER), sb);

		bold("Http body 정보", sb);
		body(MDC.get(MDCUtil.HTTP_BODY), sb);

		bold("Request URL", sb);
		body(MDC.get(MDCUtil.REQ_URL), sb);

		bold("서버 정보", sb);
		body(MDC.get(MDCUtil.SERVER_INFO), sb);

		bold("클라이언트 정보", sb);
		body(MDC.get(MDCUtil.CLIENT_INFO), sb);

		return sb.toString();
	}

	private static void bold(String s, StringBuilder sb) {
		sb.append(" * ");
		sb.append(s);
		sb.append(" * ");
	}

	private static void body(String s, StringBuilder sb) {
		sb.append(" ``` ");
		sb.append(s);
		sb.append(" ``` ");
	}
}
