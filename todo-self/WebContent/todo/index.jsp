<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ssafy.todo.model.Todo"%>
<%
	String root = request.getContextPath();
	List<Todo> list = (List<Todo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
<meta charset="utf-8">
<title>TodoMVC</title>
<link rel="stylesheet" href="<%= root %>/css/todo/base.css">
<link rel="stylesheet" href="<%= root %>/css/todo/index.css">
<link rel="stylesheet" href="<%= root %>/css/todo/app.css">
</head>
<body>
	<section class="todoapp">
		<header class="header">
			<h1>오늘의 할일</h1>
			<form action="<%= root %>/todo" method="post">
				<input type="hidden" name="action" value="regist" /> <input
					type="text" class="new-todo" placeholder="오늘의 할일을 적으세요?"
					name="content" autofocus>
			</form>
		</header>
		<section class="main">
			<ul class="todo-list">
				<%
					for( Todo todo : list){
				%>
				<li>
					<div class="view">
						<label><%= todo.getContent() %></label> <a
							href="<%=root %>/todo?action=delete&no=<%= todo.getNo() %>"
							class="destroy"></a>
					</div>
				</li>
				<%
					}
				%>
			</ul>
		</section>
		<footer class="footer">
			<span class="todo-count">전체 <strong><%= list.size() %></strong>개
			</span> <a href="<%=root %>/todo?action=clear" class="clear-completed">전체
				지우기</a>
		</footer>
	</section>
</body>
</body>
</html>