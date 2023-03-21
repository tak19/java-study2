/*
 * 파라미터 처리
 * : radio, checkbox 
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/test07")
public class Test07 extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//http://localhost:8081/supplement/servlet/test07?msg=%E3%85%81&sms=Y&dinner=1&dinner=2&dinner=3
		String msg = request.getParameter("msg");
		String sms = request.getParameter("sms");
		//String dinner = request.getParameter("dinner"); // 1 첫번째 항목만 가져온다..
		String[] dinner = request.getParameterValues("dinner"); // 1 첫번째 항목만 가져온다..
		System.out.println("msh: " + msg);
		System.out.println("sms: " + sms);
		
		if( dinner != null ) {
			for( String d : dinner ) {
				System.out.println(d);
			}
		}else {
			System.out.println("사용자가 선택하지 않음");
		}
	}
}













