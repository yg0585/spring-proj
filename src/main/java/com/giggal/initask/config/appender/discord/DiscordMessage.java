package com.giggal.initask.config.appender.discord;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.Data;

@Data
public class DiscordMessage {
	private String content;
	private Embeds[] embeds;

	public DiscordMessage(String error) {
		embeds = new Embeds[1];
		embeds[0] = new Embeds(error);
	}

	public DiscordMessage(IThrowableProxy throwableProxy) throws JsonProcessingException {
		embeds = new Embeds[1];
		embeds[0] = new Embeds(throwableProxy);
	}
}
