package survey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerDTO {
	private int answer_idx;
	private int survey_idx;
	private int num;
}
