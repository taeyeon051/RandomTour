history.pushState(null, null, location.href);
window.onpopstate = () => {
    history.go(1);
}

class App {
    urlMapping(nowUrl, goUrl) {
        const url = location.href;
        if (url.indexOf(nowUrl) > -1) {
            if (document.querySelector(".alert")) {
                setTimeout(() => {
                    location.href = url.replace(nowUrl, goUrl);
                }, 500);
            }
        }
    }

    alert(state, text) {
        if (document.querySelector('.alert')) return;
        const div = document.createElement('div');
        $(div).addClass(`alert alert-${state} alert-dismissible fade show`).attr('role', 'alert');
        div.innerHTML = `${text} <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;
        document.querySelector("body").appendChild(div);
        setTimeout(() => { div.remove(); }, 3500);
    }
}