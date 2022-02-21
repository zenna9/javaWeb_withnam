package web.dao; //

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmaillistDAOImpl extends baseDAO implements EmaillistDAO {

	@Override
	public List<EmailVo> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<EmailVo> list = new ArrayList();

		try {
			// 커넥션을 얻어오기 위해
			conn = getConnection();
			// SQL생성
			String sql = "SELECT no, first_name, last_name, email, created_at "
					+ "FROM emaillist ORDER BY created_at DESC";
			// statement만들어주기
			stmt = conn.createStatement();
			// 쿼리 실행하기
			rs = stmt.executeQuery(sql);
			// 목록만들기
			// 디저트셋을 만들면 커서가 생기니까(?)
			while (rs.next()) { // 남아있는 레코드 있는지 확인
				// 있으면 Emaillist객체를 생성한 후, list에 추가해줄것.
				Long no = rs.getLong("no");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				Date createdAt = rs.getDate("created_at");

				EmailVo vo = new EmailVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				vo.setCreatedAt(createdAt);

				list.add(vo);
			}
		} catch (SQLException e) { // 예외처리
			e.printStackTrace();
		} finally { // 자원정리
			try {
				rs.close();
				stmt.close();
				conn.close();

			} catch (Exception e) {
			}
		}
		return list;
	}
//========================하나만 표시 만드는 중======
//	@Override
//	public List<Emaillist> findList(Long no) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		List<Emaillist> list = new ArrayList();
//		int count =0;
//		try {
//			conn = getConnection();
//			String sql = "SELECT first_name, last_name, email "
//					+ "FROM emaillist "
//					+"WHERE no=?";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery(sql);
//			// 목록만들기
//			// 디저트셋을 만들면 커서가 생기니까(?)
//			while (rs.next()) { // 남아있는 레코드 있는지 확인
//				// 있으면 Emaillist객체를 생성한 후, list에 추가해줄것.
//				Long no = rs.getLong("no");
//				String firstName = rs.getString("first_name");
//				String lastName = rs.getString("last_name");
//				String email = rs.getString("email");
//				Date createdAt = rs.getDate("created_at");
//
//				Emaillist vo = new Emaillist();
//				vo.setNo(no);
//				vo.setFirstName(firstName);
//				vo.setLastName(lastName);
//				vo.setEmail(email);
//				vo.setCreatedAt(createdAt);
//
//				list.add(vo);
//			}
//		} catch (SQLException e) { // 예외처리
//			e.printStackTrace();
//		} finally { // 자원정리
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//
//			} catch (Exception e) {
//			}
//		}
//		return list;
//	}
//	}

	@Override
	public int insert(EmailVo vo) {
		Connection conn = null;
		// 동적 변경 쿼리를 위해 PreparedStatement 필요
		PreparedStatement pstmt = null;
		// 인서트, 업데이트, 딜리트 결과로 영향받은 레코드 카운트가 전달
		int count = 0; // 뭔가 하나를 성공적으로 삽입했다면, 1로 바뀔 변수
		try {
			// 커넥션 얻어오기
			conn = getConnection();
			// 실행 계획 수립
			String sql = "INSERT INTO emaillist (first_name, last_name, email) " 
			+ " VALUES(?, ?, ?)"; 
			// 동적으로 연결될 데이터는 물음표로
			// PreparedStatement
			pstmt = conn.prepareStatement(sql); // sql변수를 집어넣어 실행할 준비를 하는 것
			// 동적 연결
			pstmt.setString(1, vo.getFirstName()); // firstName 필드를 뽑아서 1번째 물음표에 집어넣음
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());

			// INSERT, UPDATE, DELETE의 경우 executeUpdate메서드로 수행
			// -> 결과로 영향받은 레코드의 카운트(정수)가 반환됨

			count = pstmt.executeUpdate();
			System.out.println("영향받은 레코드 수 : " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 자원정리
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return 0;
	}

	@Override
	public int delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0; 
		try {
			conn = getConnection();
			String sql = "DELETE FROM emaillist WHERE no=?"; 
		
			pstmt = conn.prepareStatement(sql); 
			// 동적 연결
			pstmt.setLong(1, no);
			
			//실행
			count = pstmt.executeUpdate();
			System.out.println("삭제된 레코드 수 : " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 자원정리
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}
}
