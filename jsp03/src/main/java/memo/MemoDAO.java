package memo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class MemoDAO {
	public List<Map<String, Object>> list(String search_option, String search) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<Map<String, Object>> items = null;

		if (search_option.equals("writer_memo")) {
			items = session.selectList("memo.list_all", search);
			// selectList 레코드 여러개 ↔ selectOne 레코드 한 개
			// memo.list_all → memo.xml 의 namespace.id
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("search_option", search_option);
			map.put("search", search);
			items = session.selectList("memo.list", map);
		}
		return items;
	}

	
	public void insert(Map<String, Object> map) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String memo = (String) map.get("memo");
		memo = memo.replace("<", "&lt;");
		memo = memo.replace(">", "&gt;");
		memo = memo.replace("  ", "&nbsp;&nbsp;");
		map.put("memo", memo);
		session.insert("memo.insert", map);
		session.commit();
		session.close();
	}

	
	public Map<String, Object> view(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> map = session.selectOne("memo.view", idx);
		session.close();
		return map;
	}

	
	public void update(Map<String, Object> map) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("memo.update", map);
		session.commit();
		session.close();
	}

	public void delete(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("memo.delete", idx);
		session.commit();
		session.close();
	}
	
	
}
