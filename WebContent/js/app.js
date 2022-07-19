// css 로드
const url = location.href;
let link = `<link rel="stylesheet" href="/css/`;
if (url.indexOf("user") !== -1) link += `user.css">`;
else if (url.indexOf("mypage") !== -1) link += `mypage.css">`;
else if (url.indexOf("main") !== -1) link += `main.css">`;
document.head.innerHTML += link;

// App
class App {
    // 알림창
    alert(state, text) {
        let alertBox = document.querySelector(".alert");
        if (alertBox) return;
        alertBox = document.createElement("div");
        $(alertBox).addClass(`alert alert-${state} alert-dismissible fade show`);
        alertBox.setAttribute("role", "alert");
        alertBox.innerHTML = `${text} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;
        document.body.appendChild(alertBox);
        setTimeout(() => {
            if (state !== "primary") alertBox.remove();
        }, 3500);
    }

    // 정규표현식
    getRegex(type) {
        const regexList = {
            "space": /\s/g,
            "userId": /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g,
            "certify": /[0-9]{6}/g,
            "username": /[가-힣a-zA-Z]{2,}/g,
            "nickname": /[A-Za-z0-9가-힣]{2,16}/g,
            "password": /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g,
        };
        return regexList[type];
    }

    // 입력값 체크
    formDataCheck(input, regex, passwordFocus) {
        const value = input.value;
        input.value = value.replace(this.getRegex("space"), "");
        if (passwordFocus) {
            const password = document.querySelector("#user-pwd");
            const passwordCheck = document.querySelector("#user-pwdc");
            if (password.value === passwordCheck.value && passwordCheck.value !== "") {
                $(passwordCheck).removeClass("is-invalid").addClass("is-valid");
            } else if (passwordCheck.value !== "") {
                $(passwordCheck).removeClass("is-valid").addClass("is-invalid");
            }
        }
        if (regex !== "") {
            if (value === "" || value.match(regex) === null || value.match(regex)[0] !== value) {
                $(input).removeClass("is-valid").addClass("is-invalid");
            } else {
                $(input).removeClass("is-invalid").addClass("is-valid");
            }
        }
    }

    // 빈 값 확인
	emptyCheck(domList = []) {
        let result = false;
		const inputList = domList.length > 0 ? domList : document.querySelectorAll("input");
        inputList.forEach(input => {
            if (input.value === "") result = true;
        });
		return result;
	}

    // ajax 전송 결과 확인
    ajaxResult(data) {
        const div = document.createElement("div");
        div.innerHTML = data;
        const result = div.querySelector("#result").innerHTML === "성공";
        return result;
    }
}