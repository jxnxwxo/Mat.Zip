package com.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.test.vo.TestVO;

@Component
@Repository("loginDAO")
public class LoginDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "test.";
	
	public String loginCheck(TestVO testVO) {
		
		return (String) sqlSession.selectOne(NAMESPACE + "loginCheck", testVO);
	}
}
