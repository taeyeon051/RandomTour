<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<jsp:include page="./components/Background.jsp"/>

<section class="d-flex justify-content-between">
	<jsp:include page="./components/Logo.jsp"/>
	
	<div id="mypage-main" class="p-5 bg-white border-gray">
		<ul class="list-unstyled d-flex align-items-center">
			<li>
				<a href="<%=path%>/main/roomList" class="fs-3">
					<i class="bi bi-arrow-left"></i>
				</a>
			</li>
			<li>친구목록</li>
			<li>요청</li>
			<li>친구추가</li>
			<li>설정</li>
			<li>1:1문의</li>
		</ul>
	</div>
	
	<div id="mypage-user-info" class="px-4 py-3 bg-white border-gray">
		<div class="d-flex justify-content-between mb-3">
			<h4 class="text-center text-blue fw-bold mt-3 mb-0">회원정보 수정</h4>
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="user-update-btn"/>
				<jsp:param name="text" value="수정"/>
			</jsp:include>
		</div>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-id"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="아이디"/>
			<jsp:param name="value" value="<%=user.getUserId()%>"/>
			<jsp:param name="readonly" value="true"/>
		</jsp:include>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-name"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="이름"/>
			<jsp:param name="value" value="<%=user.getUserName()%>"/>
		</jsp:include>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-nickname"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="닉네임"/>
			<jsp:param name="value" value="<%=user.getNickname()%>"/>
		</jsp:include>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-pwd"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호"/>
		</jsp:include>
				<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-pwdc"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호 확인"/>
		</jsp:include>
		
		<div class="d-flex justify-content-between mt-4 mb-3">
			<h4 class="text-center text-blue fw-bold mt-3 mb-0">문의하기</h4>
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="inquiry-btn"/>
				<jsp:param name="text" value="문의하기"/>
			</jsp:include>
		</div>
		<textarea id="inquiry-form" class="form-control"></textarea>
	</div>
</section>

<script src="<%=path%>/js/main/myPage.js"></script>

<%@ include file="./components/Footer.jsp" %>