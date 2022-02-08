package com.spring.web.model;
import java.util.*;
public class UserVO {
	//여기서 이름(필드)짓고 나중에 jsp에서 파라미터이름을 같게 해주면 된다.
	private String userId;
	private String userPw;
	private String userName;
	private List<String> hobby;
	
	//기본생성자 만드는게 규약인데 지금은 다른 매개변수 생성자가 없어서 자동으로 만들어져 있는거임
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", hobby=" + hobby + "]";
	}
	
	
	
}
