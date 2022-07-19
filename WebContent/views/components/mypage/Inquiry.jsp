<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="inquiry-form" class="w-100 p-4">
	<div class="row g-2 mb-4">
		<div class="col-7">
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
		<div class="col-1 d-flex justify-content-center align-items-center">
			<i id="explanation-icon" class="bi bi-exclamation-circle ms-2" data-bs-toggle="modal" data-bs-target="#inquiry-explanation"></i>
		</div>
	</div>
	<div contenteditable="true" class="form-control p-3" id="inquiry-content"></div>
	<div class="d-flex justify-content-end mt-3">
		<jsp:include page="../Button.jsp">
			<jsp:param name="id" value="inquiry-btn"/>
			<jsp:param name="text" value="문의하기"/>
		</jsp:include>
	</div>
</div>

<div class="modal fade" id="inquiry-explanation" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content p-3">
			<div class="d-flex justify-content-end">
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>* 문의와 관련 없는 글, 욕설 등을 작성할 경우 정지 대상이 될 수 있습니다.</p>
				<p>* 문의에 대한 답변은 현재 아이디인 이메일 주소의 메일로 답변이 갑니다.</p>
				<p>* 사진을 첨부하시면 해결에 도움이 됩니다.</p>
				<p>* 사진은 복사, 붙여넣기를 통하여 첨부할 수 있으며 최대 2장까지 가능합니다.</p>
				<p>* 질문유형을 선택해주시면 빠른 해결에 도움이 됩니다.</p>
			</div>
		</div>
	</div>
</div>