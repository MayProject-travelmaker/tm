package com.java.member.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.member.dto.MemberDto;

public interface MemberDao {
	public int memberinsert(MemberDto memberDto);
	
	public int memberidCheck(String id);

	public String loginOk(String id, String password);

	public MemberDto memberUpdate(String id);
	
	public int memberUpdateOk(MemberDto memberDto);
	
	public int memberDelete(String id, String password);
	
	public List<MemberDto> memberList();

	//회원차단
	public int addBlack(String id);
	
	//차단 리스트 조회
	public List<MemberDto> blackList();
}
