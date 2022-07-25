<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dao.FriendDAO"%>
<%@page import="vo.UserVO"%>
<%@page import="dao.MyPageDAO"%>
<%@page import="vo.ChattingVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UserVO user = (UserVO) session.getAttribute("randomtour-user");
	String nickname = request.getParameter("nickname");
	MyPageDAO dao = new MyPageDAO();
	ArrayList<ChattingVO> list = dao.getChatting(user.getUserId(), nickname);
%>

<div id="friend-chatting">
   	<div id="friend-chat">
   		<% for (ChattingVO vo : list) { %>
   			<% if (!vo.getChatting().equals("")) { %>
   				<%
	   				String dateText = vo.getSendDate();
	   				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   				Date date = dateFormat.parse(dateText);
	   				dateText = dateFormat.format(date);
   				%>
   				<% if (vo.getSendUserId().equals(user.getUserId())) { %>
			   		<div class="m-2 d-flex justify-content-end">
						<div class="msg-date d-flex align-items-end text-gray m-1"><%=dateText%></div>
						<div class="message"><%=vo.getChatting()%></div>
					</div>
   				<% } else { %>
   					<div class="m-2 d-flex justify-content-start">
						<div class="message"><%=vo.getChatting()%></div>
						<div class="msg-date d-flex align-items-end text-gray m-1"><%=dateText%></div>
					</div>
   				<% } %>
   			<% } %>   			
   		<% } %>
   	</div>
   	<div class="w-100 input-group mt-2 position-absolute bottom-0">
   		<input type="text" id="friend-chat-form" class="form-control">
   		<button id="chatting-button" class="btn btn-skyblue" type="button">전송</button>
   	</div>
</div>