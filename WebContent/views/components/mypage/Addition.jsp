<%@page import="vo.FriendVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.FriendDAO"%>
<%@page import="vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVO user = (UserVO) session.getAttribute("randomtour-user");
	FriendDAO dao = new FriendDAO();
	ArrayList<FriendVO> list = dao.getFriendSendList(user.getUserId());
%>

<div id="friend-addition">
	<jsp:include page="../Input.jsp">
		<jsp:param name="id" value="send-nickname"/>
		<jsp:param name="type" value="text"/>
		<jsp:param name="label" value="닉네임 입력"/>
		<jsp:param name="button" value="send"/>
		<jsp:param name="button-text" value="친구요청 보내기"/>
	</jsp:include>
	
	<h4 class="mt-4 mb-2">대기중</h4>
	<table>
		<tbody class="d-block">
			<% for (FriendVO vo : list) { %>
				<tr>
					<td class="nickname px-3"><%= vo.getAcceptUserId() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</div>