const log = console.log;

window.onload = () => {
    const roomlist = new RoomList();
}

class RoomList {
    constructor() {
        this.init();
    }

    init() {
        new App().urlMapping('/user/login', '/main/roomList');
    }
}