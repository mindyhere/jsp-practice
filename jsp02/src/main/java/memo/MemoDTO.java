package memo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//ctrl+o → 목록 확인	
//@Getter
//@Setter
//@ToString
public class MemoDTO {
	private int idx;
	private String writer;
	private String memo;
	private Date post_date;
	private String ip;
}
