<%@page import="vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 경로
	String path = request.getContextPath();
	// 로그인 정보
	UserVO user = (UserVO) session.getAttribute("randomtour-user");
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<%=path%>/js/app.js"></script>
<title>Random Tour</title>
<% if (user != null) { %>
	<script>
		setTimeout(() => {
			new App().alert("primary", "세션이 만료되어 로그아웃됩니다.");
			setTimeout(() => {
				location.href = "<%=path%>/user/logout";
			}, 1000);
		}, <%=session.getMaxInactiveInterval() * 1000 - 60000 %>);
	</script>
<% } %>

<script>
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">