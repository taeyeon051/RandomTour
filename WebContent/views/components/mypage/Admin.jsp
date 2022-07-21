<%@page import="java.util.ArrayList"%>
<%@page import="dao.MyPageDAO"%>
<%@page import="vo.InquiryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MyPageDAO dao = new MyPageDAO();
	ArrayList<InquiryVO> list = dao.getInquiryList();
%>

<div id="admin-page">
	<h4 class="my-3 text-center">문의목록</h4>
	<table class="text-center">
		<tbody class="d-block">
			<% for (InquiryVO vo : list) { %>
				<tr class="inquiry-item d-flex justify-content-between align-items-center px-3">
					<td class="id" title="<%=vo.getUserId()%>"><%=vo.getUserId()%></td>
					<td class="select"><%=vo.getSelect()%></td>
					<td class="title" title="<%=vo.getTitle()%>">
						<a href="<%=request.getContextPath()%>/main/mypage/getInquiry?id=<%=vo.getId()%>"><%=vo.getTitle() %></a>
					</td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>