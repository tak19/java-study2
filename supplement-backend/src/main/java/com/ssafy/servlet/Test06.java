/*
 *  Request 객체의 주요 메서드
 *  - 파라미터 처리하기
 *  
 *  GET 방식과 POST 방식
 *  
 *  POST 방식일 경우 파라미터 한글 처리 필요
 *  - setCharacterEncoding
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/test06")
public class Test06 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = req.getParameter("msg");
		System.out.println("GET 호출 : " + msg);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String msg = req.getParameter("msg");
		System.out.println("POST 호출 : " + msg);
	}


}











