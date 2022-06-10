<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String userId = (String) request.getAttribute("userId"); %>
<div id="result"><%=userId.equals("") ? "false" : "true" %></div>

<div class="toast bg-white position-absolute start-50" role="alert" aria-live="assertive" aria-atomic="true">
	<div class="toast-header">
		<strong class="me-auto">아이디 찾기 완료</strong>
		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
	</div>
	<div class="toast-body"><%=userId %></div>
</div>