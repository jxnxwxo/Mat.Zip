package com.project.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.CustomerDAO;
import com.project.vo.CustomerVO;

@Service
public class CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	@Transactional
	public void customerInsert(CustomerVO vo) throws Exception{
		customerDAO.customerInsert(vo);
	}


	public String login(CustomerVO vo) {
		return customerDAO.customerLogin(vo);
	}


	public int idCheck(CustomerVO vo) throws Exception {
		return customerDAO.idCheck(vo);
	}


	public String findId(String email) throws Exception {
		return customerDAO.findId(email);
	}


	public int updatePw(CustomerVO vo) throws Exception {
		return customerDAO.updatePw(vo);
	}

	public int customerDelete(CustomerVO vo) throws Exception {
		return customerDAO.customerDelete(vo);
	}


	public CustomerVO getMember(String id) {
		return customerDAO.getMember(id);
	}


	public String pwcheck(CustomerVO vo) {
		return customerDAO.pwcheck(vo);
	}


	public int editPw(CustomerVO vo) {
		return customerDAO.editPw(vo);
	}
	
	}
