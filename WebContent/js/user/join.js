window.onload = () => {
    const joinBtn = document.querySelector("#join-btn");
    new UserForm().inputEvent();
    new UserForm(joinBtn).buttonEvent();
    
    // 인증하기 버튼 클릭 이벤트
    new UserForm().certifyBtnClickEvent();
}
