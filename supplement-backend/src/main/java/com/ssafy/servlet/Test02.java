/**
 * GenericServlet 클래스 구현
 * 
 * service(ServletRequest, ServletResponse) 메서드 재정의
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

//http://서버주소:포트번호/프로젝트Path + WebServlet에 설정(/Servlet/test02)
//http://localhost:8081/supplement/servket/test02
@WebServlet("/servlet/test02")
public class Test02 extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service 메서드 호출");
		
	}

}