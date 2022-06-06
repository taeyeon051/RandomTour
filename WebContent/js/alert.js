window.Alert = (state, text) => {
    if (document.querySelector(".alert")) return;
    const div = document.createElement("div");
    div.innerHTML =
        `<div class="alert alert-${state} alert-dismissible fade show" role="alert">
		    ${text}
		    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	    </div>`;
    document.querySelector("body").appendChild(div);
    setTimeout(() => { div.remove(); }, 3000);
}