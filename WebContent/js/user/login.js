window.onload = () => {
    const login = new Login();
}

class Login {
    constructor() {
        this.app = new App();

        this.userId = document.querySelector("#user-id");
        this.password = document.querySelector("#user-pwd");
        this.loginBtn = document.querySelector("#login-btn");

        this.init();
        this.addEvent();
    }

    init() {
        this.app.urlMapping('/logout', '/login');
    }

    addEvent() {
        const { app, userId, password, loginBtn } = this;

        userId.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('userId')); });
        password.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('password')); });
        window.addEventListener("keydown", e => { if (e.keyCode == 13) this.formSubmit(); });
        loginBtn.addEventListener("click", e => { this.formSubmit(); });
    }

    formSubmit() {
        const { app, userId, password } = this;
        app.formSubmit([userId, password], "form");
    }
}