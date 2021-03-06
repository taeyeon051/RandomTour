class UserForm {
    constructor(submitButton) {
        this.app = new App();
        this.certify = document.querySelector("#certify-number");
        this.inputIdList = ["#user-id", "#certify-number", "#user-name", "#user-nickname", "#user-pwd", "#user-pwdc"];
        this.regexList = ["userId", "certify", "username", "nickname", "password", ""];
        // 입력창 형식 확인
        this.messageList = [
            "아이디는 이메일 형식이어야 합니다.",
            "인증번호를 다시 확인해주세요.",
            "이름은 한글, 영문만 사용할 수 있으며 2글자 이상이어야 합니다.",
            "닉네임은 한글, 영문, 숫자만 사용할 수 있으며 2~16글자여야 합니다.",
            "비밀번호는 영문 대소문자, 숫자, 기호를 포함하며 10글자 이상이어야 합니다.",
            "비밀번호와 확인이 일치하지 않습니다."
        ];
        this.submitButton = submitButton;

        // 인증하기 버튼 활성화/비활성화
        this.userId = "";
        this.isSendMail = false;
    }

    // 빈 값 체크
    inputEvent() {
        const { app, certify, inputIdList, regexList } = this;
        
        inputIdList.forEach((inputId, idx) => {
            const input = document.querySelector(inputId);
            if (input) {
                input.addEventListener("input", e => {
                    if (input.id === "user-id") {
                        if (this.userId !== e.target.value) {
                            this.userId = e.target.value;
                            this.isSendMail = false;
                            $(certify).val("").removeClass("is-invalid is-valid").attr("disabled", true);
                        }
                    }
					if (input.id === "user-pwdc") {
                        app.formDataCheck(e.target, "", true);
                    } else if (input.type === "password" && document.querySelector("#user-pwdc")) {
                        app.formDataCheck(e.target, app.getRegex(regexList[idx]), true);
                    } else {
                        app.formDataCheck(e.target, app.getRegex(regexList[idx]));
                    }
                });
            }
        });
    }
    
    // form 전송
    buttonEvent() {
        const { submitButton } = this;
        window.addEventListener("keydown", e => {
            if (e.key === "Enter") this.formSubmit();
        });
        submitButton.addEventListener("click", () => {
            this.formSubmit();
        });
    }

    // 빈 값 확인 후 form 전송
    formSubmit() {
        const { app, inputIdList, messageList } = this;

        if (app.emptyCheck()) {
            return app.alert("danger", "빈 값이 있습니다.");
        }

        let inputValueCheck = false;
        inputIdList.forEach((inputId, idx) => {
            const input = document.querySelector(inputId);
            if (input && $(input).hasClass("is-invalid")) {
                inputValueCheck = true;
                app.alert("danger", messageList[idx]);
            }
        });
        
        if (inputValueCheck) return;
        
        document.querySelector("form").submit();
    }

    // 인증하기 버튼 클릭
    certifyBtnClickEvent() {
        const certifyBtn = document.querySelector("#certify-btn");
        certifyBtn.addEventListener("click", () => { this.sendMail(); });
    }

    // 메일 전송
    sendMail() {
        const { app, certify } = this;

        const userId = document.querySelector("#user-id");
        if (userId.value === "" || $(userId).hasClass("is-invalid")) {
            return app.alert("danger", "올바른 이메일 형식을 입력 후 인증해주세요.");
        }
        if (this.isSendMail) {
            return app.alert("warning", "이미 인증 메일이 전송되었습니다.");
        }

        this.isSendMail = true;

        app.alert("primary", "인증 메일이 전송 중입니다. 잠시만 기다려주세요.");

        // 메일 전송 ajax 처리
        $.ajax({
            url: "/user/sendMail",
            method: "POST",
            data: { "user-id": userId.value },
            success: data => {
                const result = app.ajaxResult(data);
                if (result) {
                    certify.disabled = false;
                } else {
                    app.alert("danger", "인증 메일 전송에 실패하였습니다. 이메일을 다시 확인해주세요.");
                    this.isSendMail = false;
                }
                
                document.querySelector(".alert").remove();
            }
        });
    }
}