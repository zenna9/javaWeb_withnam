package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import web.dao.EmailVo;

public class JDBCTest {

	public static void main(String[] args) {

		Connection conn = null; // 커넥션 열기
		Statement stmt = null; // 스테이트먼트 열기
		ResultSet rs = null; // ...시스템 자원들을 열어준거야

		// 근데 이런건 할 떄 예외가 많이 발생
		try {
			// 첫번째 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// - 로드한 mysql connector 기준으로 jdbc.의 Driver클래스를 가져와라
			// +커넥션 객체 만들기
			String dburl = "jdbc:mysql://localhost:3306/myhome?serverTimezone=UTC&useSSL=false";
			conn = DriverManager.getConnection(dburl, "bituser", "bituser");
			// statement 객체생성
			stmt = conn.createStatement();
			String sql = "SELECT no, first_name, last_name, email," + "CREATED_at FROM emaillist ORDER BY created_at "
					+ "DESC";
			// 쿼리 전송
			rs = stmt.executeQuery(sql);
			// 제대로 실행되면 여기서 cursor가 생ㅇ성됨다고하넹..
			// ResultSet 생성
//			System.out.println(rs);

			while (rs.next()) { // 뒤에 레코드가ㅣ 있으면 커서를 이동
				Long no = rs.getLong("no"); // sql의 no필드는 long형태라서. rs.getLong(1)로 컬럼넘버로 뽑을수도 있음
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
//				String createdAt = rs.getString("created_at");
//
				Date createdAt = rs.getDate("created_at");
						
				EmailVo vo = new EmailVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				vo.setCreatedAt(rs.getDate("created_at"));
//				// 출력
				System.out.println(vo);
//				System.out.println(no + ":" + firstName + " " + lastName + ":" + email + "at" + createdAt);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("드라이브 로딩 실패!");
		} catch (Exception e) {
			System.err.println("쿼리 수행 실패");
			e.printStackTrace();
		} finally {
			// Connection 자원정리
			try {
				rs.close();
				stmt.close();
				conn.close(); // 위에 열어줬던 시스템들들 다 닫아주는것.
				// 안닫으면 커넥션이 부족해짐. 혹은 다른 곳에서 "파일 열려있어서 못열어"할수도있음
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
