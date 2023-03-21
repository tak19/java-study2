/*
 * 요청 기타 메서드
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet/test08")
public class Test08 extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod(); // GET, POST
		//suppliement

		String contextPath = request.getContextPath(); //현재 실행중인 프로젝트 설정 Path경로
		//요청 경로 얻기 (supplement/servlet.test08)
		String uri = request.getRequestURI();	
		System.out.println("method:" + method);
		System.out.println("contextPath: " + contextPath);
		System.out.println("uri: " + uri);
	}
}