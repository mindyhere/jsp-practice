package emp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DB;

public class EmpSalaryDAO {
	public List<EmpDTO> list_emp() {
		List<EmpDTO> items = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DB.dbConn();
			String sql = "select * from emp order by ename";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setHiredate(rs.getDate("hiredate"));
				dto.setSal(rs.getInt("sal"));
				items.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return items;
	}

	public void update_sal(int empno) {
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = DB.dbConn();
			String sql = "{call sal_p(?)}";
			// {call 프로시저이름(전달값)} 
			cstmt = conn.prepareCall(sql); //저장프로시저 실행객체
			cstmt.setInt(1, empno);	// → ?에 매개변수(empno) 대입
			cstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
