package web.dao;

public interface UsersDAO {
	//가입 인터페이스
	public int insert(UserVo vo);
	
	//로그인 인터페이스
	public UserVo getUser(String email, String password);
	
	
}
