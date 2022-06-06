<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String text = request.getParameter("text");
%>
<button type="button" id="<%=id%>" class="btn btn-skyblue border-0 mt-2 fw-semibold"><%=text%></button>