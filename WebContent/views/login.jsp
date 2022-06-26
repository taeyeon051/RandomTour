<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<% if (user != null) { %>
	<script>
		location.href = "<%=path%>/main/roomList";
	</script>
<% } %>

<jsp:include page="./components/Background.jsp" />
<jsp:include page="./components/Logo.jsp" />

<form action="<%=path%>/user/login" method="post" id="login-page" class="user-page p-5 text-center border-gray">
	<h4 class="text-center mb-4 fw-bolder">로그인</h4>
	<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-id" />
		<jsp:param name="type" value="text" />
		<jsp:param name="label" value="아이디" />
	</jsp:include>
	<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-pwd" />
		<jsp:param name="type" value="password" />
		<jsp:param name="label" value="비밀번호" />
	</jsp:include>
	<jsp:include page="./components/Button.jsp">
		<jsp:param name="id" value="login-btn" />
		<jsp:param name="text" value="로그인" />
	</jsp:include>
	<div id="user-page-links" class="w-100 mt-4 d-flex justify-content-around">	
		<a href="<%=path%>/user/join">회원가입</a>
		<a href="<%=path%>/user/find">아이디찾기</a>
	</div>
</form>

<script src="<%=path%>/js/user/user.js"></script>
<script src="<%=path%>/js/user/login.js"></script>

<%@ include file="./components/Footer.jsp" %>