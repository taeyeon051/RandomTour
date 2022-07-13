<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	String label = request.getParameter("label");
	String value = request.getParameter("value");
	String readonly = request.getParameter("readonly");
	String disabled = request.getParameter("disabled");
%>
    
<div class="mb-<%=id.equals("user-id") ? "1" : "3" %> row">
	<label for="<%=id%>" class="col-sm-4 col-form-label"><%=label%></label>
	<div class="col-sm-8">
		<input
			type="<%=type%>"
			<%=readonly != null ? "readonly" : ""%>
			<%=disabled != null ? "disabled" : ""%>
			name="<%=id%>"
			class="<%=readonly != null ? "form-control-plaintext" : "form-control"%>"
			id="<%=id%>"
			value="<%=value != null ? value : ""%>">
	</div>
</div>