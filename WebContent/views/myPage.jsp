<%@page import="dao.FriendDAO"%>
<%@page import="vo.ChattingVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.MyPageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>
<%
	String mypage = request.getParameter("p");
	if (mypage == null) {
		response.sendRedirect("/main/mypage?p=list");
		return;
	}
	
	boolean admin = false;
	if (user.getUserId().equals("randomtour@naver.com") && user.getUserName().equals("관리자") && user.getNickname().equals("admin")) admin = true;
	if (!admin && mypage.equals("admin")) {
		response.sendRedirect("/main/mypage?p=list");
		return;
	}
	
	FriendDAO friendDao = new FriendDAO();
	MyPageDAO mypageDao = new MyPageDAO();
	ArrayList<ChattingVO> list = mypageDao.getChattingList(user.getUserId());
	ArrayList<String> nicknameList = new ArrayList<>();
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
			<li><a href="<%=path%>/main/mypage?p=send" class="<%=mypage.equals("send") ? "text-blue" : ""%>">친구 요청</a></li>
			<li><a href="<%=path%>/main/mypage?p=add" class="<%=mypage.equals("add") ? "text-blue" : ""%>">친구추가</a></li>
			<li><a href="<%=path%>/main/mypage?p=inquiry" class="<%=mypage.equals("inquiry") ? "text-blue" : "" %>">문의하기</a></li>
			<% if (admin) { %>
				<li><a href="<%=path%>/main/mypage?p=admin" class="<%=mypage.equals("admin") ? "text-blue" : ""%>">관리자</a></li>
			<% } %>
		</ul>
		<div class="d-flex justify-content-between">
			<% if (mypage.equals("inquiry")) { %>
				<jsp:include page="./components/mypage/Inquiry.jsp"/>
			<% } else if (mypage.equals("admin") && admin) { %>
				<jsp:include page="./components/mypage/Admin.jsp"/>
			<% } else { %>
				<div id="friend-chatting-list">
					<%
						for (ChattingVO vo : list) {
							String nickname = "";
							if (vo.getSendUserId().equals(user.getUserId())) nickname = friendDao.getNickname(vo.getAcceptUserId());
							else if (vo.getAcceptUserId().equals(user.getUserId())) nickname = friendDao.getNickname(vo.getSendUserId());
							if (nicknameList.contains(nickname)) continue;
							nicknameList.add(nickname);
							if (!nickname.equals("")) {
					%>
								<div class="friend w-100 px-3 py-2 d-flex justify-content-center align-items-center">
									<a href="<%=path%>/main/mypage?p=chat&nickname=<%=nickname%>"><%=nickname%></a>
								</div>
					<%
							}
						}
					%>
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
			<% } %>
		</div>
	</div>
	
	<div id="mypage-user-info" class="p-4 bg-white d-flex flex-column justify-content-center">
		<div class="d-flex justify-content-center align-items-center mb-3">
			<h4 class="text-blue fw-bold mb-0">회원 정보 수정</h4>
			<i id="explanation-icon" class="bi bi-exclamation-circle ms-2" data-bs-toggle="modal" data-bs-target="#user-update-explanation"></i>
		</div>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="user-id"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="아이디"/>
			<jsp:param name="value" value="<%=user.getUserId()%>"/>
			<jsp:param name="readonly" value="true" />
		</jsp:include>
		<div class="d-flex justify-content-end mb-3">
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="certify-btn"/>
				<jsp:param name="text" value="인증하기"/>
			</jsp:include>
		</div>
		<jsp:include page="./components/mypage/Input.jsp">
			<jsp:param name="id" value="certify-number"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="인증번호"/>
			<jsp:param name="disabled" value="true"/>			
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
		<jsp:include page="./components/Button.jsp">
			<jsp:param name="id" value="user-update-btn"/>
			<jsp:param name="text" value="수정"/>
			<jsp:param name="margin" value="2"/>
		</jsp:include>
	</div>
</section>

<div class="modal fade" id="user-update-explanation" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content p-3">
			<div class="d-flex justify-content-end">
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>* 회원 정보 수정 시 자동으로 로그아웃됩니다.</p>
				<p>* 이름과 닉네임은 수정하는 경우에만 변경을 해주시면 됩니다.</p>
				<p>* 비밀번호는 변경하지 않을 경우 기존 비밀번호를 입력해주세요.</p>
			</div>
		</div>
	</div>
</div>

<script src="<%=path%>/js/user/user.js"></script>
<script src="<%=path%>/js/main/myPage.js"></script>
<script>
	const mypage = new Mypage("<%=user.getUserId()%>");
</script>

<%@ include file="./components/Footer.jsp" %>