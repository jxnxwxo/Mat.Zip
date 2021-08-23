package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.dao.LoginDAO;
import com.test.service.LoginService;
import com.test.vo.TestVO;

@Component
@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDAO loginDAO;
	
	@Override
	public String loginCheck(TestVO testVO) throws Exception {
		
		return (String) loginDAO.loginCheck(testVO);
	}
	
}
