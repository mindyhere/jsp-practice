package book;

public class BookDTO {
	private int idx;
	private String title;
	private String author;
	private int price;
	private int amount;

	public BookDTO() {
	}

	public BookDTO(int idx, String title, String author, int price, int amount) {
		super();
		this.idx = idx;
		this.title = title;
		this.author = author;
		this.price = price;
		this.amount = amount;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BookDTO [idx=" + idx + ", title=" + title + ", author=" + author + ", price=" + price + ", amount="
				+ amount + "]";
	}

}
