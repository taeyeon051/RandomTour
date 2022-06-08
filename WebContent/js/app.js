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
}