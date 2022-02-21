package web.dao;

import java.util.List;

public interface GuestDAO {
	
	public int insert(GuestVo vo);
	// 방명록 쓰기 인터페이스
	
	public GuestVo getGuest(String name, String content);
	//방명록 가져오기
	
	public List<GuestVo> getList() ;
	//방명록 리스트 get
	
	public int delete(Long no, String password);
	
}
