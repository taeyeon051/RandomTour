<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<jsp:include page="./components/Background.jsp"/>

<section class="d-flex justify-content-between">
	<jsp:include page="./components/Logo.jsp"/>
	
	<div id="room-list" class="p-5 bg-white border-gray">
	    <table class="text-center">
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
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	            <tr>
	                <td>0351</td>
	                <td>제목</td>
	                <td>방장</td>
	                <td>1/6</td>
	                <td>공개</td>
	                <td>대기</td>
	                <td>한국</td>
	            </tr>
	        </tbody>
	    </table>
	</div>
	
	<div id="user-chatting" class="bg-white border-gray">
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

<%@ include file="./components/Footer.jsp" %>