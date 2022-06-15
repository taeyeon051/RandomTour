history.pushState(null, null, location.href);
window.onpopstate = () => {
    history.go(1);
}

class App {
    // url 이동
    urlMapping(nowUrl, goUrl) {
        const url = location.href;
        if (url.indexOf(nowUrl) > -1) {
            if (document.querySelector(".alert")) {
                setTimeout(() => {
                    location.href = url.replace(nowUrl, goUrl);
                }, 500);
            }
        }
    }

    // 알림창
    alert(state, text) {
        if (document.querySelector('.alert')) return;
        const div = document.createElement('div');
        $(div).addClass(`alert alert-${state} alert-dismissible fade show`).attr('role', 'alert');
        div.innerHTML = `${text} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;
        document.querySelector("body").appendChild(div);
        setTimeout(() => { div.remove(); }, 3500);
    }

    // 정규표현식
    getRegex(type) {
        const regex = {
            'userId': /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}/g,
            'certify': /[0-9]{6}/g,
            'username': /[가-힣a-zA-Z]{2,}/g,
            'nickname': /[A-Za-z0-9가-힣]{2,16}/g,
            'password': /(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*().])[A-Za-z\d!@#$%^&*().]{10,}/g
        };

        return regex[type];
    }

    // 입력값 체크
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

    emptyCheck(domList) {
        for (let i = 0; i < domList.length; i++) {
            if (domList[i].value == "") return true;
        }
        return false;
    }

    // 빈 값 체크 후 폼 전송
    formSubmit(domList, form) {
        if (this.emptyCheck(domList)) return this.alert('danger', '빈 값이 있습니다.');
        if (document.querySelector(".is-invalid")) {
            return this.alert('danger', '잘못된 값이 있습니다.');
        }
        document.querySelector(form).submit();
    }
}