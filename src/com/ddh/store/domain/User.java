package com.ddh.store.domain;


public class User {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String uid, String password, String name, String email, String telephone,
			 String sex, String code, int state) {
		super();
		this.username = username;
		this.uid = uid;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.sex = sex;
		this.code = code;
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", uid=" + uid + ", password=" + password + ", name=" + name + ", email="
				+ email + ", telephone=" + telephone + ", sex=" + sex + ", code=" + code
				+ ", state=" + state + "]";
	}

	private String username;
	private String uid;
	private String password;
	private String name;
	private String email;
	private String telephone;
	/*private Date birthday;*/
	private String sex;
	private String code;
	private int state;

}
