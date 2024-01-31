package page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class EmpDAO {
	public List<EmpDTO> list(int start, int end) {
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		List<EmpDTO> items = session.selectList("emp.list", map);
		session.close();
		return items;
	}

	public int count() {
		SqlSession session = MybatisManager.getInstance().openSession();
		int count = session.selectOne("emp.count");
		session.close();
		return count;
	}
}
