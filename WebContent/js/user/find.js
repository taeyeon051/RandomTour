window.onload = () => {
    const find = new Find();
}

class Find {
    constructor() {
        this.username = document.querySelector("#user-name");
        this.nickname = document.querySelector("#user-nickname");
        this.findBtn = document.querySelector("#find-id-btn");
        this.findBtnClick = false;

        this.addEvent();
    }

    addEvent() {
        const { username, nickname, findBtn } = this;
        username.addEventListener("input", e => { e.target.value = e.target.value.replace(/\s/g, ""); });
        nickname.addEventListener("input", e => { e.target.value = e.target.value.replace(/\s/g, ""); });
        findBtn.addEventListener("click", e => {
            if (this.findBtnClick) return new App().alert('warning', '버튼은 한 번만 클릭해주세요.');
            this.findBtnClick = true;
            this.findId();
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

                if (result.innerHTML) {
                    const toast = div.querySelector(".toast");
                    $(toast).addClass('show');
                    document.querySelector("body").appendChild(toast);
                    setTimeout(() => { toast.remove(); }, 15000);
                } else {
                    new App().alert('danger', '아이디가 존재하지 않습니다.')
                    console.log(div);
                }

                setTimeout(() => { this.findBtnClick = false; }, 5000);
            }
        });
    }
}