package cn.itcast.bookstore.domain;

import java.util.Date;

public class User {
	private String id;
	private String username;
	private String password;
	private String gender;
	private String email;
	private String telephone;
	private int state;//状态；0-未激活，1-激活
	private String introduce;//概述
	private String activecode;
	private String role;//普通用户。超级用户
	private Date regisTime;//注册时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegisTime() {
		return regisTime;
	}
	public void setRegisTime(Date regisTime) {
		this.regisTime = regisTime;
	}
	public static void main(String[]args){
		System.out.println(new User());
		
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", gender=" + gender + ", email=" + email
				+ ", telephone=" + telephone + ", state=" + state + ", introduce=" + introduce + ", activecode="
				+ activecode + ", role=" + role + ", regisTime=" + regisTime + "]";
	}
	
	
}
