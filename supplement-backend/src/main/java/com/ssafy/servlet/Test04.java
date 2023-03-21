/**
 * Servlet LifeCycle
 * 
 * - init(ServletConfig) : 서블릿이 메모리에 생성될 때 최초 한번만 호출됨
 * - service(ServletRequest, ServletResponse) : 사용자 요청시마다 호출
 * - destroy() : 서블릿이 메모리에서 해제될 때 자동 호출
 * 
 * - 서블릿은 서버에 하나의 객체만 생성된다.(싱글톤)
 * 
 * 사용자의 서블릿 호출에 따른 진행
 * 최초 호출시
 * - 서버는 호출한 서블릿이 메모리에 존재하는 확인하고
 * - 만약, 존재하지 않는다면 서블릿 객체를 생성(new)한다.
 * - 생성 후 init(ServletConfig) 메서드 호출
 * - service 메서드를 호출 후 결과를 사용자에게 전송
 * 
 * 재 호출시
 * - 생성된 객체를 찾아서 service 메서드 호출 후 결과를 사용자에게 전송
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/servlet/test04")
public class Test04 extends HttpServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("실제 작업 진행하는 메서드");
	}

	@Override
	public void destroy() {
		System.out.println("서블릿 객체 메모리 해제될때 호출");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("최초 한번만 실행");
	}
	
}














