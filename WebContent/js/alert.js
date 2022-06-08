class Alert {
    constructor(state, text) {
        this.state = state;
        this.text = text;

        if (document.querySelector('.alert')) return;
        this.alert();
    }

    alert() {
        const { state, text } = this;
        const div = document.createElement('div');
        $(div).addClass(`alert alert-${state} alert-dismissible fade show`).attr('role', 'alert');
        div.innerHTML = `${text} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;
        document.querySelector("body").appendChild(div);
        setTimeout(() => { div.remove(); }, 5000);
    }
}