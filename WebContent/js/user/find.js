window.onload = () => {
    const find = new Find();
}

class Find {
    constructor() {
        this.app = new App();

        // 아이디 찾기
        this.username = document.querySelector("#user-name");
        this.nickname = document.querySelector("#user-nickname");
        this.findIdBtn = document.querySelector("#find-id-btn");
        this.findIdBtnClick = false;

        // 비밀번호 찾기 (이메일 인증)
        this.userId = document.querySelector("#user-id");
        this.certify = document.querySelector("#certify-number");
        this.certifyBtn = document.querySelector("#certify-btn");
        this.findPwBtn = document.querySelector("#find-pw-btn");

        // 인증하기 버튼 활성화/비활성화
        this.isSendMail = false;

        // 비밀번호 변경
        this.userIdValue = "";

        this.addEvent();
    }

    addEvent() {
        const app = this.app;

        // 아이디 찾기
        const { username, nickname, findIdBtn, certifyBtn } = this;
        username.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('username')); });
        nickname.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('nickname')); });
        findIdBtn.addEventListener("click", e => { this.findId(); });

        // 비밀번호 찾기 (이메일 인증)
        const { userId, certify, findPwBtn } = this;
        userId.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('userId')); });
        certify.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('certify')); });
        certifyBtn.addEventListener("click", e => { this.sendMail(); });
        findPwBtn.addEventListener("click", e => { this.findPw(); });

        // 탭 클릭 이벤트
        $('#nav-id-tab').hover(e => {
            this.borderRadius(true);
        }, e => {
            if (!$('#nav-id-tab').hasClass('active')) this.borderRadius(false);
        });
        $('.nav-link').click(e => {
            $('input').val("").removeClass("is-invalid is-valid");
            if ($(e.target).attr('id') == "nav-id-tab") {
                this.borderRadius(true);
                $('#find-id').addClass('show active');
                $('#find-pw').removeClass('show active')
            }
            if ($(e.target).attr('id') == "nav-pw-tab") {
                this.borderRadius(false);
                $('#find-id').removeClass('show active');
                $('#find-pw').addClass('show active');
            }
        });
    }

    findId() {
        const { app, username, nickname } = this;
        if (app.emptyCheck([username, nickname])) return app.alert('danger', '빈 값이 있습니다.');
        if (document.querySelector(".is-invalid")) return app.alert('danger', '잘못된 값이 있습니다.');
        if (this.findIdBtnClick) return app.alert('warning', '버튼은 한 번만 클릭해주세요.');
        this.findIdBtnClick = true;
        const formData = { "type": "id", "user-name": username.value, "nickname": nickname.value };
        $.ajax({
            url: "/user/find",
            method: "POST",
            data: formData,
            success: e => {
                const div = document.createElement("div");
                div.innerHTML = e;
                const result = div.querySelector("#result");

                if (result.innerHTML == "true") {
                    const toast = div.querySelector(".toast");
                    $(toast).addClass('show');
                    document.querySelector("body").appendChild(toast);
                    setTimeout(() => { toast.remove(); }, 15000);
                } else {
                    app.alert('danger', '아이디가 존재하지 않습니다.')
                }

                setTimeout(() => { this.findIdBtnClick = false; }, 5000);
            }
        });
    }

    findPw() {
        const { app, userId, certify } = this;
        if (app.emptyCheck([userId, certify])) return app.alert('danger', '빈 값이 있습니다.');
        if (document.querySelector(".is-invalid")) return app.alert('danger', '잘못된 값이 있습니다.');
        const formData = { "type": "pw", "user-id": userId.value, "certify-number": certify.value };
        $.ajax({
            url: "/user/find",
            method: "POST",
            data: formData,
            success: e => {
                const div = document.createElement("div");
                div.innerHTML = e;
                const result = div.querySelector("#result");

                if (result.innerHTML == "성공") {
                    result.remove();
                    const findPw = document.querySelector("#find-pw");
                    this.userIdValue = userId.value;
                    findPw.querySelector("form").remove();
                    findPw.appendChild(div.querySelector("form"));
                    this.updatePwdEvent();
                    app.alert('success', '인증되었습니다. 새비밀번호를 입력해주세요.');
                } else {
                    app.alert('danger', '인증번호를 다시 확인해주세요.');
                }
            }
        });
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

    updatePwdEvent() {
        const app = this.app;

        // 비밀번호 변경
        const password = document.querySelector("#user-pwd");
        const passwordCheck = document.querySelector("#user-pwdc");
        const updatePwBtn = document.querySelector("#update-pw-btn");
        password.addEventListener("input", e => { app.dataCheck(e.target, app.getRegex('password'), 'pwd'); });
        passwordCheck.addEventListener("input", e => { app.dataCheck(e.target, '', 'pwd'); });
        updatePwBtn.addEventListener("click", e => {
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "user-id";
            input.value = this.userIdValue;
            document.querySelector("#find-pw>form").appendChild(input);
            app.formSubmit([password, passwordCheck], "#find-pw>form");
        });
    }

    borderRadius(bool) {
        if (bool) {
            $('.tab-content').css('border-top-left-radius', '0');
        } else {
            $('.tab-content').css('border-top-left-radius', '0.375rem');
        }
    }
}