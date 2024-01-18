package emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok 라이브러리 활용 → @어노테이션, import 
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	private int empno;
	private String ename;
	private String job;
	private Date hiredate;
	private int sal;
}
