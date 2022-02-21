package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestDAOImpl extends baseDAO implements GuestDAO {

	@Override
	public int insert(GuestVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0; // 성공했다면 1이 될 것
		try {
			conn = getConnection();
			String sql = "INSERT INTO guestbook (name, password, content) " + "VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			count = pstmt.executeUpdate(); /// ????이게멀까

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}

	@Override
	public int delete(Long no, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0; // 성공했다면 1이 될 것
		try {
			conn = getConnection();
			String sql = "DELETE FROM guestbook WHERE no=? and password=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,no);
			pstmt.setString(2,password);
			
			count = pstmt.executeUpdate();
			System.out.println("삭제레코드 : "+count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}

	@Override
	public GuestVo getGuest(String name, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuestVo> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<GuestVo> list = new ArrayList();

		try {
			conn = getConnection();
			String sql = "SELECT no, name, content, reg_date " + "FROM guestbook ORDER BY reg_date";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				Date reg_date = rs.getDate("reg_date");

				GuestVo vo = new GuestVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setReg_date(reg_date);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

}
