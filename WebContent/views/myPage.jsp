<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>
<%
	String mypage = request.getParameter("p");
	if (mypage == null) {
		response.sendRedirect("/main/mypage?p=list");
		return;
	}
%>

<jsp:include page="./components/Background.jsp"/>

<section class="d-flex justify-content-between">
	<jsp:include page="./components/Logo.jsp"/>
	
	<div id="mypage-main" class="p-5 bg-white">
		<ul class="list-unstyled d-flex align-items-center">
			<li>
				<a href="<%=path%>/main/roomList" class="fs-3">
					<i class="bi bi-arrow-left"></i>
				</a>
			</li>
			<li><a href="<%=path%>/main/mypage?p=list" class="<%=mypage.equals("list") ? "text-blue" : ""%>">친구목록</a></li>
			<li><a href="<%=path%>/main/mypage?p=send" class="<%=mypage.equals("send") ? "text-blue" : ""%>">친구요청</a></li>
			<li><a href="<%=path%>/main/mypage?p=add" class="<%=mypage.equals("add") ? "text-blue" : ""%>">친구추가</a></li>
		</ul>
		<div class="d-flex justify-content-between">
			<div id="friend-chatting-list">
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					가나다라마바사아자차카타파하히히
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
				<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
					nickname
				</div>
			</div>
			<div id="friend-main" class="position-relative">
				<% if (mypage.equals("send")) { %>
					<jsp:include page="./components/mypage/Send.jsp"/>
				<% } else if (mypage.equals("add")) { %>
					<jsp:include page="./components/mypage/Addition.jsp"/>
				<% } else if (mypage.equals("chat")) { %>						
					<jsp:include page="./components/mypage/Chatting.jsp"/>
				<% } else { %>
					<jsp:include page="./components/mypage/List.jsp"/>
				<% } %>
			</div>
		</div>
	</div>
	
	<div id="mypage-user-info" class="p-4 bg-white position-relative">
		<div class="d-flex justify-content-between align-items-center mt-1 mb-3">
			<h4 class="text-center text-blue fw-bold mb-0">회원정보 수정</h4>
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="user-update-btn"/>
				<jsp:param name="text" value="수정"/>
			</jsp:include>
		</div>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="user-id"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="아이디"/>
			<jsp:param name="value" value="<%=user.getUserId()%>"/>
			<jsp:param name="readonly" value="true" />
		</jsp:include>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="user-name"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="이름"/>
			<jsp:param name="value" value="<%=user.getUserName()%>"/>
		</jsp:include>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="user-nickname"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="닉네임"/>
			<jsp:param name="value" value="<%=user.getNickname()%>"/>
		</jsp:include>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="user-pwd"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호"/>
		</jsp:include>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="user-pwdc"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호 확인"/>
		</jsp:include>
		
		<div class="d-flex justify-content-between align-items-center mt-4 mb-3">
			<h4 class="text-center text-blue fw-bold mb-0">문의하기</h4>
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="inquiry-btn"/>
				<jsp:param name="text" value="문의하기"/>
			</jsp:include>
		</div>
		<textarea id="inquiry-form" class="form-control"></textarea>

		<div class="popup d-none">
			<div class="inner">
				<jsp:include page="./components/Input.jsp">
					<jsp:param name="id" value="certify"/>
					<jsp:param name="type" value="text"/>
					<jsp:param name="label" value="인증번호"/>
					<jsp:param name="button" value="certify"/>
				</jsp:include>
			</div>
		</div>
	</div>
</section>

<script src="<%=path%>/js/main/myPage.js"></script>
<script>
	const mypage = new Mypage("<%=user.getUserId()%>");
</script>

<%@ include file="./components/Footer.jsp" %>