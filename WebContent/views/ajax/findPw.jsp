<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../components/Header.jsp" %>    
    
<% Boolean state = Boolean.parseBoolean(request.getAttribute("state").toString()); %>
<div id="result"><%=state ? "실패" : "성공"%></div>

<form id="update-pw-form" action="<%=path%>/user/updatePwd" method="post">
	<jsp:include page="../components/Input.jsp">
		<jsp:param name="id" value="user-pwd"/>
		<jsp:param name="type" value="password"/>
		<jsp:param name="label" value="새비밀번호"/>
	</jsp:include>
	<jsp:include page="../components/Input.jsp">
		<jsp:param name="id" value="user-pwdc"/>
		<jsp:param name="type" value="password"/>
		<jsp:param name="label" value="새비밀번호 확인"/>
	</jsp:include>
	<jsp:include page="../components/Button.jsp">
		<jsp:param name="id" value="update-pw-btn"/>
		<jsp:param name="text" value="확인"/>
		<jsp:param name="margin" value="2"/>
	</jsp:include>
</form>
		
<%@ include file="../components/Footer.jsp" %>