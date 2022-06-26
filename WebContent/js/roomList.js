const app = new App();

window.onload = () => {
    if (document.referrer.indexOf("/user/login")) {
        app.alert("success", "성공적으로 로그인 되었습니다.");
    }

    const chatList = document.querySelector("#chatting-list");
    const chatForm = document.querySelector("#chatting-form");
    const submitButton = document.querySelector("#chatting-button");

    const webSocket = new WebSocket(`ws://${location.href.split("/")[2]}/chatting`);

    window.addEventListener("keydown", e => {
        if (e.key === "Enter") {
            send();
        }
    });

    submitButton.addEventListener("click", e => {
        send();
    });

    webSocket.onerror = e => {
        onError(e);
    }

    webSocket.onopen = e => {
        onOpen(e);
    }

    webSocket.onmessage = e => {
        onMessage(e);
    }

    function onMessage(e) {
        console.log(e);
        const div = document.createElement("div");
        div.innerHTML = e.data; 
        chatList.appendChild(div);
    }

    function onOpen(e) {
        const div = document.createElement("div");
        div.innerHTML = "연결 성공";
        chatList.appendChild(div);
    }

    function onError(e) {
        app.alert(e.data);
    }

    function send() {
        console.log(chatForm.value);
        if (chatForm.value.trim() === "") return;
        const div = document.createElement("div");
        div.innerHTML = chatForm.value; 
        webSocket.send(chatForm.value);
        chatForm.value = "";
        chatList.appendChild(div);
    }
}