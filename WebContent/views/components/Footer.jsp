<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 알림창
	String alert = (String) request.getAttribute("alert");
	String success = (String) request.getAttribute("success");
%>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    <% if (alert != null) { %>
    	new App().alert("danger", "<%=alert%>");
    <% } %>
    <% if (success != null) { %>
    	new App().alert("success", "<%=success%>");
    <% } %>
</script>

</html>