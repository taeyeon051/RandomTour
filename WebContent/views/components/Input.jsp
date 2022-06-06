<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	String label = request.getParameter("label");
%>
<div class="form-floating mb-3">
    <input type="<%=type%>" id="<%=id%>" name="<%=id%>" class="form-control" placeholder=" ">
    <label for="<%=id%>"><%=label%></label>
</div>