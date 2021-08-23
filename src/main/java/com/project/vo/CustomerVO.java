package com.project.vo;


public class CustomerVO {

	private int custNum;
	private String id, pw, nickname;
	private int birth;
	private String email;
	private String msg;
	private int dice;

	public int getCustNum() {
		return custNum;
	}
	public void setCustNum(int custNum) {
		this.custNum = custNum;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public int getDice() {
		return dice;
	}
	public void setDice(int dice) {
		this.dice = dice;
	}
	public CustomerVO() {
		super();
		this.id = id;
		this.pw = pw;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "CustomerVO [custNum=" + custNum + ", id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", birth="
				+ birth + ", email=" + email + ", msg=" + msg + ", dice=" + dice + "]";
	}
}
