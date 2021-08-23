package com.project.dao;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.vo.CustomerVO;

@Repository
public class CustomerDAO {

	@Autowired
	SqlSession sqlSession;
	
	public void customerInsert(CustomerVO vo) {
		System.out.println(vo);
		sqlSession.insert("mapper.customer.customerInsert", vo);
	}

	public String customerLogin(CustomerVO vo) {
		return sqlSession.selectOne("mapper.customer.customerLogin", vo);
	}

	public int idCheck(CustomerVO vo) {
		return sqlSession.selectOne("mapper.customer.idCheck", vo);
	}
	
	public String findId(String email) throws Exception{
		return sqlSession.selectOne("mapper.customer.findId", email);
	}

	public int updatePw(CustomerVO vo) throws Exception{
		return sqlSession.update("mapper.customer.updatePw", vo);
	}

	public int customerDelete(CustomerVO vo) throws Exception{
		return sqlSession.delete("mapper.customer.customerDelete", vo);
	}

	public CustomerVO getMember(String id) {
		return sqlSession.selectOne("mapper.customer.getMember", id);
	}

	public String pwcheck(CustomerVO vo) {
		return sqlSession.selectOne("mapper.customer.pwcheck", vo);
	}

	public int editPw(CustomerVO vo) {
		return sqlSession.update("mapper.customer.editPw", vo);
	}

}
