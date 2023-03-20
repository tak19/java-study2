/**
 * HttpServlet 클래스 구현
 * 
 * service(ServletRequest, ServletResponse) 메서드 재정의
 * service(HttpServletRequest, HttpServletResponse) 메서드 재정의
 * doGet(HttpServletRequest, HttpServletResponse) 메서드 재정의
 * doPost(HttpServletRequest, HttpServletResponse) 메서드 재정의
 * 
 * 위의 메서드 중에서 사용자 요청에 따라 재정의 결정
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/test03")
public class Test03 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Get요청 잘 들어옴");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Post요청 잘 들어옴");
	}
	
}














