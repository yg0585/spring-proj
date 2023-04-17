package com.giggal.initask.config.appender.slack;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.Data;

@Data
public class SlackMessage {
	private Block[] blocks;

	public SlackMessage(IThrowableProxy throwableProxy) {
		blocks = new Block[2];
		blocks[0] = Block.headerBlock();
		blocks[1] = new Block(throwableProxy);
	}
}
