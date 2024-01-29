package guestbook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestbookDTO {
	private int idx;
	private String name;
	private String email;
	private String passwd;
	private String contents;
	private String post_date;
}
