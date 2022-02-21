package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	// 인터페이스니까 인터페이스 안의 메서드를 모두 구현해야돼. 오버라이드

	// 필터의 자원정리코드
	@Override
	public void destroy() { 
		Filter.super.destroy();
	}

	//필터가 실제 구현할 내용: 주로 인코딩필터
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		chain.doFilter(request, response); // 뒤쪽에 연결된 필터에 요청과 응답 전달하기 위해
		 // 필터는 여러개 낄 수 있음. 그래서 chain을 작성해준것이고
		//응답 시 적용할 필터코드는 이 줄 밑에 써주면 됨
		
	}

	//필터의 초기화코드
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	} 

}
