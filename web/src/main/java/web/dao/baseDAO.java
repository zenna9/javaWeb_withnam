package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baseDAO {
	// 접속 메서드
	// 상속받아서 만들거기 때문에
	protected Connection getConnection() throws SQLException {
		// 겟커넥션 메서드를 수행하면 커넥션을 얻어내야 함
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// +커넥션 객체 만들기
			String dburl = "jdbc:mysql://localhost:3306/myhome?serverTimezone=UTC&useSSL=false";
			conn = DriverManager.getConnection(dburl, "bituser", "bituser");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실ㅍㅐ!");
		}
		return conn;
	}
}
