<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<jsp:include page="./components/Background.jsp" />
<jsp:include page="./components/Logo.jsp" />

<div id="join-page" class="user-page p-5 text-center border-gray position-absolute top-50 start-50">
	<h4 class="text-center mb-4 fw-bolder">회원가입</h4>
	<a href="<%=path%>/user/login" class="position-absolute fs-3" style="top: 1.2rem; left: 1.5rem;">
		<i class="bi bi-arrow-left"></i>
	</a>
	<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-id" />
		<jsp:param name="type" value="email" />
		<jsp:param name="label" value="아이디" />
	</jsp:include>
		<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-nickname" />
		<jsp:param name="type" value="text" />
		<jsp:param name="label" value="닉네임" />
	</jsp:include>
		<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-pwd" />
		<jsp:param name="type" value="password" />
		<jsp:param name="label" value="비밀번호" />
	</jsp:include>
	<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-pwdc" />
		<jsp:param name="type" value="password" />
		<jsp:param name="label" value="비밀번호 확인" />
	</jsp:include>
	<jsp:include page="./components/Button.jsp">
		<jsp:param name="id" value="join-btn" />
		<jsp:param name="text" value="회원가입" />
	</jsp:include>
</div>

<script src="<%=path%>/js/join.js"></script>

<%@ include file="./components/Footer.jsp" %>