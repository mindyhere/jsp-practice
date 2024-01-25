package product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class ProductDAO {
	public List<Map<String, Object>> list() {
		//SQL 실행객체
		SqlSession session = MybatisManager.getInstance().openSession();
		// MybatisManager → sqlmap/sqlMapConfig.xml 참조(DB연결정보, SQL명령어 etc.) → <mapper resource> 정보추가
		
		List<Map<String, Object>> items = session.selectList("product.list");
		// Map : key(필드명) value			   product.xml → namespace.id
		return items;
	}

}
