function addCart(productId, lotId, quantity) {
    let obj = {'productId': productId, 'lotId': lotId, 'quantity': quantity};
    let res = ajaxCall(pageParam.cartUrl ,obj,'PATCH');
    if(res.status === 'success'){
        // loadDataTable(currentPage);
    }else {
        alert(res.msg);
    }
}

function getCart() {
    let res = ajaxCall(pageParam.cartUrl ,null,'GET');
    if(res.status === 'success'){
        // loadDataTable(currentPage);
    }else {
        alert(res.msg);
    }
}

function updateCart() {
    let obj = {'productId': productId, 'lotId': lotId, 'quantity': quantity};
    let res = ajaxCall(pageParam.cartUrl ,obj,'POST');
    if(res.status === 'success'){
        // loadDataTable(currentPage);
    }else {
        alert(res.msg);
    }
}