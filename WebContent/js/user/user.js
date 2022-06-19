class UserForm {
    constructor(submitButton) {
        this.app = new App();
        this.inputIdList = ["#user-id", "#certify-number", "#user-name", "#user-nickname", "#user-pwd", "#user-pwdc"];
        this.regexList = ["userId", "certify", "username", "nickname", "password", ""];
        this.submitButton = submitButton;

        // 인증하기 버튼 활성화/비활성화
        this.isSendMail = false;
    }

    inputEvent() {
        const { app, inputIdList, regexList } = this;
        
        inputIdList.forEach((inputId, idx) => {
            const input = document.querySelector(inputId);
            if (input) {
                input.addEventListener("input", e => {
					if (input.id === "user-pwdc") {
                        app.formDataCheck(e.target, "", true);
                    } else if (input.type === "password" && location.href.indexOf("/login") < 0) {
                        app.formDataCheck(e.target, app.getRegex(regexList[idx]), true);
                    } else {
                        app.formDataCheck(e.target, app.getRegex(regexList[idx]));
                    }
                });
            }
        });
    }
    
    buttonEvent() {
        const { app, submitButton } = this;
        window.addEventListener("keydown", e => {
            if (e.key === "Enter") app.formSubmit();
        });
        submitButton.addEventListener("click", () => {
            app.formSubmit();
        });
    }

    certifyBtnClickEvent() {
        const certifyBtn = document.querySelector("#certify-btn");
        certifyBtn.addEventListener("click", () => { this.sendMail(); });
    }

    sendMail() {
        const { app } = this;

        const userId = document.querySelector("#user-id");
        const certify = document.querySelector("#certify-number");
        if (userId.value === "" || userId.classList.contains("is-invalid")) {
            return app.alert("danger", "올바른 이메일 형식을 입력 후 인증해주세요.");
        }
        if (this.isSendMail) {
            return app.alert("warning", "이미 인증메일이 전송되었습니다.");
        }

        this.isSendMail = true;

        $.ajax({
            url: "/user/sendMail",
            method: "POST",
            data: { "user-id": userId.value },
            success: data => {
                const result = app.ajaxResult(data);
                if (result) {
                    certify.disabled = false;
                } else {
                    app.alert("danger", "인증메일 전송에 실패하였습니다. 이메일을 다시 확인해주세요.");
                    this.isSendMail = false;
                }
            }
        });
    }
}