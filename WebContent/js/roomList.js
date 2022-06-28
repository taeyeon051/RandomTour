class RoomList {
    constructor(userId, nickname) {
        this.app = new App();

        this.userId = userId;
        this.nickname = nickname;

        this.chatList = document.querySelector("#chatting-list");
        this.chatForm = document.querySelector("#chatting-form");
        this.submitButton = document.querySelector("#chatting-button");

        this.webSocket = new WebSocket(`ws://${location.href.split("/")[2]}/chatting`, );

        this.addEvent();
    }

    addEvent() {
        const { submitButton, webSocket } = this;

        window.addEventListener("keydown", e => {
            if (e.key === "Enter") {
                this.sendChat();
            }
        });

        submitButton.addEventListener("click", () => {
            this.sendChat();
        });

        webSocket.onerror = e => { this.onError(e); };
        webSocket.onopen = e => { this.onOpen(e); };
        webSocket.onmessage = e => { this.onMessage(e); };
    }

    onMessage = e => {
        console.log(e.data);
        console.log(JSON.parse(JSON.stringify(e.data)));
        this.addChat(e.data);
    }

    onOpen = e => {
        console.log(e);
        const { userId, nickname } = this;
        this.addChat(`${nickname}님이 접속하였습니다.`, userId);
    }

    onError = e => {
        console.log(e);
        const { app } = this;
        app.alert(e.data);
    }

    sendChat() {
        const { chatForm, webSocket } = this;
        const { value } = chatForm;
        if (value.trim() === "") return;
        // this.addChat(value);
        webSocket.send(value);
        chatForm.value = "";
    }

    addChat(data, name) {
        const { nickname, chatList } = this;
        const div = document.createElement("div");
        div.innerHTML = data;
        if (name === nickname) div.classList.add("my-chat");
        else div.classList.add("chat");
        chatList.appendChild(div);
    }
}