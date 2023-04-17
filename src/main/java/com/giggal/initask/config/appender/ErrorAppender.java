package com.giggal.initask.config.appender;

import com.giggal.initask.config.appender.discord.DiscordWebhook;
import com.giggal.initask.config.appender.slack.SlackWebhook;
import com.giggal.initask.config.appender.telegram.TelegramWebhook;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.AppenderBase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorAppender extends AppenderBase<ILoggingEvent> {

	private final static boolean LOGGING_DISCORD = true;

	private final static boolean LOGGING_SLACK = true;

	private final static boolean LOGGING_TELEGRAM = true;

	@Override
	protected void append(ILoggingEvent eventObject) {
		if (eventObject.getLevel().isGreaterOrEqual(Level.ERROR)) {
			IThrowableProxy throwableProxy = eventObject.getThrowableProxy();

			if (LOGGING_DISCORD)
				DiscordWebhook.generate(throwableProxy);
			if (LOGGING_SLACK)
				SlackWebhook.generate(throwableProxy);
			if (LOGGING_TELEGRAM)
				TelegramWebhook.generate(throwableProxy);
		}
	}
}
