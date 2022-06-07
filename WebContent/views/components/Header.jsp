<%@page import="vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 경로
	String path = request.getContextPath();
	// 로그인 정보
	UserVO user = (UserVO) session.getAttribute("user");
	// 알림창
	String alert = (String) request.getAttribute("alert");
	String success = (String) request.getAttribute("success");
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
<script src="<%=path%>/js/alert.js"></script>
<title>Random Tour</title>
</head>

<body>