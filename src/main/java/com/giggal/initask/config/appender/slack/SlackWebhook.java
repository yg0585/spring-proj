package com.giggal.initask.config.appender.slack;

import org.slf4j.LoggerFactory;

import com.giggal.initask.config.appender.discord.DiscordWebhook;
import com.giggal.initask.config.appender.util.AppenderUtil;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.IThrowableProxy;

public class SlackWebhook {
	private static Logger logger = (Logger)LoggerFactory.getLogger(DiscordWebhook.class);

	private static final String url = "https://hooks.slack.com/services/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";

	public static void generate(IThrowableProxy throwableProxy) {
		SlackMessage slackMessage = new SlackMessage(throwableProxy);

		AppenderUtil.send(url, slackMessage, logger);
	}
}
