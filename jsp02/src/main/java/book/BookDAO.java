package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DB;

public class BookDAO {
	public List<BookDTO> list_book() {
		List<BookDTO> items = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			// context.xml dbcp1개 리턴
			String sql = "select * from books";
			pstmt = conn.prepareStatement(sql); // sql실행 객체 생성
			rs = pstmt.executeQuery(); // sql 실행

			while (rs.next()) {
				BookDTO dto = new BookDTO(); // 레코드 1개 → dto에 저장. mapper
				dto.setIdx(rs.getInt("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPrice(rs.getInt("price"));
				dto.setAmount(rs.getInt("amount"));
				items.add(dto); // 리스트에 dto추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return items;
	}
}
