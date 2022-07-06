<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>

<jsp:include page="./components/Background.jsp"/>

<section class="d-flex justify-content-between">
	<jsp:include page="./components/Logo.jsp"/>
	
	<div id="mypage-main" class="p-5 bg-white">
		<ul class="list-unstyled d-flex align-items-center">
			<li>
				<a href="<%=path%>/main/roomList" class="fs-3">
					<i class="bi bi-arrow-left"></i>
				</a>
			</li>
			<li>친구목록</li>
			<li>친구요청</li>
			<li>친구추가</li>
		</ul>
		<div class="d-flex justify-content-between">
			<div id="friend-chatting-list">
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
				<div class="friend">nickname</div>
			</div>
			<div id="friend-main" class="position-relative">
				<div id="friend-chatting" class="d-none">
			    	<div id="friend-chat">
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
						<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-end">
							<div class="message">message</div>
						</div>
			    		<div class="m-2 text-start">
							<div class="message">message</div>
						</div>
			    	</div>
			    	<div class="w-100 input-group mt-2 position-absolute bottom-0">
			    		<input type="text" id="friend-chat-form" class="form-control">
			    		<button id="chatting-button" class="btn btn-skyblue" type="button">전송</button>
			    	</div>
				</div>
				<div id="friend-list" class="d-none">
					<table class="text-center">
						<tbody class="d-block">
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
							<tr class="friend-item d-flex justify-content-between align-items-center px-3">
								<td class="friend-item-nickname">nickname</td>
								<td class="btn btn-red">친구삭제</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="friend-request" class="dd-none">
					
				</div>
				<div id="friend-add" class="d-none">
				
				</div>
			</div>
		</div>
	</div>
	
	<div id="mypage-user-info" class="px-4 py-3 bg-white border-gray">
		<div class="d-flex justify-content-between mb-3">
			<h4 class="text-center text-blue fw-bold mt-3 mb-0">회원정보 수정</h4>
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="user-update-btn"/>
				<jsp:param name="text" value="수정"/>
			</jsp:include>
		</div>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-id"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="아이디"/>
			<jsp:param name="value" value="<%=user.getUserId()%>"/>
			<jsp:param name="readonly" value="true"/>
		</jsp:include>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-name"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="이름"/>
			<jsp:param name="value" value="<%=user.getUserName()%>"/>
		</jsp:include>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-nickname"/>
			<jsp:param name="type" value="text"/>
			<jsp:param name="label" value="닉네임"/>
			<jsp:param name="value" value="<%=user.getNickname()%>"/>
		</jsp:include>
		<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-pwd"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호"/>
		</jsp:include>
				<jsp:include page="./components/MyPageInput.jsp">
			<jsp:param name="id" value="user-pwdc"/>
			<jsp:param name="type" value="password"/>
			<jsp:param name="label" value="비밀번호 확인"/>
		</jsp:include>
		
		<div class="d-flex justify-content-between mt-4 mb-3">
			<h4 class="text-center text-blue fw-bold mt-3 mb-0">문의하기</h4>
			<jsp:include page="./components/Button.jsp">
				<jsp:param name="id" value="inquiry-btn"/>
				<jsp:param name="text" value="문의하기"/>
			</jsp:include>
		</div>
		<textarea id="inquiry-form" class="form-control"></textarea>
	</div>
</section>

<script src="<%=path%>/js/main/myPage.js"></script>

<%@ include file="./components/Footer.jsp" %>