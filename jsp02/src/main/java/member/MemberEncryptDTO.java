package member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberEncryptDTO {
	private String userid;
	private String passwd;
	private String name;
	
}
