package common;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DB {
	public static Connection dbConn() {
		DataSource ds = null;
		Connection conn = null; // DB접속
		try {
			Context ctx = new InitialContext();
			// context.xml 분석기
			ds = (DataSource) ctx.lookup("java:comp/env/oraDB"); // java:comp/env/리소스태그이름
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
