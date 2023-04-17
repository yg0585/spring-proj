package com.giggal.initask.config.appender.slack;

import lombok.Data;

@Data
public class SlackField {
	private String type;
	private String text;

	public SlackField() {
		type = "plain_text";
		text = "Error Log";
	}

	public SlackField(String head, String body) {
		type = "mrkdwn";

		StringBuilder sb = new StringBuilder();
		head(head, sb);
		sb.append(body);

		text = sb.toString();
	}

	private void head(String head, StringBuilder sb) {
		sb.append("*");
		sb.append(head);
		sb.append("*\n\n");
	}
}
