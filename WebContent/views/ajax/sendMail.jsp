<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../components/Header.jsp" %>

<% Boolean state = Boolean.parseBoolean(request.getAttribute("state").toString()); %>
<div id="result"><%=state ? "성공" : "실패"%></div>

<%@ include file="../components/Footer.jsp" %>