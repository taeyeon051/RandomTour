// 뒤로가기 버튼 방지
history.pushState(null, null, location.href);
window.onpopstate = () => history.go(1);

class App {
    // URL 맵핑
    urlMapping(check, go) {
        // 현재 URL 가져오기
        const url = location.href;
        // 알림창이 있는지 체크
        const isAlert = document.querySelector(".alert");
        if (url.indexOf(check) !== -1 && isAlert) {
            setTimeout(() => {
                location.href = url.replace(check, go);
            }, 500);
        } 
    }

    // 알림창
    alert(state, text) {
        let alertBox = document.querySelector(".alert");
        if (alertBox) alertBox.remove();
        alertBox = document.createElement("div");
        alertBox.classList.add("alert", `alert-${state}`, "alert-dismissible", "fade", "show");
        alertBox.setAttribute("role", "alert");
        alertBox.innerHTML = `${text} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;
        document.body.appendChild(alertBox);
        setTimeout(() => {
            alertBox.remove();
        }, 3500);
    }

    // 정규표현식
    getRegex(type) {
        const regexList = {
            'space': /\s/g,
            'userId': /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g,
            'certify': /[0-9]{6}/g,
            'username': /[가-힣a-zA-Z]{2,}/g,
            'nickname': /[A-Za-z0-9가-힣]{2,16}/g,
            'password': /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g,
        };
        return regexList[type];
    }

    // 입력값 체크
    formDataCheck(input, regex, passwordFocus) {
        const value = input.value;
        input.value = value.replace(this.getRegex(space), "");
        if (passwordFocus) {
            const password = document.querySelector("#user-pwd").value;
            const passwordCheck = document.querySelector("#user-pwdc").value;
            if (password === passwordCheck && passwordCheck !== "") {
                passwordCheck.classList.replace("is-invalid", "is-valid");
            } else {
                passwordCheck.classList.replace("is-valid", "is-invalid");
            }
        }
        if (regex !== "") {
            if (value === "" || value.match(regex) === null || value.match(regex)[0] !== value) {
                input.classList.replace("is-valid", "is-invalid");
            } else {
                input.classList.replace("is-invalid", "is-valid");
            }
        }
    }
}