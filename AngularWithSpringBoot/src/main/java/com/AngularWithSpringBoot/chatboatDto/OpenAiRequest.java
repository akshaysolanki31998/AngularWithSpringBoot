package com.AngularWithSpringBoot.chatboatDto;

import java.util.List;

import lombok.Data;

@Data
public class OpenAiRequest {

    private String model;
    private List<Input> input;

    public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Input> getInput() {
		return input;
	}

	public void setInput(List<Input> input) {
		this.input = input;
	}

	@Data
    public static class Input {
        private String role;
        public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		private String content;
    }
}
