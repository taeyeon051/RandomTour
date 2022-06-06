<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String text = request.getParameter("text");
%>
<div class="alert alert-success alert-dismissible fade show" role="alert">
	<%=text%>
	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>