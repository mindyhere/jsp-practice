package board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int num; // 글번호
	private String writer; // 작성자
	private String subject; // 글제목
	private String passwd; // 비밀번호
	private String reg_date; // 작성일
	private int hit; // 조회수
	private int group_num; // 답변그룹
	private int re_order; // 출력순서
	private int re_depth; // 답변단계
	private String contents; // 게시글내용
	private String ip;
	private int count_comments;
	private String filename; // 첨부파일
	private int filesize;
	private int down; // 다운로드횟수
}
