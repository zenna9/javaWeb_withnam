// web/src/main/java - web.dao → web.dao
package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAOImpl extends baseDAO implements UsersDAO {

	@Override
	public int insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO users (name, password, email, gender)" + "VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGender());

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
		return count;
	}

	@Override // 로그인
	public UserVo getUser(String email, String password) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo vo = null;
		try {
			//커넥션
			conn=getConnection();
			String sql = "SELECT no, name, email, gender FROM users "+
			   "WHERE email=? AND password=?"; // 실행계획
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			//쿼리수행
			rs=pstmt.executeQuery();
			
			if (rs.next()) { // 뒤에 레코드가 있는지 확인
				Long no = rs.getLong(1); // 몇번째 컬럼에 있는 데이터를 가져올것인지가 ()안에 들어감
				String name = rs.getString(2);
				String gender = rs.getString(4);
				
				//UserVo 객체생성
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
			}
			System.out.println("사용자 찾음! "+vo);
		} // 이제 예외처리와 자원정리
		catch (SQLException e) {
			e.printStackTrace();
		}//이제 자원정리
		finally {
			try {
				if (rs !=null) rs.close();
				if (pstmt !=null) pstmt.close();
				if (conn !=null) conn.close();
			}catch (SQLException e) {}
		}
		return vo;
	}

}
