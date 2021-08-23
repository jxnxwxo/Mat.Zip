package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.LoginService;
import com.test.vo.TestVO;

@Controller
@RequestMapping("/main/")
public class IndexComtroller {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("index")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		return mav;
	}
	
	@ResponseBody
	@PostMapping("loginCheck")
	public String loginCheck(TestVO testVO) throws Exception {
		
		System.out.println(testVO.toString());
		String name = loginService.loginCheck(testVO);
		return name + "님 login ok"; 
	}
}
