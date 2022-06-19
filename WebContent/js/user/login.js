window.onload = () => {
    const app = new App();
    app.urlMapping("/logout", "/login");

    const loginBtn = document.querySelector("#login-btn");
    new UserForm().inputEvent();
    new UserForm(loginBtn).buttonEvent();
}