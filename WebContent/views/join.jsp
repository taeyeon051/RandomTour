<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<% if (user != null) { %>
	<script>
		location.href = "<%=path%>/main/roomList";
	</script>
<% } %>

<jsp:include page="./components/Background.jsp"/>

<section>
	<jsp:include page="./components/Logo.jsp"/>

	<form action="<%=path%>/user/join" method="post" id="join-page" class="user-page p-5 text-center border-gray position-relative">
		<div class="d-flex justify-content-center align-items-center mb-4">
			<h4 class="fw-bolder">회원가입</h4>
			<i id="explanation-icon" class="bi bi-exclamation-circle ms-2" data-bs-toggle="modal" data-bs-target="#join-explanation"></i>
		</div>
		<a href="<%=path%>/user/login" class="position-absolute fs-3" style="top: 1.2rem; left: 1.5rem;">
			<i class="bi bi-arrow-left"></i>
		</a>
		<jsp:include page="./components/Input.jsp">
			<jsp:param name="id" value="user-id"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="아이디"/>
			<jsp:param name="button" value="certify"/>
		</jsp:include>
		<jsp:include page="./components/Input.jsp">
			<jsp:param name="id" value="certify-number"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="인증번호"/>
			<jsp:param name="disabled" value="true"/>
		</jsp:include>
		<div class="row g-2">
			<div class="col-md">
				<jsp:include page="./components/Input.jsp">
					<jsp:param name="id" value="user-name"/>
					<jsp:param name="type" value="text"/>
					<jsp:param name="label" value="이름"/>		
				</jsp:include>
			</div>
			<div class="col-md">
				<jsp:include page="./components/Input.jsp">
					<jsp:param name="id" value="user-nickname"/>
					<jsp:param name="type" value="text"/>
					<jsp:param name="label" value="닉네임"/>
				</jsp:include>
			</div>
		</div>
		<jsp:include page="./components/Input.jsp">
			<jsp:param name="id" value="user-pwd"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호"/>
		</jsp:include>
		<jsp:include page="./components/Input.jsp">
			<jsp:param name="id" value="user-pwdc"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호 확인"/>
		</jsp:include>
		<jsp:include page="./components/Button.jsp">
			<jsp:param name="id" value="join-btn"/>
			<jsp:param name="text" value="회원가입"/>
			<jsp:param name="margin" value="2"/>
		</jsp:include>
	</form>
</section>

<div class="modal fade" id="join-explanation" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content p-3">
			<div class="d-flex justify-content-end">
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
			    <p>* 아이디는 이메일 형식이어야 합니다.</p>
				<p>* 인증번호를 다시 확인해주세요.</p>
				<p>* 이름은 한글, 영문만 사용할 수 있으며 2글자 이상이어야 합니다.</p>
				<p>* 닉네임은 한글, 영문, 숫자만 사용할 수 있으며 2~16글자여야 합니다.</p>
				<p>* 비밀번호는 영문 대소문자, 숫자, 기호를 포함하며 10글자 이상이어야 합니다.</p>
				<p>* 비밀번호와 확인이 일치하지 않습니다.</p>
			</div>
		</div>
	</div>
</div>

<script src="<%=path%>/js/user/user.js"></script>
<script src="<%=path%>/js/user/join.js"></script>

<%@ include file="./components/Footer.jsp" %>