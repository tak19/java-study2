/**
 * HttpServletResponse 클래스의 주요 메서드 
 * 
 * setContentType(브라우저에게 전송되는 문서의 타입을 알려준다.);
 * setContentType("text/html; charset=utf-8");
 * setCharacterEncoding("encoding");
 * 
 * 전송할 내용을 출력하기 위해서
 * PrintWriter getWriter();
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/test05")
public class Test05 extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
}











