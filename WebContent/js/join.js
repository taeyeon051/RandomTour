const log = console.log;

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
        this.userIdRegex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}/g;
        this.nicknameRegex = /[A-Za-z0-9가-힣]{2,16}/g;
        this.passwordRegex = /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g;

        this.addEvent();
    }

    addEvent() {
        const { userIdRegex, nicknameRegex, passwordRegex } = this;
        const { userId, nickname, password, passwordCheck, joinBtn } = this;

        userId.addEventListener("input", e => { this.dataCheck(e, userIdRegex); });
        userId.addEventListener("change", e => { this.removeSpace(e); });

    }

    dataCheck(dom, regex) {
        log(dom, regex);
    }

    removeSpace(e) {
        e.target.value = e.target.value.replace(/\s/g, "");
    }
}