<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="inquiry" class="w-100 p-4">
	<div class="row g-2 mb-4">
		<div class="col-8">
			<div class="form-floating">
				<input type="text" class="form-control" id="inquiry-title" placeholder="title">
				<label for="inquiry-title">제목</label>
			</div>
		</div>
		<div class="col-4">
			<div class="form-floating">
				<select class="form-select" id="inquiry-select">
					<option value="util">이용 문의</option>
					<option value="design">디자인 제안</option>
					<option value="service">서비스 제안</option>
					<option value="error">오류 신고</option>
					<option value="etc">기타</option>
				</select>
				<label for="inquiry-select">질문 유형을 선택해주세요.</label>
			</div>
		</div>
	</div>
	<textarea class="form-control p-3" id="inquiry-content"></textarea>
	<div class="d-flex justify-content-end mt-3">
		<jsp:include page="../Button.jsp">
			<jsp:param name="id" value="inquiry-btn"/>
			<jsp:param name="text" value="문의하기"/>
		</jsp:include>
	</div>
</div>