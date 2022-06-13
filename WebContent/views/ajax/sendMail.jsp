<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../components/Header.jsp" %>

<% String state = (String) request.getAttribute("state"); %>
<div id="result"><%=state%></div>

<%@ include file="../components/Footer.jsp" %>