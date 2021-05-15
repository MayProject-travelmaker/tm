package com.java.member.dao;

import java.util.HashMap;

import com.java.member.dto.MemberDto;

public interface MemberDao {
	public int memberinsert(MemberDto memberDto);
	
	public int memberidCheck(String id);

	public HashMap<String, Object> loginOk(String id, String password);

	public MemberDto memberUpdate(String id);
	
	public int memberUpdateOk(MemberDto memberDto);
	
	public int memberDelete(String id, String password);

	public String memberFindId(String name, String phone);

	public int checkIdAndEmail(String id, String email);

	public int updateAuthKey(String id, String email, String authKey);

	public int changePassword(String id, String password, String authKey);
}
