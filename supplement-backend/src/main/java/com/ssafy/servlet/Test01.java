/**
 * 서블릿
 * - .java : 자바클래스
 * - Servlet 인터페이스 타입이 되어야 한다.
 *
 * - Servlet 인터페이스를 상속한 자식 클래스를 상속(GenericServlet(추상), HttpServlet(추상))
 *   public class MyClass implements Servlet
 *   public class MyClass extends GenericServlet
 *   public class MyClass extends HttpServlet
 *   
 *   Servlet 
 *   <- GenericServlet(Servlet 인터페이스의 대다수의 메서드를 오버라이딩) 
 *   <- HttpServlet(Http 프로토콜과 연관된 메서드가 정의되어 있음)
 */
package com.ssafy.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Test01 implements Servlet{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}













