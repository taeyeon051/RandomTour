window.onload = () => {
    const find = new Find();
}

class Find {
    constructor() {
        // 아이디 찾기
        this.username = document.querySelector("#user-name");
        this.nickname = document.querySelector("#user-nickname");
        this.findIdBtn = document.querySelector("#find-id-btn");
        this.usernameRegex = /[가-힣a-zA-Z]{2,}/g;
        this.nicknameRegex = /[A-Za-z0-9가-힣]{2,16}/g;
        this.findIdBtnClick = false;

        // 비밀번호 찾기 (이메일 인증)
        this.userId = document.querySelector("#user-id");
        this.certify = document.querySelector("#certify-number");
        this.certifyBtn = document.querySelector("#certify-btn");
        this.findPwBtn = document.querySelector("#find-pw-btn");
        this.userIdRegex = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g;
        this.certifyRegex = /[0-9]{6}/g;

        // 인증하기 버튼 활성화/비활성화
        this.isSendMail = false;

        // 비밀번호 변경
        this.passwordRegex = /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g;
        this.userIdValue = "";

        this.addEvent();
    }

    addEvent() {
        // 아이디 찾기
        const { username, nickname, findIdBtn, certifyBtn } = this;
        const { usernameRegex, nicknameRegex } = this;
        username.addEventListener("input", e => { this.dataCheck(e.target, usernameRegex); });
        nickname.addEventListener("input", e => { this.dataCheck(e.target, nicknameRegex); });
        findIdBtn.addEventListener("click", e => {
            if (this.findIdBtnClick) return new App().alert('warning', '버튼은 한 번만 클릭해주세요.');
            this.findIdBtnClick = true;
            this.findId();
        });

        // 비밀번호 찾기 (이메일 인증)
        const { userId, certify, findPwBtn } = this;
        const { userIdRegex, certifyRegex } = this;
        userId.addEventListener("input", e => { this.dataCheck(e.target, userIdRegex); });
        certify.addEventListener("input", e => { this.dataCheck(e.target, certifyRegex); });
        certifyBtn.addEventListener("click", e => { this.sendMail(); });
        findPwBtn.addEventListener("click", e => { this.findPw(); });

        // 탭 클릭 이벤트
        $('#nav-id-tab').hover(e => {
            this.borderRadius(true);
        }, e => {
            if (!$('#nav-id-tab').hasClass('active')) this.borderRadius(false);
        });
        $('.nav-link').click(e => {
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
        const { username, nickname } = this;
        if (username.value == "" || nickname.value == "") return new App().alert('danger', '빈 값이 있습니다.');
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
                    new App().alert('danger', '아이디가 존재하지 않습니다.')
                }

                setTimeout(() => { this.findIdBtnClick = false; }, 5000);
            }
        });
    }

    findPw() {
        const { userId, certify } = this;
        if (userId.value == "" || certify.value == "") return new App().alert('danger', '빈 값이 있습니다.');
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
                    new App().alert('success', '인증되었습니다. 새비밀번호를 입력해주세요.');
                } else {
                    new App().alert('danger', '인증번호를 다시 확인해주세요.');
                }
            }
        });
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
                } else {
                    new App().alert('danger', '인증메일 전송에 실패하였습니다. 이메일을 다시 확인해주세요.');
                    this.isSendMail = false;
                }
            }
        });
    }

    updatePwdEvent() {
        // 비밀번호 변경
        const password = document.querySelector("#user-pwd");
        const passwordCheck = document.querySelector("#user-pwdc");
        const updatePwBtn = document.querySelector("#update-pw-btn");
        const passwordRegex = this.passwordRegex;
        password.addEventListener("input", e => { this.dataCheck(e.target, passwordRegex, 'pwd'); });
        passwordCheck.addEventListener("input", e => { this.dataCheck(e.target, '', 'pwd'); });
        updatePwBtn.addEventListener("click", e => {
            if (password.value == "" || passwordCheck == "") {
                return new App().alert('danger', '빈 값이 있습니다.');
            }
            if (document.querySelector("#find-pw .is-invalid")) return new App().alert('danger', '잘못된 값이 있습니다.');
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "user-id";
            input.value = this.userIdValue;
            document.querySelector("#find-pw>form").appendChild(input);
            document.querySelector("#find-pw>form").submit();
        });
    }

    dataCheck(dom, regex, pwd) {
        const value = dom.value;
        dom.value = dom.value.replace(/\s/g, "");
        if (pwd === 'pwd') {
            const password = document.querySelector("#user-pwd");
            const passwordCheck = document.querySelector("#user-pwdc");
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

    borderRadius(bool) {
        if (bool) {
            $('.tab-content').css('border-top-left-radius', '0');
        } else {
            $('.tab-content').css('border-top-left-radius', '0.375rem');
        }
    }
}