package com.giggal.initask.config.appender.telegram;

import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.giggal.initask.config.appender.discord.DiscordWebhook;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.IThrowableProxy;

public class TelegramWebhook {

	private static Logger logger = (Logger)LoggerFactory.getLogger(DiscordWebhook.class);

	private static final String URL = "https://api.telegram.org/bot6094910095:AAEUeydZKeEedkEUDqIY4DLjCyG7Tto1ACg/sendMessage";
	private static final String CHAT_ROOM_ID = "-1001892776731";

	private static final String CHAT_ID = "chat_id";
	private static final String TEXT = "text";

	private static final String PARSE_MODE = "parse_mode";
	private static final String MARKDOWN = "markdown";

	public static void generate(IThrowableProxy throwableProxy) {
		String message = TelegramMessage.generateMessage(throwableProxy);

		DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(URL);
		defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

		WebClient.builder()
			.uriBuilderFactory(defaultUriBuilderFactory).build()
			.get()
			.uri(v -> v.queryParam(CHAT_ID, CHAT_ROOM_ID)
				.queryParam(TEXT, "{message}")
				.queryParam(PARSE_MODE, MARKDOWN)
				.build(message))
			.retrieve()
			.toBodilessEntity()
			.subscribe((e) -> {
			}, (error) -> {
				logger.error(error.getMessage());
			});
	}
}
