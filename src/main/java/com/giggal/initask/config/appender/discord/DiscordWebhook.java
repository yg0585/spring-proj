package com.giggal.initask.config.appender.discord;

import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.giggal.initask.config.appender.util.AppenderUtil;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiscordWebhook {

	private static Logger logger = (Logger)LoggerFactory.getLogger(DiscordWebhook.class);

	private static final int DISCORD_MAX_LENGTH = 1000;

	private static final String url = "https://discord.com/api/webhooks/1096011716480991262/pARnmPlUEIwDIJ_gfoJQoHnTRyLltnP1aAbiSf7Ht4EIFKZwu-CGend2I2VcpkMhk_-Q";

	public static void generate(IThrowableProxy throwableProxy) {
		DiscordMessage discordMessage;
		DiscordMessage discordError;

		try {
			discordMessage = new DiscordMessage(throwableProxy);
			discordError = new DiscordMessage(
				ThrowableProxyUtil.asString(throwableProxy).substring(0, DISCORD_MAX_LENGTH));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		AppenderUtil.send(url, discordMessage, logger);
		AppenderUtil.send(url, discordError, logger);
	}
}
