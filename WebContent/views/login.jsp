<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<jsp:include page="./components/Background.jsp" />

<div id="user-page" class="p-5 text-center border-gray position-absolute top-50 start-50">
	<h4 class="text-center mb-4 fw-bolder">로그인</h4>
	<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-id" />
		<jsp:param name="type" value="email" />
		<jsp:param name="label" value="아이디" />
	</jsp:include>
	<jsp:include page="./components/Input.jsp">
		<jsp:param name="id" value="user-pwd" />
		<jsp:param name="type" value="password" />
		<jsp:param name="label" value="비밀번호" />
	</jsp:include>
	<jsp:include page="./components/Button.jsp">
		<jsp:param name="text" value="로그인" />
	</jsp:include>
	<div id="user-page-links" class="w-100 mt-4 d-flex justify-content-around">	
		<a href="<%=path%>/user/join">회원가입</a>
		<a href="<%=path%>/user/find/id">아이디 찾기</a>
	</div>
</div>
<%@ include file="./components/Footer.jsp" %>