<%@page import="vo.FriendVO"%>
<%@page import="vo.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.FriendDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO user = (UserVO) session.getAttribute("randomtour-user");
	FriendDAO dao = new FriendDAO();
	ArrayList<FriendVO> list = dao.getFriendList(user.getUserId());
%>

<div id="friend-list" class="d-block">
	<table class="text-center">
		<tbody class="d-block">
			<% for (FriendVO vo : list) { %>
				<tr class="friend-item d-flex justify-content-between align-items-center px-3">
					<% if (vo.getSendUserId().equals(user.getUserId())) { %>
						<td class="nickname"><%= dao.getNickname(vo.getAcceptUserId()) %></td>
					<% } else if (vo.getAcceptUserId().equals(user.getUserId())) { %>
						<td class="nickname"><%= dao.getNickname(vo.getSendUserId()) %></td>
					<% } %>
					<td class="btn btn-red">친구 삭제</td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>