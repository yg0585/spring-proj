package com.giggal.initask.config.appender.util;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
public class AppenderUtil {
	public static void send(String url, Object object, Logger logger) {

		WebClient.create(url)
			.post()
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(object)
			.retrieve()
			.toBodilessEntity()
			.subscribe((e) -> {
			}, (error) -> {
				logger.error(error.getMessage());
			});
	}
}
