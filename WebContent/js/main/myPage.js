const log = console.log;

class Mypage {
	constructor(userId) {
		this.app = new App();
		this.userForm = new UserForm();
		this.url = new URL(location.href);
		this.webSocket;

		this.userId = userId;
		this.chatNickname;
		this.isUpdateUserFocus = false;

		this.nicknameList = [];

		this.addEvent();
		this.updateUserFormEvent();
	}

	addEvent() {
		const { url } = this;
		const page = url.searchParams.get("p");
		if (page === "chat") this.chatEvent();
		else if (page === "add") {
			const sendBtn = document.querySelector("#send-btn");
			sendBtn.addEventListener("click", () => { this.addFriend(); });
		} else if (page === "send") {
			this.getNicknameList();
			const { nicknameList } = this;
			nicknameList.forEach(nickname => {
				const acceptBtn = nickname.btnDom.querySelector(".accept-btn");
				const refuseBtn = nickname.btnDom.querySelector(".refuse-btn");
				acceptBtn.addEventListener("click", () => { this.acceptFriend(nickname, true); });
				refuseBtn.addEventListener("click", () => { this.acceptFriend(nickname, false); });
			});
		} else if (page === "list") {
			this.deleteFriendEvent();
		} else if (page === "inquiry") {
			this.inquiryEvent();
		}
	}

	// 문의하기 페이지 이벤트
	inquiryEvent() {
		const { app } = this;

		const title = document.querySelector("#inquiry-title");
		const select = document.querySelector("#inquiry-select");
		const content = document.querySelector("#inquiry-content");
		const inquiryBtn = document.querySelector("#inquiry-btn");
		inquiryBtn.addEventListener("click", () => {
			if (title.value.trim() === "") return app.alert("danger", "제목을 입력해주세요.");
			if (select.value.trim() === "") return app.alert("danger", "질문 유형을 선택해주세요.");
			if (content.innerHTML.trim() === "") return app.alert("danger", "내용을 입력해주세요.");

			this.inquirySend(title, select, content);
		});
	}

	// 문의하기 전송
	inquirySend(title, select, content) {
		const { userId } = this;
		const selectList = { "util": "이용 문의", "design": "디자인 제안", "service": "서비스 제안", "error": "오류 신고", "etc": "기타" };
		const inquiryData = { "user-id": userId, "title": title.value, "select": selectList[select.value], "content": content.innerHTML };
		
		$.ajax({
			url: "/main/inquiry",
			type: "POST",
			data: inquiryData,
			success: data => {
				this.ajaxAlert(data);
                if (data.indexOf("success") !== -1) {
                    title.value = "";
					select.value = "util";
					content.innerHTML = "";
                }
			}
		});
	}

	// 친구 삭제 이벤트
	deleteFriendEvent() {
		const nicknameList = [];
		const list = document.querySelectorAll("#friend-list tr");
		list.forEach(tr => {
			const name = tr.querySelector(".nickname").innerText;
			const btn = tr.querySelector(".btn");
			nicknameList.push({ "name": name, "btn": btn });
		});

		nicknameList.forEach(nickname => {
			const deleteBtn = nickname.btn;
			deleteBtn.addEventListener("click", () => { this.deleteFriend(nickname); });
		});
	}

	// 친구 삭제
	deleteFriend(nickname) {
		if (!confirm("정말 삭제하시겠습니까?")) return;
		const { userId } = this;
		$.ajax({
			url: "/main/friend/delete",
			type: "POST",
			data: { "user-id": userId, "nickname": nickname.name },
			success: data => {
				this.ajaxAlert(data, "accept", nickname.btn.parentElement);
			}
		});
	}

	chatEvent() {

	}

	// 친구 요청 페이지 닉네임, 버튼 리스트에 저장
	getNicknameList() {
		const sendTable = document.querySelectorAll("#friend-send tbody>tr");
		sendTable.forEach(tr => {
			const name = tr.querySelector(".nickname").innerHTML;
			const btnDom = tr.querySelector(".send-btn");
			this.nicknameList.push({ "name": name, "btnDom": btnDom });
		});
	}

	// 회원 정보 수정 form 이벤트
	updateUserFormEvent() {
		const { userForm } = this;
		userForm.inputEvent();
		userForm.certifyBtnClickEvent();

		const updateBtn = document.querySelector("#user-update-btn");
		updateBtn.addEventListener("click", () => { this.updateUser(); });

		const inputList = document.querySelectorAll("#mypage-user-info input");
		inputList.forEach(input => {
			input.addEventListener("focusin", () => { this.isUpdateUserFocus = true; });
			input.addEventListener("focusout", () => { this.isUpdateUserFocus = false; });
		});

		window.addEventListener("keydown", e => {
			if (e.key === "Enter" && this.isUpdateUserFocus) this.updateUser();
		});
	}

	// 회원 정보 수정
	updateUser() {
		const { app, userForm, userId } = this;
		const { inputIdList, messageList } = userForm;
		const formData = {};

		const inputList = document.querySelectorAll("#mypage-user-info input");
		if (app.emptyCheck(inputList)) return app.alert("danger", "빈 값이 있습니다.");
		let inputValueCheck = false;
		inputIdList.forEach((inputId, idx) => {
			const input = document.querySelector(inputId);
			formData[`${inputIdList[idx].substring(1)}`] = input.value;
			if ($(input).hasClass("is-invalid")) {
				inputValueCheck = true;
				app.alert("danger", messageList[idx]);
			}
		});
		if (inputValueCheck) return;
		if (userId !== formData["user-id"]) return;

		$.ajax({
			url: "/user/update",
			type: "POST",
			data: formData,
			success: data => {
				this.ajaxAlert(data);
				if (data.indexOf("success") !== -1) {
					setTimeout(() => {
						location.href = "/user/logout";
					}, 700);
				}
			}
		});
	}

	// 친구 추가
	addFriend() {
		const { app, userId } = this;
		const nickname = document.querySelector("#send-nickname");

		if (nickname.value.trim() === "") return app.alert("danger", "빈 값이 있습니다.");
		if (nickname.value.match(app.getRegex("nickname")) === null || nickname.value.match(app.getRegex("nickname"))[0] !== nickname.value) {
			return app.alert("danger", "존재하지 않는 닉네임입니다.");
		}

		let isSend = false;
		const table = document.querySelector("#friend-addition tbody");
		table.querySelectorAll("tr").forEach(tr => {
			if (tr.querySelector(".nickname").innerText === nickname.value) {
				isSend = true;
				return false;
			}
		});
		if (isSend) return app.alert("warning", "이미 친구요청이 전송되었습니다.");

		$.ajax({
			url: "/main/friend/add",
			type: "POST",
			data: { "user-id": userId, "nickname": nickname.value },
			success: data => {
				this.ajaxAlert(data, "add");
			}
		});
	}

	// 친구 요청 수락, 거절
	acceptFriend(nickname, accept) {
		const { userId } = this;
		$.ajax({
			url: "/main/friend/accept",
			type: "POST",
			data: { "user-id": userId, "nickname": nickname.name, "accept": accept },
			success: data => {
				this.ajaxAlert(data, "accept", nickname.btnDom.parentElement);
			}
		});
	}

	// ajax 알림창
	ajaxAlert(data, friend, dom) {
		const div = document.createElement("div");
		div.innerHTML = data;
		document.body.appendChild(div);

		if (friend === "add") {
			const table = document.querySelector("#friend-addition tbody");
			const nickname = document.querySelector("#send-nickname");
			if (data.indexOf("success") !== -1) {
				const tr = document.createElement("tr");
				tr.innerHTML = `<td class="nickname px-3">${nickname.value}</td>`;
				table.prepend(tr);
				nickname.value = "";
			}
		} else if (friend === "accept") {
			if (!div.querySelector("alert-warning")) {
				dom.remove();
			}
		}

		setTimeout(() => {
			div.remove();
		}, 3500);
	}
}