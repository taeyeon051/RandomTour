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

<form action="<%=path%>/user/join" method="post" id="join-page" class="user-page p-5 text-center border-gray position-relative">
	<h4 class="text-center mb-4 fw-bolder">회원가입</h4>
	<a href="<%=path%>/user/login" class="position-absolute fs-3" style="top: 1.2rem; left: 1.5rem;">
		<i class="bi bi-arrow-left"></i>
	</a>
	<div id="user-id-box" class="form-floating mb-3">
    	<input type="text" id="user-id" name="user-id" class="form-control" placeholder=" ">
   		<label for="user-id">아이디</label>
    	<button type="button" id="certify-btn" class="btn btn-skyblue">인증하기</button>
	</div>
	<div class="form-floating mb-3">
    	<input type="text" id="certify-number" name="certify-number" class="form-control" placeholder=" " disabled>
   		<label for="certify-number">인증번호</label>
	</div>
	<div class="row g-2">
		<div class="col-md">
			<jsp:include page="./components/Input.jsp">
				<jsp:param name="id" value="user-name" />
				<jsp:param name="type" value="text" />
				<jsp:param name="label" value="이름" />		
			</jsp:include>
		</div>
		<div class="col-md">
			<jsp:include page="./components/Input.jsp">
				<jsp:param name="id" value="user-nickname" />
				<jsp:param name="type" value="text" />
				<jsp:param name="label" value="닉네임" />
			</jsp:include>
		</div>
	</div>
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
</form>

<script src="<%=path%>/js/user/join.js"></script>

<%@ include file="./components/Footer.jsp" %>