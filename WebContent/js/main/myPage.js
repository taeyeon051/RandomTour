const log = console.log;

class Mypage {
	constructor(userId) {
		this.app = new App();
		this.url = new URL(location.href);

		this.userId = userId;
		this.userData;

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
		}
	}

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

	deleteFriend(nickname) {
		if (!confirm("정말 삭제하시겠습니까?")) return;
		const { userId } = this;
		$.ajax({
			url: "/main/friend/delete",
			type: "POST",
			data: { "user-id": userId, "user-nickname": nickname.name },
			success: data => {
				this.ajaxAlert(data, "accept", nickname.btn.parentElement);
			}
		});
	}

	chatEvent() {
		const chatList = document.querySelectorAll("#friend-chatting-list>div");
		chatList.forEach(chat => {
			chat.addEventListener("click", () => {
				$(menuList).removeClass("text-blue");
				$(domList).removeClass("d-block").addClass("d-none");
				$("#friend-chatting").removeClass("d-none").addClass("d-block");
			});
		});
	}

	getNicknameList() {
		const { username, nickname } = this;
		this.userData = { "username": username.value, "nickname": nickname.value };

		const sendTable = document.querySelectorAll("#friend-send tbody>tr");
		sendTable.forEach(tr => {
			const name = tr.querySelector(".nickname").innerHTML;
			const btnDom = tr.querySelector(".send-btn");
			this.nicknameList.push({ "name": name, "btnDom": btnDom });
		});
	}

	updateUserFormEvent() {
		const userForm = new UserForm();
		userForm.inputEvent();
		userForm.certifyBtnClickEvent();

		const updateBtn = document.querySelector("#user-update-btn");
		updateBtn.addEventListener("click", () => {

		});
		const inputList = document.querySelectorAll("#mypage-user-info input");
		inputList.forEach(input => {
			input.addEventListener("focus", e => {
				log(e);
			});
		});
	}

	addFriend() {
		const { app, userId } = this;
		const nickname = document.querySelector("#send-nickname");

		if (nickname.value.trim() === "") return;
		if (nickname.value.match(app.getRegex("nickname")) === null || nickname.value.match(app.getRegex("nickname"))[0] !== nickname.value) return;

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
			data: { "user-id": userId, "user-nickname": nickname.value },
			success: data => {
				this.ajaxAlert(data, "add");
			}
		});
	}

	acceptFriend(nickname, accept) {
		const { userId } = this;
		$.ajax({
			url: "/main/friend/accept",
			type: "POST",
			data: { "user-id": userId, "user-nickname": nickname.name, "accept": accept },
			success: data => {
				this.ajaxAlert(data, "accept", nickname.btnDom.parentElement);
			}
		});
	}

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