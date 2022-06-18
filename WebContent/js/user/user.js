class UserForm {
    constructor(submitButton) {
        this.inputIdList = ["#user-id", "#certify-number", "#user-name", "#user-nickname", "#user-pwd", "#user-pwdc"];
        this.regexList = ["userId", "certify", "username", "nickname", "password", ""];
        this.submitButton = submitButton;
    }

    addEvent() {
        const app = new App();
        const { inputIdList, regexList, submitButton } = this;
        
        inputIdList.forEach((inputId, idx) => {
            const input = document.querySelector(inputId);
            if (input) {
                input.addEventListener("input", e => {
                    const passwordFocus = input.type === "password" && location.href.indexOf('/join') !== -1;
                    app.formDataCheck(e.target, app.getRegex(regexList[idx]), passwordFocus);
                });
            }
        });

        window.addEventListener("keydown", e => {
            if (e.key === "Enter") app.formSubmit();
        });
        submitButton.addEventListener("click", () => {
            app.formSubmit();
        });
    }
}