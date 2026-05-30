package com.AngularWithSpringBoot.chatboatDto;

import java.util.List;

import lombok.Data;

@Data
public class OpenAiResponse {

    private List<Output> output;

    public List<Output> getOutput() {
		return output;
	}

	public void setOutput(List<Output> output) {
		this.output = output;
	}

	@Data
    public static class Output {
        public List<Content> getContent() {
			return content;
		}

		public void setContent(List<Content> content) {
			this.content = content;
		}

		private List<Content> content;
    }

    @Data
    public static class Content {
        private String text;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
    }
}
