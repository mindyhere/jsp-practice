package guestbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class GuestbookDAO {
	public List<GuestbookDTO> list(String searchkey, String search) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestbookDTO> list = null;

		if (searchkey.equals("name_contents")) {
			list = session.selectList("guestbook.list_all", search);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			list = session.selectList("guestbook.list", map);
		}
		session.close();
		return list;
	}

	public void insert(GuestbookDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String contents = dto.getContents();
		contents = contents.replace("<", "&lt;");
		contents = contents.replace(">", "&gt;");
		contents = contents.replace("\n", "<br>;");
		contents = contents.replace("  ", "&nbsp&nbsp;");
		dto.setContents(contents);
		session.insert("guestbook.insert", dto);
		session.commit();
		session.close();
	}

	public boolean check_pwd(int idx, String passwd) {
		boolean result = false;
		SqlSession session = MybatisManager.getInstance().openSession();
		GuestbookDTO dto = new GuestbookDTO();
		dto.setIdx(idx);
		dto.setPasswd(passwd);
		int count = session.selectOne("guestbook.check_pwd", dto);
		result = count == 1 ? true : false;
		session.close();
		return result;
	}

	public GuestbookDTO detail(int idx) {
		GuestbookDTO dto = new GuestbookDTO();
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("guestbook.detail", idx);
		String contents = dto.getContents();
		contents = contents.replace("<br>", "\n");
		dto.setContents(contents);
		session.close();
		return dto;
	}

	public void update(GuestbookDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String contents = dto.getContents();
		contents = contents.replace("<", "&lt;");
		contents = contents.replace(">", "&gt;");
		contents = contents.replace("\n", "<br>;");
		contents = contents.replace("  ", "&nbsp&nbsp;");
		dto.setContents(contents);
		session.update("guestbook.update", dto);
		session.commit();
		session.close();
	}

	public void delete(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("guestbook.delete", idx);
		session.commit();
		session.close();
	}
}
