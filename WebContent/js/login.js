window.onload = () => {
    const login = new Login();
}

class Login {
    constructor() {
        this.userId = document.querySelector("#user-id");
        this.password = document.querySelector("#user-pwd");
        this.loginBtn = document.querySelector("#login-btn");
        this.userIdRegex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g;
        this.passwordRegex = /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g;

        this.init();
        this.addEvent();
    }

    init() {
        new App().urlMapping('/logout', '/login');
    }

    addEvent() {
        const { userIdRegex, passwordRegex } = this;
        const { userId, password, loginBtn } = this;

        userId.addEventListener("input", e => { this.dataCheck(e.target, userIdRegex); });
        password.addEventListener("input", e => { this.dataCheck(e.target, passwordRegex); });
        window.addEventListener("keydown", e => { if (e.keyCode == 13) this.formSubmit(); });
        loginBtn.addEventListener("click", e => { this.formSubmit(); });
    }

    formSubmit() {
        const { userId, password } = this;
        if (userId.value == "" || password.value == "") return new Alert('danger', '빈 값이 있습니다.');
        if (document.querySelector(".is-invalid")) return new Alert('danger', '잘못된 값이 있습니다.');
        document.querySelector("form").submit();
    }

    dataCheck(dom, regex) {
        const value = dom.value;
        dom.value = dom.value.replace(/\s/g, "");
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