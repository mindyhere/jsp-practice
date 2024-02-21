package survey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SummaryDTO {
	private int survey_idx;
	private int num;
	private int sum_num;
	private double rate;
}
