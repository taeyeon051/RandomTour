window.onload = () => {
    const find = new Find();
}

class Find {
    constructor() {
        this.username = document.querySelector("#user-name");
        this.nickname = document.querySelector("#user-nickname");
        this.findBtn = document.querySelector("#find-id-btn");
        this.usernameRegex = /[가-힣a-zA-Z]{2,}/g;
        this.nicknameRegex = /[A-Za-z0-9가-힣]{2,16}/g;
        this.findBtnClick = false;

        this.addEvent();
    }

    addEvent() {
        const { username, nickname, findBtn } = this;
        const { usernameRegex, nicknameRegex } = this;
        username.addEventListener("input", e => { this.dataCheck(e.target, usernameRegex); });
        nickname.addEventListener("input", e => { this.dataCheck(e.target, nicknameRegex); });
        findBtn.addEventListener("click", e => {
            if (this.findBtnClick) return new App().alert('warning', '버튼은 한 번만 클릭해주세요.');
            this.findBtnClick = true;
            this.findId();
        });

        $('#nav-id-tab').hover(e => { $('.tab-content').css('border-top-left-radius', '0'); });
        $('.nav-link').click(e => {
            if ($(e.target).attr('id') == "nav-id-tab") {
                $('.tab-content').css('border-top-left-radius', '0');
                $('#find-id').addClass('show active');
                $('#find-pw').removeClass('show active')
            }
            if ($(e.target).attr('id') == "nav-pw-tab") {
                $('.tab-content').css('border-top-left-radius', '0.375rem');
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

                console.log(result.innerHTML == "true");
                if (result.innerHTML == "true") {
                    const toast = div.querySelector(".toast");
                    $(toast).addClass('show');
                    document.querySelector("body").appendChild(toast);
                    setTimeout(() => { toast.remove(); }, 15000);
                } else {
                    new App().alert('danger', '아이디가 존재하지 않습니다.')
                }

                setTimeout(() => { this.findBtnClick = false; }, 5000);
            }
        });
    }

    dataCheck(dom, regex) {
        const value = dom.value;
        dom.value = dom.value.replace(/\s/g, "");
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
}