package com.java.member.dao;

import java.util.HashMap;
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
	public HashMap<String,Object> loginOk(String id, String password) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("password", password);
		HashMap<String,Object> result = sqlSessionTemplate.selectOne("member_login", map);
		return result;
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

}
