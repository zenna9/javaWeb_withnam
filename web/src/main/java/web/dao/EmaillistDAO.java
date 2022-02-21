package web.dao;

import java.util.List;

public interface EmaillistDAO {
	//목록 리스트 메서드
	public List<EmailVo> getList() ; //getList는 목록불러오기
	//새 아이템 추가 메서드
	public int insert(EmailVo vo);
	
	public int delete(Long no);
	
}
