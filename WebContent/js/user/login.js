window.onload = () => {
    const loginBtn = document.querySelector("#login-btn");
    new UserForm().inputEvent();
    new UserForm(loginBtn).buttonEvent();
}