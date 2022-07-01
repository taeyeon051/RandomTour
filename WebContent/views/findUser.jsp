<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<% if (user != null) { %>
	<script>
		location.href = "<%=path%>/main/roomList";
	</script>
<% } %>

<jsp:include page="./components/Background.jsp" />

<section>
	<jsp:include page="./components/Logo.jsp" />
	
	<div id="user-find-page" class="user-page text-center border-gray position-relative">
		<a href="<%=path%>/user/login" class="position-absolute fs-3" style="top: 1.2rem; left: 1.5rem;">
			<i class="bi bi-arrow-left"></i>
		</a>
		<nav>
			<div class="nav nav-tabs" id="nav-tab" role="tablist">
		    	<button class="nav-link fw-bold active" id="nav-id-tab" data-bs-toggle="tab" data-bs-target="#nav-id" type="button" role="tab" aria-controls="nav-id" aria-selected="true">아이디 찾기</button>
		    	<button class="nav-link fw-bold" id="nav-pw-tab" data-bs-toggle="tab" data-bs-target="#nav-pw" type="button" role="tab" aria-controls="nav-pw" aria-selected="false">비밀번호 찾기</button>
		    </div>
		</nav>
		<div class="tab-content border d-flex flex-column justify-content-center align-items-center" id="nav-tabContent">
		  	<div id="find-id" class="tab-pane fade show active" id="nav-id" role="tabpanel" aria-labelledby="nav-id-tab" tabindex="0">
		  		<form action="<%=path%>/user/find" method="post">
					<jsp:include page="./components/Input.jsp">
						<jsp:param name="id" value="user-name" />
						<jsp:param name="type" value="text" />
						<jsp:param name="label" value="이름" />
					</jsp:include>
					<jsp:include page="./components/Input.jsp">
						<jsp:param name="id" value="user-nickname" />
						<jsp:param name="type" value="text" />
						<jsp:param name="label" value="닉네임" />
					</jsp:include>
					<jsp:include page="./components/Button.jsp">
						<jsp:param name="id" value="find-id-btn" />
						<jsp:param name="text" value="확인" />
					</jsp:include>
		  		</form>
		  	</div>
		  	<div id="find-pw" class="tab-pane fade" id="nav-pw" role="tabpanel" aria-labelledby="nav-pw-tab" tabindex="0">
		  		<form action="<%=path%>/user/find" method="post">
		  			<jsp:include page="./components/Input.jsp">
		  				<jsp:param name="id" value="user-id" />
		  				<jsp:param name="type" value="text" />
		  				<jsp:param name="label" value="아이디" />
		  				<jsp:param name="button" value="certify" />
		  			</jsp:include>
					<jsp:include page="./components/Input.jsp">
		  				<jsp:param name="id" value="certify-number" />
		  				<jsp:param name="type" value="text" />
		  				<jsp:param name="label" value="인증번호" />
		  				<jsp:param name="disabled" value="true" />
		  			</jsp:include>
					<jsp:include page="./components/Button.jsp">
						<jsp:param name="id" value="find-pw-btn" />
						<jsp:param name="text" value="확인" />
					</jsp:include>
		  		</form>
		  	</div>
		</div>
	</div>
</section>

<script src="<%=path%>/js/user/user.js"></script>
<script src="<%=path%>/js/user/find.js"></script>

<%@ include file="./components/Footer.jsp" %>