<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String state = request.getAttribute("state").toString();
	String message = request.getAttribute("message").toString();
%>

<div class="alert alert-<%=state%> alert-dismissible fade show" role="alert">
	<%=message%>
	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>