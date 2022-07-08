window.onload = () => {
    const userForm = new UserForm();
    const joinBtn = document.querySelector("#join-btn");
    userForm.inputEvent();
    new UserForm(joinBtn).buttonEvent();
    
    // 인증하기 버튼 클릭 이벤트
    userForm.certifyBtnClickEvent();
}
