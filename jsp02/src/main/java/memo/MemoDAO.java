package memo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DB;
import oracle.jdbc.OracleTypes;

public class MemoDAO {
	public List<MemoDTO> list_memo() {
		List<MemoDTO> items = new ArrayList<MemoDTO>();
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "{call memo_list_p(?)}";
			cstmt = conn.prepareCall(sql); // 프로시저 실행객체 생성
			cstmt.registerOutParameter(1, OracleTypes.CURSOR); // 출력매개변수의 자료형 지정
			cstmt.execute(); // 프로시저 호출
			rs = (ResultSet) cstmt.getObject(1);
			// (ResultSet) ← 커서 → 레코드집합 주소값
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String writer = rs.getString("writer");
				String memo = rs.getString("memo");
				Date post_date = rs.getDate("post_date");
				String ip = rs.getString("ip");
				MemoDTO dto = new MemoDTO(idx, writer, memo, post_date, ip);
				items.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public void insert_memo(MemoDTO dto) {
		Connection conn = null;
		CallableStatement cstmt = null; // 프로시저 실행

		try {
			conn = DB.dbConn();
			String sql = "{call memo_insert_p(?,?,?)}";
			// {call 프로시저(전달값)}, ? → 1,2,3
			cstmt = conn.prepareCall(sql); // 프로시저 실행객체 생성
			cstmt.setString(1, dto.getWriter());
			cstmt.setString(2, dto.getMemo());
			cstmt.setString(3, dto.getIp());
			cstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
