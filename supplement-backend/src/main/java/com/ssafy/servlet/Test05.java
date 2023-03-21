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
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/test05")
public class Test05 extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setContentType("text/plain"); //내가 지금 보내는 내용의 가이드를 보냄 --> 현재의 경우 일반 텍스트
		//res.setContentType("text/html; charset=utf-8"); //내가 지금 보내는 내용의 가이드를 보냄 --> h2태그 인식 위해
		res.setContentType("text/xml; charset=utf-8");
		//문자를 전송할때
		PrintWriter out = res.getWriter();
		//out.println("test05 call");
		//out.println("<h2>test05 call</h2>");
		out.println("<family><father>심슨</father><mother>더글로리</mother></family>");
		out.close();
		
		//바이트 전송할때
		res.getOutputStream();
	}
}











