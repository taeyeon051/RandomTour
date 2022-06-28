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
        this.addChat(data, false);
    }

    onOpen = e => {
        this.addChat("채팅방에 오신 것을 환영합니다!");
    }

    onError = e => {
        const { app } = this;
        app.alert(e.data);
    }

    sendChat() {
        const { chatForm, webSocket } = this;
        const { value } = chatForm;
        if (value.trim() === "") return;
        this.addChat(value, true);
        webSocket.send(value);
        chatForm.value = "";
    }

    addChat(message, send) {
        console.log(data);
        const { chatList } = this;
        const { nickname, message } = data;
        const div = document.createElement("div");
        if (!send) {
            div.innerHTML = `${nickname} : `;
        }
        div.innerHTML += message;


        if (this.nickname === nickname) div.classList.add("my-chat");
        else div.classList.add("chat");
        chatList.appendChild(div);
    }
}