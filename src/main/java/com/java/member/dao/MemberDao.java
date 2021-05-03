package com.java.member.dao;

import com.java.member.dto.MemberDto;

public interface MemberDao {
	public int memberinsert(MemberDto memberDto);
	
	public int memberidCheck(String id);

	public String loginOk(String id, String password);

	public MemberDto memberUpdate(String id);
	
	public int memberUpdateOk(MemberDto memberDto);
	
	public int memberDelete(String id, String password);
}
