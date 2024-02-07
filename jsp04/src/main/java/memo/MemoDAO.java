package memo;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import config.HibernateManager;

public class MemoDAO {
	public List<MemoDTO> listMemo(String searchkey, String search) {
		Session session = HibernateManager.getFactory().openSession();
		String sql = "select idx, writer, memo, post_date from memo";
		if (searchkey.equals("writer_memo")) {
			sql += " where writer like :search or memo like :search";
			//					파라미터 → :변수	
		} else {
			sql += " where " + searchkey + " like :search";
		}
		sql += " order by idx desc";
		List list = session.createNativeQuery(sql, MemoDTO.class).setParameter("search", "%" + search + "%").getResultList();
		//					native query → sql 직접 실행
		session.close();
		return list;
	}

	public void deleteMemo(int idx) {
		Session session = HibernateManager.getFactory().openSession();
		session.beginTransaction();
		MemoDTO dto = new MemoDTO();
		dto.setIdx(idx);
		session.delete(dto);
		session.getTransaction().commit();
		session.close();
		
		/* Hibernate: delete from memo where idx=? 
		   Hibernate: select idx,writer,memo,post_date from memo where writer like ? order by idx desc
		 */
	}

	public void insertMemo(MemoDTO dto) {
		Session session = HibernateManager.getFactory().openSession();
		// hibernate 실행객체         SessionFactory

		// 트랜잭션 시작(insert, update, delete 쿼리)
		session.beginTransaction();
		session.save(dto); // insert쿼리 생성
		session.getTransaction().commit(); // 트랜잭션 완료
		session.close();
		
		/* Hibernate: select hibernate_sequence.nextval from dual 
		   Hibernate: insert into memo (memo, post_date, writer, idx) values (?, ?, ?, ?) 
		   Hibernate: selectidx,writer,memo,post_date from memo where writer like ? order by idx desc
		 */
	}

	public void updateMemo(MemoDTO dto) {
		Session session = HibernateManager.getFactory().openSession();
		session.beginTransaction();
		dto.setPost_date(new Date());
		//	post_date     현재 날짜
		session.update(dto);
		session.getTransaction().commit();
		session.close();
		
		/* Hibernate: update memo set memo=?, post_date=?, writer=? where idx=?
		   Hibernate: select idx,writer,memo,post_date from memo where writer like ? order by idx desc
		 */ 
	}

	public MemoDTO viewMemo(int idx) {
		Session session = HibernateManager.getFactory().openSession();
		MemoDTO dto = (MemoDTO) session.get(MemoDTO.class, idx);
		session.close();
		return dto;
		
		/*
		Hibernate: 
			select memodto0_.idx as idx1_0_0_, 
				   memodto0_.memo as memo2_0_0_,
				   memodto0_.post_date as post_date3_0_0_, 
				   memodto0_.writer as writer4_0_0_ 
			from   memo memodto0_ 
			where  memodto0_.idx=?
		 */
	}
}
