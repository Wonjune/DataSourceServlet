package spms.vo;

public class Member {
	protected int no;
	protected String name;
	protected String email;
	protected String password;
	protected String createDate;
	protected String modifiedDate;
	public int getNo() {
		return no;
	}
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getCreateDate() {
		return createDate;
	}
	public Member setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	
	
}
