package memo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // 테이블객체
@Table(name = "memo") // 테이블명
@Getter
@Setter
@NoArgsConstructor // 기본생성자
@ToString
public class MemoDTO {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) 
							   // 일련번호 → sequence
	@Column(name = "idx") // 테이블의 필드명
	private int idx;

	@Column(name = "writer")
	private String writer;

	@Column(name = "memo")
	private String memo;

	@CreationTimestamp	// 날짜 (default → sysdate)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_date")
	private Date post_date;

	public MemoDTO(String writer, String memo) {
		this.writer = writer;
		this.memo = memo;
	}

}
