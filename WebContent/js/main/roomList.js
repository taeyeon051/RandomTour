window.onload = () => {
    const roomList = new RoomList();
}

class RoomList {
    constructor() {
        this.app = new App();
        this.webSocket;

        this.chatList = document.querySelector("#chatting-list");
        this.chatForm = document.querySelector("#chatting-form");
        this.submitButton = document.querySelector("#chatting-button");
        this.roomTitle = document.querySelector("#room-title");
        this.roomButton = document.querySelector("#room-button");

        this.colorList = ["gray", "skyblue", "mint", "wheat", "brown", "silver", "blue"];
        this.chatColor = 0;

        this.init();
        this.addEvent();
    }

    init() {
        this.webSocket = new WebSocket(`ws://${location.href.split("/")[2]}/chatting/all`);

        const { webSocket } = this;
        webSocket.onerror = e => { this.onError(e); };
        webSocket.onopen = e => { this.onOpen(e); };
        webSocket.onmessage = e => { this.onMessage(e); };
    }

    addEvent() {
        const { submitButton, roomTitle, roomButton } = this;

        window.addEventListener("keydown", e => {
            if (e.key === "Enter") this.sendChat();
        });

        submitButton.addEventListener("click", () => {
            this.sendChat();
        });

        roomButton.addEventListener("click", () => {
            const { app } = this;
            const { value } = roomTitle;
            if (value.trim() === "") return app.alert("danger", "제목을 입력해주세요.");
        });
    }

    roomCreate() {
        const { userId, roomTitle } = this;
        $.ajax({
            url: "/room/create",
            type: "POST",
            data: { "title": roomTitle.value, "user-id": userId },
            success: data => {
                console.log(data);
                if (data.indexOf("success") !== -1) {
                    console.log(1);
                }
            }
        });

        $.ajax({
			url: "/main/friend/accept",
			type: "POST",
			data: { "user-id": userId, "nickname": nickname.name, "accept": accept },
			success: data => {
				this.ajaxAlert(data, "accept", nickname.btnDom.parentElement);
			}
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
        location.href = "/";
    }

    sendChat() {
        const { chatForm, webSocket } = this;
        const { value } = chatForm;
        if (value.trim() === "") return;
        this.addChat({ "message": value }, "my-chat");
        webSocket.send(value);
        chatForm.value = "";
    }

    addChat(data, chat) {
        const { chatList, colorList, chatColor } = this;
        const { nickname, message } = data;

        const div = document.createElement("div");
        if (chat === "in-chat") {
            $(div).addClass(`${chat} m-0 text-center p-1`);
        } else if (chat === "my-chat") {
            $(div).addClass(`${chat} m-2 text-end`);
        } else if (nickname) {
            this.chatColor = chatColor >= colorList.length - 1 ? 0 : chatColor + 1;
            div.innerHTML =
                `<div class="nickname text-${colorList[chatColor]}">
                    ${nickname}
                </div>`;
            $(div).addClass(`m-2`);
        }

        div.innerHTML += `<div class="message">${message}</div>`;
        chatList.appendChild(div);
        chatList.scrollTop = chatList.scrollHeight;
    }
}