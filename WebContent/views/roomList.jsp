<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./components/Header.jsp" %>
<% if (user == null) { %>
	<script>
		new App().alert('danger', '로그인 후 이용가능합니다.');
		location.href = "<%=path%>/user/login";
	</script>
<% } %>

<jsp:include page="./components/Background.jsp" />
<jsp:include page="./components/Logo.jsp" />

<section id="main-page" class="d-flex justify-content-between">
	<table class="table table-striped table-hover text-center bg-white">
		<thead>
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
		
		<tbody>
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
</section>

<script src="<%=path%>/js/roomList.js"></script>

<%@ include file="./components/Footer.jsp" %>