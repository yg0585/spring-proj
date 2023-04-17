package com.giggal.initask.config.appender.slack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.MDC;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.giggal.initask.config.filter.util.MDCUtil;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Block {
	private String type;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SlackField[] fields;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SlackField text;

	public static Block headerBlock() {
		Block block = new Block();
		block.type = "header";
		block.text = new SlackField();
		return block;
	}

	public Block(IThrowableProxy throwableProxy) {
		fields = new SlackField[7];
		type = "section";

		fields[0] = new SlackField("에러 내용",
			throwableProxy.getClassName() + ": " + throwableProxy.getMessage() + "\n" + ThrowableProxyUtil.asString(
				throwableProxy).substring(0, 1000));
		fields[1] = new SlackField("http header 정보", MDC.get(MDCUtil.HTTP_HEADER));
		fields[2] = new SlackField("http body 정보", MDC.get(MDCUtil.HTTP_BODY));
		fields[3] = new SlackField("서버 정보", MDC.get(MDCUtil.SERVER_INFO));
		fields[4] = new SlackField("클라이언트 정보", MDC.get(MDCUtil.CLIENT_INFO));
		fields[5] = new SlackField("발생 시간",
			LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		fields[6] = new SlackField("Request URL", MDC.get(MDCUtil.REQ_URL));
	}
}
