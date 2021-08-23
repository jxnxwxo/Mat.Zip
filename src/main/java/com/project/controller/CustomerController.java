package com.project.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.CustomerService;
import com.project.vo.CustomerVO;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	JavaMailSender mailSender;

	// 회원 탈퇴
	@RequestMapping(value = "/customerDelete", method = RequestMethod.POST)
	public int customerDelete(CustomerVO vo) throws Exception {
		System.out.println("회원탈퇴진입");
		int deleteflag = 0;
		
		if (vo.getPw().equals(customerService.pwcheck(vo))) {
			System.out.println("일치");
			customerService.customerDelete(vo);
			deleteflag++;
			}else {
				System.out.println("불일치");
				return 0;
			}
		return deleteflag;
	}
	
	// 비밀번호 찾기(임시비밀번호로 변경)
	@RequestMapping(value = "/updatePw", method = RequestMethod.POST)
	@ResponseBody
	public int updatePwPOST(CustomerVO vo) throws Exception {

		int checkValue = 0;
		String tempPw = "";
		StringBuffer sb = new StringBuffer();
		StringBuffer sc = new StringBuffer("!@#$%^&*-=?~");

		// 아이디, email 일치하는 회원 있는지 확인 -> 통과하면 임시비밀번호 만들고 메일 보냄
		if (customerService.idCheck(vo) == 0) {
			System.out.println("일치하는 회원정보 없음");
			return 0;
		} else {

			// 대문자 4개를 임의 발생
			sb.append((char) ((Math.random() * 26) + 65)); // 첫글자는 대문자, 첫글자부터 특수문자 나오면 안 이쁨
			for (int i = 0; i < 3; i++) {
				sb.append((char) ((Math.random() * 26) + 65)); // 아스키번호 65(A) 부터 26글자 중에서 택일
			}

			// 소문자 4개를 임의발생
			for (int i = 0; i < 4; i++) {
				sb.append((char) ((Math.random() * 26) + 97)); // 아스키번호 97(a) 부터 26글자 중에서 택일
			}

			// 숫자 2개를 임의 발생
			for (int i = 0; i < 2; i++) {
				sb.append((char) ((Math.random() * 10) + 48)); // 아스키번호 48(1) 부터 10글자 중에서 택일
			}

			// 특수문자를 두개 발생시켜 랜덤하게 중간에 끼워 넣는다
			sb.setCharAt(((int) (Math.random() * 3) + 1), sc.charAt((int) (Math.random() * sc.length() - 1))); // 대문자3개중
																												// 하나
			sb.setCharAt(((int) (Math.random() * 4) + 4), sc.charAt((int) (Math.random() * sc.length() - 1))); // 소문자4개중
																												// 하나

			tempPw = sb.toString();
			System.out.println("임시비밀번호 = " + tempPw);

			String setfrom = "mulcamf2@gmail.com";

			vo.setPw(tempPw);

			String title = "임시비밀번호 안내 메일입니다.";
			String content = System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성
					vo.getId() + " 님의 임시비밀번호는 " + tempPw + " 입니다. ";

			customerService.updatePw(vo);

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
				messageHelper.setTo(vo.getEmail()); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용

				mailSender.send(message);

			} catch (Exception e) {
				e.printStackTrace();
				checkValue = 0;
			}

			if (checkValue == 1) {
				return 1;
			} else {

			}

		}
		return 1;
	}

	// 아이디 찾기
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	@ResponseBody
	public String findIdPOST(String email, CustomerVO vo) throws Exception {

		return customerService.findId(email);

	}
	
	// 회원정보 수정을 위한 객체 가져오기
	@RequestMapping(value = "/getMember", method = RequestMethod.POST)
	public CustomerVO getMember(String id) {
		//System.out.println("id:" + id);
		
		return customerService.getMember(id);
	}
	
	// 회원정보 확인 (비밀번호확인)
	@RequestMapping(value = "/pwcheck", method = RequestMethod.POST)
	public String pwcheck(CustomerVO vo) {
		String pw1 = customerService.pwcheck(vo);
		// ==이안먹힐때 equals
		if(vo.getPw().equals(pw1)) {
			return "correct";
		}else {
			return "";
		}
	}
		
	// 회원정보 수정 (비밀번호변경)
	@RequestMapping(value = "/editPw", method = RequestMethod.POST)
	public int editPw(CustomerVO vo) {
		int a = 0;
		if(vo.getPw().equals(customerService.pwcheck(vo))) { 
			vo.setPw(vo.getMsg());
			a = customerService.editPw(vo);
		} 
		return a;
	}
		
		
	

	// 이메일 인증번호 보내기
	@PostMapping("/codesend")
	public int codesend(@ModelAttribute CustomerVO vo, HttpServletResponse response_email) throws IOException {

		int checkValue = 1;

		Random r = new Random();
		int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)

		String setfrom = "mulcamf2@gmail.com";
		String tomail = vo.getEmail(); // 받는 사람 이메일
		String title = "회원가입 인증 이메일 입니다."; // 제목
		String content =

				System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성
						System.getProperty("line.separator") + "안녕하세요. 맛.ZIP 홈페이지를 찾아주셔서 감사합니다"
						+ System.getProperty("line.separator") + System.getProperty("line.separator") + " 인증번호는 " + dice
						+ " 입니다. ";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			checkValue = 0;
		}

		if (checkValue == 1) {
			return dice;
		} else {
			return 0;
		}

	}

	// 이메일 인증 페이지 맵핑 메소드
	/*
	 * @RequestMapping("/member/email.do") public String email() { return
	 * "member/email"; }
	 */

	// 이메일로 받은 인증번호를 입력하고 전송 버튼을 누르면 맵핑되는 메소드.
	// 내가 입력한 인증번호와 메일로 입력한 인증번호가 맞는지 확인해서 맞으면 회원가입 페이지로 넘어가고,
	// 틀리면 다시 원래 페이지로 돌아오는 메소드

	/*
	 * @RequestMapping(value = "/join_injeung.do{dice}", method =
	 * RequestMethod.POST)
	 * 
	 * 
	 * @PostMapping("/verify") public String verify(@ModelAttribute CustomerVO vo)
	 * throws IOException {
	 * 
	 * System.out.println("유저 입력 인증번호 : " + vo.getDice());
	 * System.out.println("서버 인증번호  : " + verifyCode);
	 * 
	 * String
	 * 
	 * return null; }
	 */

	// 로그인
	@PostMapping("/login")

	public CustomerVO login(@ModelAttribute("info") CustomerVO vo, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		try {

			String nickname = customerService.login(vo);
			if (nickname != null) {
				session = request.getSession();
				session.setAttribute("customer", vo);
				vo.setNickname(nickname);

				System.out.println(vo);
			} else {
				vo = new CustomerVO();
			}
		} catch (Exception e) {
			vo = new CustomerVO();
		}
		return vo;
	}

	// 로그아웃
	@RequestMapping(value = "logout", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	
	public String logout(HttpServletRequest request,
			HttpServletResponse response){
			HttpSession session=request.getSession(false);
			session.invalidate();
			return "";
	}
	
	
	// 아이디 중복 검사
	@RequestMapping(value = "/customerIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String customerIdChkPOST(CustomerVO vo) throws Exception {

		int result = customerService.idCheck(vo);

		if (result != 0) {
			return "fail"; // 중복 아이디가 존재
		} else {
			return "success"; // 중복 아이디 x
		}

	} // customerIdChkPOST() 종료

	// 회원가입
	@PostMapping("/customerInsert")
	public String customerInsert(@ModelAttribute CustomerVO vo) {
		try {
			customerService.customerInsert(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			vo.setMsg("회원가입실패");
		}
		vo.setPw(null);
		return "ok";
	}
}
