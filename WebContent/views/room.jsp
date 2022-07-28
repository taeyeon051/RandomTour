<%@page import="vo.RoomVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp"%>

<%
	RoomVO room = (RoomVO) request.getAttribute("room-data");
%>

<section class="w-100 h-100 bg-white">
	<div class="menu">
		<div class="back">
			<div id="back-btn">
				<i class="bi bi-arrow-left"></i>
			</div>
		</div>
		<div class="gmae-code card">
			<p><%=room.getRoomId()%></p>
		</div>
		<div class="private card">
			<p>비공개</p>
		</div>
		<div class="game-password card">
			<p>비밀번호: 1127</p>
		</div>
		<div class="game-name card">
			<p><%=room.getTitle()%></p>
		</div>
	</div>
	<div class="user-box">
		<div class="user-item card">
			<i class="bi bi-person-fill text-wheat"></i>
			<h3>이름</h3>
		</div>
		<div class="user-item card">
			<i class="bi bi-person-fill text-skyblue"></i>
			<h3>이름</h3>
		</div>
		<div class="user-item card">
			<i class="bi bi-person-fill text-mint"></i>
			<h3>이름</h3>
		</div>
		<div class="user-item card">
			<i class="bi bi-person-fill text-brown"></i>
			<h3>이름</h3>
		</div>
		<div class="user-item card">
			<i class="bi bi-person-fill text-silver"></i>
			<h3>이름</h3>
		</div>
		<div class="user-item card">
			<i class="bi bi-person-fill text-blue"></i>
			<h3>이름</h3>
		</div>
	</div>
	<div class="chat-start">
		<div class="chat-box">
			<div class="chat-fild">
				<p>텍스트</p>
			</div>
			<div id="chat-input" class="chat p-3 pt-0">
				<div id="chatting-list"></div>
				<div class="input-group mt-2">
					<input type="text" id="chatting-form" class="form-control">
					<button id="chatting-button" class="btn btn-brown" type="button">전송</button>
				</div>
			</div>
		</div>
		<div class="game-set">
			<div class="map"></div>
			<div class="start-button">
				<p>시작</p>
			</div>
		</div>
	</div>
</section>

<%@ include file="./components/Footer.jsp"%>