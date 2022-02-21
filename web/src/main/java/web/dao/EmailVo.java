package web.dao; // src/main/java

import java.util.Date;

// DTO
// 1.기본 생성자는 있어ㅑㅇ 한다
// 2. 필드 데이터는 은닉
// 3. getter와 setter로 우회 접근
// 4. DTO객체의 어떤 로직은 집어넣지 않음
// 5. toString, equals, hashCode같은 Object관련 메서드를 오버라이드 하기도 함
public class EmailVo {
	private Long no; //PK
	private String firstName;
	private String lastName;
	private String email;
	private Date createdAt;
	
	//1번 
	public EmailVo() {
	}
	//3번
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	//5번 toString 사용
	@Override
	public String toString() {
		return "Emaillist [no=" + no + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", createdAt=" + createdAt + "]";
	}
	


}
