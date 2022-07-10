<%@page import="vo.FriendVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.FriendDAO"%>
<%@page import="vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO user = (UserVO) session.getAttribute("randomtour-user");
	FriendDAO dao = new FriendDAO();
	ArrayList<FriendVO> list = dao.getFriendRequestList(user.getUserId());
%>

<div id="friend-request">
	<table class="text-center">
		<tbody class="d-block">
			<% for (FriendVO vo : list) { %>				
				<tr class="request d-flex justify-content-between align-items-center p-3">
					<td class="nickname"><%= vo.getSendUserId() %></td>
					<td class="request-btn d-flex">
						<div class="yes-btn text-success mx-3">
							<i class="bi bi-check-circle-fill"></i>
						</div>
						<div class="no-btn text-danger">
							<i class="bi bi-x-circle-fill"></i>
						</div>
					</td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>