window.onload = () => {
    const app = new App();

    const joinBtn = document.querySelector("#join-btn");
    new UserForm(joinBtn).addEvent();

    const userId = document.querySelector("#user-id");
    const certify = document.querySelector("#certify-number");
    const certifyBtn = document.querySelector("#certify-btn");

    // 인증하기 버튼 활성화/비활성화
    let isSendMail = false;

    // 인증하기 버튼 클릭 이벤트
    certifyBtn.addEventListener("click", () => {
        sendMail();
    });

    function sendMail() {
        if (userId.value === "" || userId.classList.contains("is-invalid")) {
            return app.alert("danger", "올바른 이메일 형식을 입력 후 인증해주세요.");
        }
        if (isSendMail) {
            return app.alert("warning", "이미 인증메일이 전송되었습니다.");
        }

        isSendMail = true;
        
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
                    isSendMail = false;
                }
            }
        });
    }
}
