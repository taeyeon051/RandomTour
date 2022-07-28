<%@page import="dao.FriendDAO"%>
<%@page import="dao.RoomDAO"%>
<%@page import="vo.RoomVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<%
	RoomDAO dao = new RoomDAO();
	ArrayList<RoomVO> list = dao.getRoomList();
	FriendDAO friend = new FriendDAO();
%>

<jsp:include page="./components/Background.jsp"/>

<section class="d-flex justify-content-between">
	<jsp:include page="./components/Logo.jsp"/>
	
	<div id="room-list" class="p-5 bg-white">
	    <table class="text-center mt-2">
	        <thead class="d-block">
	            <tr>
	                <th>방번호</th>
	                <th>제목</th>
	                <th>방장</th>
	                <th>인원</th>
	                <th>비고</th>
	                <th>상태</th>
	                <th>맵종류</th>
	            </tr>
	        </thead>
	
	        <tbody class="d-block">
		        <% for (RoomVO room : list) { %>
		            <tr>
		                <td><%=room.getRoomId()%></td>
		                <td><%=room.getTitle()%></td>
		                <td><%=friend.getNickname(room.getUserId())%></td>
		                <td><%=room.getPersonnel()%>/6</td>
		                <%
		                	boolean roomPrivate = room.isRoomPrivate();
		                	boolean state = room.isState();
		                	String map = room.getMap();
		                	map = map.equals("korea") ? "한국" : "세계";
		                %>
		                <td><%=roomPrivate ? "비공개" : "공개"%></td>
		                <td><%=state ? "게임중" : "대기"%></td>
		                <td><%=map%></td>
		            </tr>
		        <% } %>
	        </tbody>
	    </table>
    	<div class="input-group mt-4">
    		<input type="text" id="room-title" class="form-control" placeholder="방 제목을 입력하세요.">
    		<button id="room-button" class="btn btn-brown" type="button">방 생성</button>
    	</div>
	</div>
	
	<div id="user-chatting" class="bg-white">
	    <div id="user-info" class="w-100 d-flex justify-content-between align-items-center p-3">
	        <a href="<%=path%>/main/mypage" id="nickname"><%=user.getNickname()%>님</a>
	        <a id="logout-btn" class="btn btn-brown" href="<%=path%>/user/logout">로그아웃</a>
	    </div>
	    <div id="chatting" class="p-3 pt-0">
	    	<div id="chatting-list"></div>
	    	<div class="input-group mt-2">
	    		<input type="text" id="chatting-form" class="form-control">
	    		<button id="chatting-button" class="btn btn-brown" type="button">전송</button>
	    	</div>
	    </div>
	</div>
</section>

<script src="<%=path%>/js/main/roomList.js"></script>
<script>
	const roomList = new RoomList("<%=user.getUserId()%>");
</script>

<%@ include file="./components/Footer.jsp" %>