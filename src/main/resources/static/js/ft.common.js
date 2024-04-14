function showToast(content, type, timeout = 13000) {
    const id = 'toast' + Math.round(Date.now() / 1000);
    let toastContent = type === 'success' ? '<div class="toast-body alert alert-success" style="margin-bottom: 0">' + content + '</div>' :
        '<div class="toast-body alert alert-danger" style="margin-bottom: 0">' + content + '</div>';

    let html = '' +
        '<div class="toast" role="alert" id="' + id + '" aria-live="assertive" aria-atomic="true" >\n' +
        toastContent +
        '</div>';

    $('.toast-container').append(html);
    $("#" + id).toast("show");
    const toastTimeout = setTimeout(() => {
        let $selected = $('#' + id);
        $selected.toast('dispose');
        $selected.remove();
    }, timeout);
}

function ftErrorAlert(message) {
    $("#popup-alert-msg").html(message);
    $('#error-modal').modal('show');
}

function ftAlert(message, callback) {
    $("#alert-confirm-btn").off("click");
    $("#pop-alert-msg").html(message);
    $("#alert-confirm-btn").on("click", function () {
        $("#alert-modal").modal('hide');
        if (typeof callback === "function") {
            callback();
        }
        return false;
    });
    $("#alert-modal").modal('show');
}

function ftHideLoading() {
    // $("#loading").hide();
};

const select = (el, all = false) => {
    el = el.trim()
    if (all) {
        return [...document.querySelectorAll(el)]
    } else {
        return document.querySelector(el)
    }
}