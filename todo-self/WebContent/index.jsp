<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  경로를 지정해 주는 것임 내가 경로 설정을 안했다면 프로젝트 이름으로 설정된다.
		  해당 부분을 하드 코딩하면 유연성이 떨어진다.
	 -->
	<a href="<%= request.getContextPath() %>/todo">오늘의 할일</a>
</body>
</html>