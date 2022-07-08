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
	}

	init() {
		const { username, nickname } = this;
		this.userData = { "username": username.value, "nickname": nickname.value };
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
}