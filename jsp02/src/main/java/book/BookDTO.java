package book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok 라이브러리 → ctrl+o 확인
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private int idx;
	private String title;
	private String author;
	private int price;
	private int amount;
	
	public BookDTO(String title, String author, int price, int amount) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.amount = amount;
	}
}
