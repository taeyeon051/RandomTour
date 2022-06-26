const app = new App();

// 아이디 찾기 버튼 활성화/비활성화
let findIdBtnClick = false;
// 비밀번호 변경시 필요한 유저 아이디
let userIdValue = "";

window.onload = () => {
    tabClickEvent();
    
    const findIdBtn = document.querySelector("#find-id-btn");
    const findPwBtn = document.querySelector("#find-pw-btn");

    findIdBtn.addEventListener("click", () => { findId(); });
    findPwBtn.addEventListener("click", () => { certify(); });

    new UserForm().inputEvent();

    // 인증하기 버튼 클릭 이벤트
    new UserForm().certifyBtnClickEvent();
}

// 아이디 찾기
function findId() {
    const userName = document.querySelector("#user-name");
    const nickname = document.querySelector("#user-nickname");

    if (app.emptyCheck([userName, nickname])) return app.alert("danger", "빈 값이 있습니다.");
    if (document.querySelector(".is-invalid")) return app.alert("danger", "잘못된 값이 있습니다.");
    if (findIdBtnClick) return app.alert("warning", "버튼은 한 번만 클릭해주세요.");

    findIdBtnClick = true;
    const formData = { "type": "id", "user-name": userName.value, "nickname": nickname.value };
    $.ajax({
        url: "/user/find",
        method: "POST",
        data: formData,
        success: data => {
            const div = document.createElement("div");
            div.innerHTML = data;
            const result = div.querySelector("#result");
            if (result.innerHTML === "성공") {
                const toast = div.querySelector(".toast");
                toast.classList.add("show");
                document.body.appendChild(toast);
                setTimeout(() => { toast.remove(); }, 12000);
            } else {
                app.alert("danger", "아이디가 존재하지 않습니다.");
            }

            setTimeout(() => { findIdBtnClick = false; }, 5000);
        }
    });
}

// 비밀번호 찾기 이메일 인증
function certify() {
    const userId = document.querySelector("#user-id");
    const certifyNumber = document.querySelector("#certify-number");

    if (app.emptyCheck([userId, certifyNumber])) return app.alert("danger", "빈 값이 있습니다.");
    if (document.querySelector(".is-invalid")) return app.alert("danger", "잘못된 값이 있습니다.");
    
    const formData = { "type": "pw", "user-id": userId.value, "certify-number": certifyNumber.value };
    $.ajax({
        url: "/user/find",
        method: "POST",
        data: formData,
        success: data => {
            const div = document.createElement("div");
            div.innerHTML = data;
            const result = div.querySelector("#result");

            if (result.innerHTML === "성공") {
                result.remove();
                const findPw = document.querySelector("#find-pw");
                userIdValue = userId.value;
                findPw.querySelector("form").remove();
                findPw.appendChild(div.querySelector("form"));
                updatePwEvent();
                app.alert("success", "인증되었습니다. 새비밀번호를 입력해주세요.");
            } else {
                app.alert("danger", "인증번호를 다시 확인해주세요.");
            }
        }
    });
}

// 비밀번호 변경
function updatePwEvent() {
    const password = document.querySelector("#user-pwd");
    const passwordCheck = document.querySelector("#user-pwdc");
    const updatePwBtn = document.querySelector("#update-pw-btn");
    
    new UserForm().inputEvent();

    updatePwBtn.addEventListener("click", () => {
        const form = document.querySelector("#find-pw>form");
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "user-id";
        input.value = userIdValue;
        form.appendChild(input);
        if (app.emptyCheck([password, passwordCheck])) return app.alert("danger", "빈 값이 있습니다.");
        if (document.querySelector(".is-invalid")) return app.alert("danger", "잘못된 값이 있습니다.");
        form.submit();
    });
}

// 탭 클릭 이벤트
function tabClickEvent() {
    $("#nav-id-tab").hover(() => {
        borderRadius(true);
    }, () => {
        borderRadius($("#nav-id-tab").hasClass("active"));
    });
    $(".nav-link").click(e => {
        $("input").val("").removeClass("is-invalid is-valid");
        const tabPaneId = $(e.target).attr("id");
        if (tabPaneId === "nav-id-tab") {
            borderRadius(true);
            $("#find-id").addClass("show active");
            $("#find-pw").removeClass("show active")
        }
        if (tabPaneId === "nav-pw-tab") {
            borderRadius(false);
            $("#find-id").removeClass("show active");
            $("#find-pw").addClass("show active");
        }
    });
}

function borderRadius(isIdTab) {
    if (isIdTab) $(".tab-content").css("border-top-left-radius", "0");
    else $(".tab-content").css("border-top-left-radius", "0.375rem");
}