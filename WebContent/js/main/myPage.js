const log = console.log;

class Mypage {
	constructor(userId) {
		this.app = new App();
		this.userId = userId;
		this.userData;
		this.username = document.querySelector("#user-name");
		this.nickname = document.querySelector("#user-nickname");
		this.password = document.querySelector("#user-pwd");
		this.passwordCheck = document.querySelector("#user-pwdc");

		this.init();
		this.menuClickEvent();
		this.updateUserFormEvent();
		this.friendEvent();
	}

	init() {
		const { username, nickname } = this;
		this.userData = { "username": username.value, "nickname": nickname.value };
	}

	menuClickEvent() {
		const domList = document.querySelectorAll("#friend-main>div");
		const menuList = document.querySelectorAll("#mypage-main>ul>li:not(:first-child)");
		menuList.forEach(menu => {
			menu.addEventListener("click", e => {
				const domId = e.target.dataset.id;
				$(menuList).removeClass("text-blue");
				$(e.target).addClass("text-blue");
				$(domList).removeClass("d-block").addClass("d-none");
				$(`#${domId}`).removeClass("d-none").addClass("d-block");
			});
		});

		const chatList = document.querySelectorAll("#friend-chatting-list>div");
		chatList.forEach(chat => {
			chat.addEventListener("click", e => {
				$(menuList).removeClass("text-blue");
				$(domList).removeClass("d-block").addClass("d-none");
				$("#friend-chatting").removeClass("d-none").addClass("d-block");
			});
		});
	}

	updateUserFormEvent() {
		const { app, userId, userData, username, nickname, password, passwordCheck } = this;
		const updateBtn = document.querySelector("#user-update-btn");

		username.addEventListener("input", e => {
			app.formDataCheck(e.target, app.getRegex("username"));
			if (e.target.value === userData.username) $(e.target).removeClass("is-invalid is-valid");
		});
		nickname.addEventListener("input", e => {
			app.formDataCheck(e.target, app.getRegex("nickname"));
			if (e.target.value === userData.nickname) $(e.target).removeClass("is-invalid is-valid");
		});
		password.addEventListener("input", e => { app.formDataCheck(e.target, app.getRegex("password"), true); });
		passwordCheck.addEventListener("input", e => { app.formDataCheck(e.target, "", true); });

		updateBtn.addEventListener("click", () => {
			this.updateUserCheck();
			this.changePassword();
		});
	}

	updateUserCheck() {
		const { app, userData, username, nickname } = this;
		if (username.value === userData.username && nickname.value === userData.nickname) return;
		if (app.emptyCheck([username, nickname])) return app.alert("danger", "빈 값이 있습니다.");
		if ($(username).hasClass("is-invalid")) return app.alert("danger", "이름은 한글, 영문만 사용할 수 있으며 2글자 이상이어야 합니다.");
		if ($(nickname).hasClass("is-invalid")) return app.alert("danger", "닉네임은 한글, 영문, 숫자만 사용할 수 있으며 2~16글자여야 합니다.");
	}

	changePassword() {

	}

	friendEvent() {
		const sendBtn = document.querySelector("#send-btn");
		sendBtn.addEventListener("click", () => { this.addFriend(); });
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
				const div = document.createElement("div");
				div.innerHTML = data;
				document.body.appendChild(div);
				if (div.querySelector("alert-success")) {
					const tr = document.createElement("tr");
					tr.innerHTML = `<td class="nickname px-3">${nickname.value}</td>`;
					table.prepend(tr);
				}
				setTimeout(() => {
					div.remove();
				}, 3500);
			}
		});
	}
}