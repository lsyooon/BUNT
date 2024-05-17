package com.baseball.bunt.model.dto.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchCondition {
	private int teamId;
	private String key = "none";
	private String word;

	public SearchCondition() {
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "SearchCondition{" +
				"teamId=" + teamId +
				", key='" + key + '\'' +
				", word='" + word + '\'' +
				'}';
	}
}
