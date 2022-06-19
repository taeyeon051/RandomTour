<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	String label = request.getParameter("label");
	String button = request.getParameter("button");
	String disabled = request.getParameter("disabled");
%>
<div class="form-floating mb-3" id="<%=button != null ? "user-id-box" : ""%>">
    <input type="<%=type%>" id="<%=id%>" name="<%=id%>" class="form-control" placeholder=" " <%=disabled != null ? "disabled" : ""%>>
    <label for="<%=id%>"><%=label%></label>
    <% if (button != null && button.equals("certify")) { %>
    	<jsp:include page="./Button.jsp">
			<jsp:param name="id" value="certify-btn" />
			<jsp:param name="text" value="인증하기" />
    	</jsp:include>
    <% } %>
</div>