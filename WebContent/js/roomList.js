window.onload = () => {
    const roomList = new RoomList();
}

class RoomList {
    constructor() {
        this.app = new App();

        this.chatList = document.querySelector("#chatting-list");
        this.chatForm = document.querySelector("#chatting-form");
        this.submitButton = document.querySelector("#chatting-button");

        this.webSocket = new WebSocket(`ws://${location.href.split("/")[2]}/chatting`);

        this.init();
        this.addEvent();
    }

    init() {
        const { webSocket } = this;
        webSocket.onerror = e => { this.onError(e); };
        webSocket.onopen = e => { this.onOpen(e); };
        webSocket.onmessage = e => { this.onMessage(e); };
    }

    addEvent() {
        const { submitButton } = this;

        window.addEventListener("keydown", e => {
            if (e.key === "Enter") this.sendChat();
        });

        submitButton.addEventListener("click", () => {
            this.sendChat();
        });
    }

    onMessage = e => {
        const data = JSON.parse(e.data.toString());
        this.addChat(data);
    }

    onOpen = e => {
        this.addChat({ "message": "채팅방에 오신 것을 환영합니다!" }, "in-chat");
    }

    onError = e => {
        const { app } = this;
        app.alert(e.data);
    }

    sendChat() {
        const { chatForm, webSocket } = this;
        const { value } = chatForm;
        if (value.trim() === "") return;
        this.addChat({ "message": value });
        webSocket.send(value);
        chatForm.value = "";
    }

    addChat(data, chat) {
        const { chatList } = this;
        const { nickname, message } = data;

        const div = document.createElement("div");
        if (chat === "in-chat") div.classList.add(chat);
        if (nickname) {
            div.innerHTML = `${nickname} : `;
            div.classList.add("chat");
        } else {
            div.classList.add("my-chat");
        }

        div.innerHTML += message;
        chatList.appendChild(div);
    }
}