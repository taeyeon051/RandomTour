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

<div id="friend-send">
	<table class="text-center">
		<tbody class="d-block">
			<% for (FriendVO vo : list) { %>
				<tr class="send d-flex justify-content-between align-items-center p-3">
					<td class="nickname"><%= dao.getNickname(vo.getSendUserId()) %></td>
					<td class="send-btn d-flex">
						<div class="accept-btn text-success mx-3">
							<i class="bi bi-check-circle-fill"></i>
						</div>
						<div class="refuse-btn text-danger">
							<i class="bi bi-x-circle-fill"></i>
						</div>
					</td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>