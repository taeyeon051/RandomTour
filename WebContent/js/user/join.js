window.onload = () => {
    const join = new Join();
}

class Join {
    constructor() {
        this.userId = document.querySelector("#user-id");
        this.nickname = document.querySelector("#user-nickname");
        this.password = document.querySelector("#user-pwd");
        this.passwordCheck = document.querySelector("#user-pwdc");
        this.joinBtn = document.querySelector("#join-btn");
        this.userIdRegex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g;
        this.nicknameRegex = /[A-Za-z0-9가-힣]{2,16}/g;
        this.passwordRegex = /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g;

        this.addEvent();
    }

    addEvent() {
        const { userIdRegex, nicknameRegex, passwordRegex } = this;
        const { userId, nickname, password, passwordCheck, joinBtn } = this;

        userId.addEventListener("input", e => { this.dataCheck(e.target, userIdRegex); });
        nickname.addEventListener("input", e => { this.dataCheck(e.target, nicknameRegex); });
        password.addEventListener("input", e => { this.dataCheck(e.target, passwordRegex, 'pwd'); });
        passwordCheck.addEventListener("input", e => { this.dataCheck(e.target, '', 'pwd'); });
        window.addEventListener("keydown", e => { if (e.keyCode == 13) this.formSubmit(); });
        joinBtn.addEventListener("click", e => { this.formSubmit(); });
    }

    formSubmit() {
        const { userId, nickname, password, passwordCheck } = this;
        if (userId.value == "" || nickname.value == "" || password.value == "" || passwordCheck.value == "") return new App().alert('danger', '빈 값이 있습니다.');
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