package com.java.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.member.dto.MemberDto;

@Component
public class MemberDaoImp implements MemberDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int memberinsert(MemberDto memberDto) {
		
		
		return sqlSessionTemplate.insert("member_insert", memberDto);
	}

	@Override
	public int memberidCheck(String id) {
		
		return sqlSessionTemplate.selectOne("id_doublecheck",id);
		
	}

	@Override
	public String loginOk(String id, String password) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("password", password);

		return sqlSessionTemplate.selectOne("member_login", map);
	}

	@Override
	public MemberDto memberUpdate(String id) {
		
		return sqlSessionTemplate.selectOne("member_select",id);
	}
	
	@Override
	public int memberUpdateOk(MemberDto memberDto) {
		return sqlSessionTemplate.update("member_update",memberDto);
	}
	
	
	@Override
	public int memberDelete(String id, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

//	회원조회
	@Override
	public List<MemberDto> memberList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("member_list");
	}

	//회원차단
	@Override
	public int  addBlack(String id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("addblack",id);
	}

	@Override
	public List<MemberDto> blackList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("blacklist_select");
	}

}
