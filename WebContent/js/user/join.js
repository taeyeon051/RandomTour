window.onload = () => {
    const join = new Join();
}

class Join {
    constructor() {
        this.userId = document.querySelector("#user-id");
        this.certify = document.querySelector("#certify-number");
        this.username = document.querySelector("#user-name");
        this.nickname = document.querySelector("#user-nickname");
        this.password = document.querySelector("#user-pwd");
        this.passwordCheck = document.querySelector("#user-pwdc");
        this.joinBtn = document.querySelector("#join-btn");
        this.certifyBtn = document.querySelector("#certify-btn");

        // 인증코드
        this.code = "";
        // 인증하기 버튼 활성화/비활성화
        this.isSendMail = false;

        // 정규표현식
        this.userIdRegex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g;
        this.certifyRegex = /[0-9]{6}/g;
        this.usernameRegex = /[가-힣a-zA-Z]{2,}/g;
        this.nicknameRegex = /[A-Za-z0-9가-힣]{2,16}/g;
        this.passwordRegex = /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g;

        this.addEvent();
    }

    addEvent() {
        const { userIdRegex, certifyRegex, usernameRegex, nicknameRegex, passwordRegex } = this;
        const { userId, certify, username, nickname, password, passwordCheck, joinBtn, certifyBtn } = this;

        // 입력값 체크
        userId.addEventListener("input", e => { this.dataCheck(e.target, userIdRegex); });
        certify.addEventListener("input", e => { this.dataCheck(e.target, certifyRegex); });
        username.addEventListener("input", e => { this.dataCheck(e.target, usernameRegex); });
        nickname.addEventListener("input", e => { this.dataCheck(e.target, nicknameRegex); });
        password.addEventListener("input", e => { this.dataCheck(e.target, passwordRegex, 'pwd'); });
        passwordCheck.addEventListener("input", e => { this.dataCheck(e.target, '', 'pwd'); });
        // 회원가입 버튼 클릭
        window.addEventListener("keydown", e => { if (e.keyCode == 13) this.formSubmit(); });
        joinBtn.addEventListener("click", e => { this.formSubmit(); });
        // 인증하기 버튼 클릭
        certifyBtn.addEventListener("click", e => { this.sendMail(); });
    }

    sendMail() {
        const { userId, isSendMail } = this;
        if (userId.value == "" || $(userId).hasClass(".is-invalid")) return new App().alert('danger', '올바른 이메일 형식을 입력 후 인증해주세요.');
        if (isSendMail) return new App().alert('warning', '이미 인증메일이 전송되었습니다.');
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
                    this.code = result.innerHTML;
                } else {
                    new App().alert('danger', '인증메일 전송에 실패하였습니다. 이메일을 다시 확인해주세요.');
                    this.isSendMail = false;
                }
            }
        });
    }

    formSubmit() {
        const { userId, certify, username, nickname, password, passwordCheck, code } = this;
        if (userId.value == "" || certify.value == "" || username.value == "" || nickname.value == "" || password.value == "" || passwordCheck.value == "") {
            return new App().alert('danger', '빈 값이 있습니다.');
        }
        if (certify.value !== code) return new App().alert('danger', '인증번호가 틀렸습니다.');
        if (document.querySelector(".is-invalid")) return new App().alert('danger', '잘못된 값이 있습니다.');
        document.querySelector("form").submit();
    }

    dataCheck(dom, regex, pwd) {
        const value = dom.value;
        dom.value = dom.value.replace(/\s/g, "");
        if (pwd === 'pwd') {
            const { password, passwordCheck } = this;
            if (password.value === passwordCheck.value && passwordCheck.value != "") {
                passwordCheck.classList.remove('is-invalid');
                passwordCheck.classList.add('is-valid');
            } else {
                passwordCheck.classList.remove('is-valid');
                passwordCheck.classList.add('is-invalid');
            }
        }
        if (regex !== "") {
            if (value == "" || value.match(regex) == null || value.match(regex)[0] != value) {
                dom.classList.remove('is-valid');
                dom.classList.add('is-invalid');
            } else {
                dom.classList.remove('is-invalid');
                dom.classList.add('is-valid');
            }
        }
    }
}