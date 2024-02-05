package shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	private int product_code;
	private String product_name;
	private int price;
	private String description;
	private String filename;
}
