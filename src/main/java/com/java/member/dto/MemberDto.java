package com.java.member.dto;

import java.sql.Date;

public class MemberDto {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String jumin1;
	private String jumin2;
	private String phone;
	private String email;
	private String domain;
	private int memberLevel;
	private int reportCount ;
	private Date blockDate;
	private Date registerDate;
	private Date delDate;
	private String userKey;
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String id, String password, String name, String gender, String jumin1, String jumin2, String phone,
			String email, String domain, int memberLevel, int reportCount, Date blockDate, Date registerDate,
			Date delDate, String userKey) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.jumin1 = jumin1;
		this.jumin2 = jumin2;
		this.phone = phone;
		this.email = email;
		this.domain = domain;
		this.memberLevel = memberLevel;
		this.reportCount = reportCount;
		this.blockDate = blockDate;
		this.registerDate = registerDate;
		this.delDate = delDate;
		this.userKey = userKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJumin1() {
		return jumin1;
	}

	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}

	public String getJumin2() {
		return jumin2;
	}

	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public Date getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", jumin1="
				+ jumin1 + ", jumin2=" + jumin2 + ", phone=" + phone + ", email=" + email + ", domain=" + domain
				+ ", memberLevel=" + memberLevel + ", reportCount=" + reportCount + ", blockDate=" + blockDate
				+ ", registerDate=" + registerDate + ", delDate=" + delDate + ", userKey=" + userKey + "]";
	}
	
	
	
}
