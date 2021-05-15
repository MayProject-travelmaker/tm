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

	// 회원정보 수정 화면
	@Override
	public MemberDto memberUpdate(String id) {
		return sqlSessionTemplate.selectOne("member_select",id);
	}
	
	// 회원정보 수정 완료
	@Override
	public int memberUpdateOk(MemberDto memberDto) {
		return sqlSessionTemplate.update("member_update",memberDto);
	}
	
	// 회원탈퇴
	@Override
	public int memberDelete(String id, String password) {
		return sqlSessionTemplate.update("member_delete", id);
	}

	// 아이디 찾기
	@Override
	public String memberFindId(String name, String phone) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("phone", phone);
		return sqlSessionTemplate.selectOne("member_findId", map);
	}
	// 비밀번호 찾기 - 아이디, 이메일 찾기
	@Override
	public int checkIdAndEmail(String id, String email) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("email", email);
		return sqlSessionTemplate.selectOne("checkIdandEmail", map);
	}

	// 비밀번호 찾기 - 인증키 저장
	@Override
	public int updateAuthKey(String id, String email, String authKey) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("email", email);
		map.put("authKey", authKey);
		return sqlSessionTemplate.update("updateAuthkey", map);
	}
	// 비밀번호 찾기 - 비밀번호 변경
	@Override
	public int changePassword(String id, String password, String authKey) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("password", password);
		map.put("authKey", authKey);
		
		return sqlSessionTemplate.update("changePassword", map);
	}

}
