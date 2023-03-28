package com.ssafy.todo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.todo.model.Todo;
import com.ssafy.todo.model.dao.TodoDao;
import com.ssafy.todo.model.dao.TodoDaoImpl;

//목록, 등록, 특정할 일 삭제, 전체삭제 => 액션 파라메터 값을 통해 일을 구분함
// /todo>action = list
// /todo>action = regist
// /todo>action = delete
// /todo>action = clear
@WebServlet("/todo")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoDao todoDao;
	
	public void init() {
		todoDao = TodoDaoImpl.getInstance();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// todo
		// action = regist
		String action = request.getParameter("action");
		if (action == null || action.equals("")) action = "list";
		
		String path = "/todo/index.jsp";
		if("list".equals(action)) {				// 목록
			path = list(request,response);
			forward(request, response, path);
		} else if ("regist".equals(action)) {	// 등록
			path = regist(request, response);
			redirect(request, response, path);
		} else if ("delete".equals(action)) {	// 선택삭제
			path = delete(request, response);
			redirect(request, response, path);
		} else if ("clear".equals(action)) {	// 전체삭제
			path = clear(request, response);
			redirect(request, response, path);
		} 
	}

	private String list(HttpServletRequest request, HttpServletResponse response)  {
		try {
			// 목록 데이터 가져오기
			List<Todo> list = todoDao.selectTodo();
			/*
			 * 페이지의 4 가지 공유 영역
			 * pageContext	- 자기 자신만 사용
			 * request		- 한번의 요청에 대한 응답을 하기까지 호출되는 페이지에서 공유
			 * session		- 로그인과 같은 브라우저 단위(사용자)
			 * application	- 웹프로젝트 단위
			 */
			// 이동되는 JSP페이지에서 사용하도록 설정해야한다.(공유)
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/todo/index.jsp";
	}
	
	private String regist(HttpServletRequest request, HttpServletResponse response)  {
		try {
			// 등록 처리하기
			Todo todo = new Todo();
			todo.setContent(request.getParameter("content") );
			todoDao.insertTodo(todo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/todo?action=list";
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response)  {
		try {
			// 삭제 처리하기
			todoDao.deleteTodo(Integer.parseInt(request.getParameter("no")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/todo?action=list";
	}
	
	private String clear(HttpServletRequest request, HttpServletResponse response)  {
		try {
			// 전체 삭제하기
			todoDao.clearTodo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/todo?action=list";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}	
}







