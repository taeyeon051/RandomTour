<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String text = request.getParameter("text");
	String margin = request.getParameter("margin");
%>
<button type="button" id="<%=id%>" class="btn btn-skyblue border-0 fw-semibold <%=margin != null ? "mt-" + margin : ""%>">
	<%=text%>
</button>