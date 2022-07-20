<%@page import="vo.InquiryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Header.jsp" %>
<%
	if (user == null) {
		response.sendRedirect("/");
		return;
	}

	if (!user.getUserId().equals("randomtour@naver.com")) {
		response.sendRedirect("/main/mypage?p=list");
		return;
	}
	
	InquiryVO vo = (InquiryVO) request.getAttribute("inquiry");
%>

<jsp:include page="../Background.jsp"/>
 
<div id="inquiry-popup" class="bg-white d-flex justify-content-center align-items-center">
	<div id="get-inquiry" class="row g-2">
		<div class="col-1 pt-2 ps-2">
			<a href="<%=path%>/main/mypage?p=admin" class="fs-3">
				<i class="bi bi-arrow-left"></i>
			</a>
		</div>
		<div class="col-7">
			<div class="form-floating">
				<input type="text" class="form-control" id="inquiry-title" placeholder="title" value="<%=vo.getTitle()%>">
				<label for="inquiry-title">제목</label>
			</div>
		</div>
		<div class="col-4">
			<div class="form-floating">
				<select class="form-select" id="inquiry-select">
					<% String sel = vo.getSelect(); %>
					<option value="util" <%=sel.equals("이용 문의") ? "selected" : ""%>>이용 문의</option>
					<option value="design" <%=sel.equals("디자인 제안") ? "selected" : ""%>>디자인 제안</option>
					<option value="service" <%=sel.equals("서비스 제안") ? "selected" : ""%>>서비스 제안</option>
					<option value="error" <%=sel.equals("오류 신고") ? "selected" : ""%>>오류 신고</option>
					<option value="etc" <%=sel.equals("기타") ? "selected" : ""%>>기타</option>
				</select>
				<label for="inquiry-select">질문 유형을 선택해주세요.</label>
			</div>
		</div>
		<div contenteditable="true" class="form-control p-3" id="inquiry-content"><%=vo.getContent()%></div>
	</div>
</div>

<script src="<%=path%>/js/main/myPage.js"></script>

<%@ include file="../Footer.jsp" %>