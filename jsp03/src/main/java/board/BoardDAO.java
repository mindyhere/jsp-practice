package board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class BoardDAO {
//	public int count(String search_option, String keyword) {
//		int result=0;
//		SqlSession session=MybatisManager.getInstance().openSession();
//		
//		try {
//			Map<String , Object> map=new HashMap<String, Object>();
//			map.put("search_option",search_option);
//			map.put(keyword, map)
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			
//		}
//	}

	public List<BoardDTO> list() {
		List<BoardDTO> list = null;
		SqlSession session = null;

		try {
			session = MybatisManager.getInstance().openSession();
			list = session.selectList("board.list");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public void insert(BoardDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("board.insert", dto);
		session.commit();
		session.close();
	}

	public String getFilename(int num) {
		String result = null;
		SqlSession session = null;

		try {
			session = MybatisManager.getInstance().openSession();
			result = session.selectOne("board.filename", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

	// 선택한 게시글 → 다운로드 횟수를 증가시키는 함수	
	public void plus_down(int num) {
		SqlSession session = null;

		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.plus_down", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
}
