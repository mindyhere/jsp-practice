package board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCommentDTO {
	private int comment_num;
	private int board_num;
	private String writer;
	private String contents;
	private Date reg_date;

}
