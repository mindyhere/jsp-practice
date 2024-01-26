package product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class ProductDAO {
	public List<Map<String, Object>> list() {
		// SQL 실행객체
		SqlSession session = MybatisManager.getInstance().openSession();

		// MybatisManager → sqlmap/sqlMapConfig.xml 참조(DB연결정보, SQL명령어 etc.) → <mapper
		// resource> 정보추가

		List<Map<String, Object>> items = session.selectList("product.list");
		// Map : key(필드명) value product.xml → namespace.id

		session.close();
		return items;
	}

	public void insert(Map<String, Object> map) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("product.insert", map);
		// xml → 패키지(namespace).아이디
		// insert() : 파라미터 1개만 사용가능 → map 객체로 묶어서 전달
		session.commit();	//추가 수정 삭제
		session.close(); // 세션 종료

	}

	public Map<String, Object> detail(int product_code) {
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> map = session.selectOne("product.detail", product_code);
		// selectOne 레코드 1개, selectList 레코드목록(여러개)
		session.close(); // 세션 종료
		return map;
	}

	public void update(Map<String, Object> map) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("product.update", map);
		session.commit();
		session.close(); // 세션 종료
	}

	public void delete(int product_code) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("product.delete", product_code);
		session.commit();
		session.close(); // 세션 종료
	}

}
