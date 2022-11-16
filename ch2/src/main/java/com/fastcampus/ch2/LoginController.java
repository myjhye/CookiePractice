package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	// 로그인 폼
	@GetMapping("/login")
	public String loginForm()
	{
		return "loginForm";
	}
	
	
	// 로그인 처리
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response) throws Exception
	{
		System.out.println("id = " + id); // asdf
		System.out.println("pwd = " + pwd); // 1234
		System.out.println("rememberId = " + rememberId); // true/false
		
		
		// 1. id와 pwd를 확인
		if(!loginCheck(id, pwd))
		{
			// 2-1. id와 pwd와 일치하지 않으면, 에러 메세지와 loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg="+msg;
		}
		
		
		
		// 2-2. id와 pwd가 일치하면,
		
		if(rememberId) // rememberId가 true면
		{
			// 1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id);
			
			// 2. response에 저장
			response.addCookie(cookie);
			
		}
		else // false면
		{
			// 1. 쿠키 삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);
			
			// 2. response에 저장
			response.addCookie(cookie);
		}	
		
		
		// 3. 홈으로 이동
		return "redirect: /ch2";
	}


	// id && pwd
	private boolean loginCheck(String id, String pwd) {
		
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
