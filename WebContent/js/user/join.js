window.onload = () => {
    const join = new Join();
}

class Join {
    constructor() {
        this.app = new App();

        this.userId = document.querySelector("#user-id");
        this.certify = document.querySelector("#certify-number");
        this.username = document.querySelector("#user-name");
        this.nickname = document.querySelector("#user-nickname");
        this.password = document.querySelector("#user-pwd");
        this.passwordCheck = document.querySelector("#user-pwdc");
        this.joinBtn = document.querySelector("#join-btn");
        this.certifyBtn = document.querySelector("#certify-btn");

        // 인증하기 버튼 활성화/비활성화
        this.isSendMail = false;

        this.addEvent();
    }

    addEvent() {
        const { app, userId, certify, username, nickname, password, passwordCheck, joinBtn, certifyBtn } = this;

        // 입력값 체크
        userId.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('userId')); });
        certify.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('certify')); });
        username.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('username')); });
        nickname.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('nickname')); });
        password.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('password'), 'pwd'); });
        passwordCheck.addEventListener("input", e => { app.dataCheck(e.target, '', 'pwd'); });
        // 회원가입 버튼 클릭
        window.addEventListener("keydown", e => { if (e.keyCode == 13) this.formSubmit(); });
        joinBtn.addEventListener("click", e => { this.formSubmit(); });
        // 인증하기 버튼 클릭
        certifyBtn.addEventListener("click", e => { this.sendMail(); });
    }

    sendMail() {
        const { app, userId, isSendMail } = this;
        if (userId.value == "" || $(userId).hasClass("is-invalid")) return app.alert('danger', '올바른 이메일 형식을 입력 후 인증해주세요.');
        if (isSendMail) return app.alert('warning', '이미 인증메일이 전송되었습니다.');
        this.isSendMail = true;
        const formData = { "user-id": userId.value };
        $.ajax({
            url: "/user/sendMail",
            method: "POST",
            data: formData,
            success: e => {
                const div = document.createElement("div");
                div.innerHTML = e;
                const result = div.querySelector("#result");
                if (result.innerHTML != "실패") {
                    document.querySelector("#certify-number").disabled = false;
                } else {
                    app.alert('danger', '인증메일 전송에 실패하였습니다. 이메일을 다시 확인해주세요.');
                    this.isSendMail = false;
                }
            }
        });
    }

    formSubmit() {
        const { app, userId, certify, username, nickname, password, passwordCheck } = this;
        app.formSubmit([userId, certify, username, nickname, password, passwordCheck], "form");
    }
}