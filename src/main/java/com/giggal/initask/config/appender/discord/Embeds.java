package com.giggal.initask.config.appender.discord;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.MDC;

import com.giggal.initask.config.filter.util.MDCUtil;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Embeds {
	private Field[] fields;
	private int color;

	public Embeds(IThrowableProxy throwableProxy) {
		fields = new Field[7];
		fields[0] = new Field("에러 내용", throwableProxy.getClassName() + ": " + throwableProxy.getMessage());
		fields[1] = new Field("HTTP 헤더 정보", MDC.get(MDCUtil.HTTP_HEADER));
		fields[2] = new Field("HTTP 바디 정보", MDC.get(MDCUtil.HTTP_BODY));
		fields[3] = new Field("요청 URL", MDC.get(MDCUtil.REQ_URL));
		fields[4] = new Field("서버 정보", MDC.get(MDCUtil.SERVER_INFO));
		fields[5] = new Field("클라이언트 정보", MDC.get(MDCUtil.CLIENT_INFO));
		fields[6] = new Field("발생 시간", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		red();
	}

	private void red() {
		int rgb = Color.RED.getRGB();
		int r = (rgb >> 16) & 0xFF;  // R 값 추출
		int g = (rgb >> 8) & 0xFF;   // G 값 추출
		int b = rgb & 0xFF;
		this.color = (r << 16) | (g << 8) | b;
	}

	public Embeds(String s) {
		fields = new Field[1];
		fields[0] = new Field("에러 상세 내용", s);
		red();
	}
}
