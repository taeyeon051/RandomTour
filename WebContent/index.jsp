<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./views/components/Header.jsp" %>

<script>
	<% if (user == null) { %>
		location.href = "<%=path%>/user/login";
	<% } else { %>
		location.href="<%=path%>/main/roomList";
	<% } %>
</script>

<%@ include file="./views/components/Footer.jsp" %>