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
        if (alertBox) return;
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
                passwordCheck.classList.remove('is-invalid');
                passwordCheck.classList.add('is-valid');
            } else {
                passwordCheck.classList.remove('is-valid');
                passwordCheck.classList.add('is-invalid');
            }
        }
        if (regex !== "") {
            if (value === "" || value.match(regex) === null || value.match(regex)[0] !== value) {
                input.classList.remove('is-valid');
                input.classList.add('is-invalid');
            } else {
                input.classList.remove('is-invalid');
                input.classList.add('is-valid');
            }
        }
    }

    // 빈값 확인
	emptyCheck() {
		const inputList = document.querySelectorAll("input");
		for (let i = 0; i < inputList.length; i++) {
			if (inputList[i].value === "") return true;
		}
		return false;
	}

    // 빈값 확인 후 form 전송
    formSubmit() {
        if (this.emptyCheck()) {
			return this.alert("danger", "빈 값이 있습니다.");
		}
        if (document.querySelector(".is-invalid")) {
			return this.alert("danger", "잘못된 값이 있습니다.");
		}
		
        document.querySelector("form").submit();
    }

    // ajax 전송 결과 확인
    ajaxResult(data) {
        const div = document.createElement("div");
        div.innerHTML = data;
        const result = div.querySelector("#result");
        return result.innerHTML === "성공";
    }
}