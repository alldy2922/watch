/**
 * @author do
 **/

/*
--call with param json
let param = {
    id: id,
    status: status
}
let res = ajaxCall(apiDomain + '/api/v1/config/template/update-status',param,'patch');

--call with param form-data
let form = $('#form-template')[0];
let param = new FormData(form);
let res = ajaxCall(apiDomain + '/api/v1/config/template',param,'post',false);

--call with param url
let param = '&pageNo=' + pageNo + '&pageSize=' + pageSize;
let res = ajaxCall(apiDomain + '/api/v1/config/template', param, method);

 */

//REST API Ajax
function ajaxCall(callUrl,param,method,content,dType,async,process,credential){
    let resData = '';
    if(typeof param == 'object' && (typeof content == 'undefined' || content == null)){
        param = JSON.stringify(param);
    }
    if(method==null || (typeof method == 'undefined')) method = "post";
    if(process==null || (typeof process == 'undefined')) process = false;
    if(content==null || (typeof content == 'undefined')) content = "application/json; charset=utf-8";
    if(async==null || (typeof async == 'undefined')) async = false
    if(dType==null || (typeof dType == 'undefined')) dType = 'json';
    if(credential==null || (typeof credential == 'undefined')) credential = true;
    $.ajax({
        url: callUrl,
        type: method,
        async: async,
        data: param,
        dataType: dType,
        processData : process,
        contentType : content,
        xhrFields: { withCredentials: credential },
        success: function(res) {
            if(res != null){
                resData = res;
            }
        },
        error : function(request, status, error ) {
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            resData = { msg : request.responseJSON.msg};
        }
    });
    return resData;
}

let currentPage;
let currentPageSize;
function loadDataTable(pageNo) {
    let formSelector = '#' + ((typeof pageParam.formId == 'undefined') ? 'schForm' : pageParam.formId);
    let callUrl = ((typeof pageParam.dataListUrl == 'undefined') ?  $(formSelector).attr("action") : pageParam.dataListUrl);
    let method = $(formSelector).attr("method");
    let param = $(formSelector).serialize();
    let pageSize = $("#page-size option:selected").val();
    param += '&pageNo=' + pageNo + '&pageSize=' + pageSize;
    let res = ajaxCall(callUrl, param, method);
    currentPage = res.pageNo;
    currentPageSize = res.pageSize;
    if(res.status==="success"){
        $('#data-table').html(getDataTable(res.data));
        let totalPages = Math.ceil(res.total / res.pageSize);
        $('#data-pagination').html(addPagination(totalPages, res.pageNo, res.pageSize))
    }else{
        alert(res.msg);
    }
}

function addPage(start, end, currentPage){
    let listPage = '';
    for(let i = start; i <= end; i++){
        let active = '';
        if(currentPage === i){
            active = 'active pe-none';
        }
        listPage += '<li class="page-item '+active+'"><a class="page-link" href="#" onclick="loadDataTable('+i+');return false;">'+i+'</a></li>';
    }
    return listPage;
}
function addPagination(totalPage, currentPage, pageSize){
    if(totalPage === 0){
        return '';
    }
    let disableStart = currentPage === 1 ? 'disabled' : '';
    let disableEnd = currentPage === totalPage ? 'disabled' : '';

    let listPage = '';
    if(totalPage > 10){
        if(currentPage <= 5 || currentPage >= totalPage - 4){
            let pageMore = currentPage <= 5 ? 6 : (totalPage - 5);
            listPage += addPage(1,5,currentPage);
            listPage += '<li class="page-item"><a class="page-link" href="#" onclick="loadDataTable('+pageMore+');return false;">...</a></li>';
            listPage += addPage(totalPage - 4, totalPage, currentPage);
        }
        if(currentPage > 5 && currentPage < totalPage - 4){
            listPage += '<li class="page-item"><a class="page-link" href="#" onclick="loadDataTable('+(currentPage - 5)+');return false;">...</a></li>';
            listPage += addPage(currentPage - 4, currentPage + 4, currentPage);
            listPage += '<li class="page-item"><a class="page-link" href="#" onclick="loadDataTable('+(currentPage + 5)+');return false;">...</a></li>';
        }
    }
    else {
        listPage += addPage(1, totalPage, currentPage);
    }

    return '<nav aria-label="Page navigation example">' +
        '                  <ul class="pagination">' +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableStart + '" href="#" onclick="loadDataTable(1);return false;" aria-label="Previous">' +
        '                        <span aria-hidden="true">«</span>' +
        '                      </a>' +
        '                    </li>' +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableStart + '" href="#" onclick="loadDataTable(' + (currentPage - 1) + ');return false;" aria-label="Previous">' +
        '                        <span aria-hidden="true">‹</span>' +
        '                      </a>' +
        '                    </li>' +
        listPage +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableEnd + '" href="#" onclick="loadDataTable(' + (currentPage + 1) + ');return false;" aria-label="Previous">' +
        '                        <span aria-hidden="true">›</span>' +
        '                      </a>' +
        '                    </li>' +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableEnd + '" href="#" onclick="loadDataTable(' + totalPage + ');return false;" aria-label="Next">' +
        '                        <span aria-hidden="true">»</span>' +
        '                      </a>' +
        '                    </li>' +
        '                  </ul>' +
        '                </nav>';
}

$("#page-size").change(function(){
    loadDataTable(1);
});

function rowOrder(index){
    if(currentPage === 1){
        return index + 1;
    }
    return (currentPageSize * (currentPage - 1)) + index + 1;
}

function showImgPreview(idPreview, idInput, event) {
    let file = $('#'+idInput)[0].files[0];
    if(file != null) {
        $('#'+idPreview).empty().append('<img id="'+idPreview+'-common" width="100%" height="100%" alt=""/>' +
            '<i style="cursor:pointer; position:absolute; top:-12px; right: -8px" class="bi bi-x-circle-fill" onclick="deleteImgPreview(\''+idPreview+'\',\''+idInput+'\')"></i>')
            .show();
        let image = document.getElementById(idPreview+'-common');
        image.src = URL.createObjectURL(event.files[0]);
    }
}

function addImgByUrl(idPreview, url, idInput){
    if(url != null) {
        $('#'+idPreview).empty();
        $('#'+idPreview).show();
        $('#'+idInput).val(url);
        $('#'+idPreview).append('<img id="' + idPreview + '-common" width="100%" height="100%" src="' + url + '"/>' +
            '<i style="cursor:pointer; position:absolute; top:-12px; right: -8px" class="bi bi-x-circle-fill" onclick="deleteImgPreview(\'' + idPreview + '\',\'' + idInput + '\')"></i>');
    }
}
function deleteImgPreview(idPreview, idInput){
    $('#'+idPreview).hide().empty();
    $('#'+idInput).val('');
}

function textToHtml(textHtml){
    const parser = new DOMParser();
    return parser.parseFromString(textHtml, 'text/xml');
}

function addContentFile(evt, idAddValContent, idFilePreview) {
    let f = evt.files[0];
    if (f) {
        let r = new FileReader();
        r.onload = function (e) {
            let contents = e.target.result;
            idAddValContent ? $('#'+idAddValContent).val(contents) : '';
            idFilePreview ? $('#'+idFilePreview).html(contents) : '';
        }
        r.readAsText(f);
    } else {
        alert("Failed to load file");
    }
}

//build file with content and download file
function download(filename, content) {
    let element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(content));
    element.setAttribute('download', filename);
    element.style.display = 'none';
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
}

//Prevent users from submitting a form by hitting Enter
$(document).ready(function() {
    $(window).keydown(function(event){
        if(event.keyCode === 13) {
            event.preventDefault();
            return false;
        }
    });
});

function removeAccents(str) {
    return str.normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/đ/g, 'd').replace(/Đ/g, 'D');
}

function getClassStatus(status) {
    let className = '';
    switch(status) {
        case 1:
            className = 'bg-success';
            break;
        case 0:
            className = 'bg-danger';
            break;
        default:
            className = '';
    }
    return className;
}

function getBooleanClassStatus(status) {
    return status ? 'bg-success' : 'bg-danger';
}

function getBooleanTextStatus(status) {
    return status ? 'Sử dụng' : 'Không sử dụng';
}

function getChecked(isChecked) {
    return isChecked ? 'checked' : '';
}

function getBooleanClassOrderStatus(status) {
    switch (status) {
        case "COMPETE":
            return 'bg-dark.bg-gradient';
        case 'CONFIRMED':
            return 'bg-primary';
        case 'PACKING' || 'READY_TO_SHIP':
            return 'bg-info'
        case 'SHIPPING':
            return 'bg-success'
        case 'REJECTED' || 'CANCELLED':
            return 'bg-secondary';
        case 'REQUEST_BACK' || 'BACK':
            return 'bg-danger';
        default:
            return 'bg-warning';
    }
}

function getBooleanOrderStatus(status) {
    switch (status) {
        case "COMPETE":
            return 'Hoàn thành';
        case 'CONFIRMED':
            return 'Đã xác nhận';
        case 'PACKING':
            return 'Đang xử lý';
        case 'READY_TO_SHIP':
            return 'Sẵn sàng giao hàng';
        case 'SHIPPING':
            return 'Đang giao hàng';
        case 'REJECTED':
            return 'Huỷ đơn';
        case 'CANCELLED':
            return 'Khách hàng huỷ đơn';
        case 'REQUEST_BACK':
            return 'Yêu cầu trả hàng';
        case 'BACK':
            return 'Đã trả hàng';
        default:
            return 'Đơn mới';
    }
}


function getTextStatus(status) {
    let text = '';
    switch(status) {
        case 1:
            text = 'Sử dụng';
            break;
        case 0:
            text = 'Không sử dụng';
            break;
        default:
            text = '';
    }
    return text;
}

function allCheck() {
    const selectAll = document.querySelector('.check-all-remove').checked;
    const valueCheckboxes = document.querySelectorAll('.check-box-remove')
    for (let i = 0; i < valueCheckboxes.length; i++) {
        valueCheckboxes[i].checked = selectAll;
    }
}

function remove() {
    // Get the list of checkboxes that have value and are checked
    const valueCheckboxes = document.querySelectorAll('.check-box-remove:checked')
    // If the list is empty, display an alert
    if (valueCheckboxes.length === 0) return alert("Hãy chọn hàng muốn xoá")
    // Otherwise, create an array containing the values of the checkboxes
    else {
        if (window.confirm("Bạn có chắc chắn muốn xóa?")) {
            let ids = Array.from(valueCheckboxes, e => parseInt($(e).val()))
            let param = '?ids='+ids;
            let res = ajaxCall(pageParam.deleteUrl + param, '', "DELETE");
            if(res.status==="success"){
                alert("Xóa thành công");
                location.reload();
            }else{
                alert(res.msg);
            }
        }
    }
}

function updateStatus(id, status){
    let param = {
        id: id,
        status: status === 1 ? 0 : 1
    }
    let res = ajaxCall(pageParam.updateStatusUrl,param,'PATCH');
    if(res.status === 'success'){
        loadDataTable(currentPage);
    }else {
        alert(res.msg);
    }
}


function getIdCheckBox(id) {
    return id ? ("checkbox_" + id) : "checkbox_all"
}

function reload() {
    location.reload();
}

function loadButton(){
    const valueCheckboxes = document.querySelectorAll('.form-check-input.value')
    if (valueCheckboxes.length === 0){

    }else if(valueCheckboxes.length === 0){

    }else{

    }
}
function remove_ver2() {
    // Get the list of checkboxes that have value and are checked
    const valueCheckboxes = document.querySelectorAll('.check-box-remove:checked')
    // If the list is empty, display an alert
    if (valueCheckboxes.length === 0) return alert("Hãy chọn hàng muốn xoá")
    // Otherwise, create an array containing the values of the checkboxes
    else {
        if (window.confirm("Bạn có chắc chắn muốn xóa ?")) {
            let ids = Array.from(valueCheckboxes, e => parseInt($(e).val()))
            let res = ajaxCall(pageParam.deleteUrl, {ids: ids}, "DELETE");
            if(res.status==="success"){
                loadDataTable(currentPage);

            }else{
                alert(res.msg);
            }
        }
    }
}

$.fn.validateJson = function (isCheckInput, isCheckFile, inElement, tagListId, isShowImg) {
    let els = inElement ? $(this).find(':input').get()
        : $(this).find(':input').get().filter((e) => !e.closest('#'+tagListId));
    let item = $(this);
    let check = true;
    $.each(els, function () {
        if (this.name && !this.disabled && ((this.checked && /radio/i.test(this.type)) || /select|textarea/i.test(this.nodeName) || /text|password|checkbox/i.test(this.type))) {
            if($(this).val() == '' && isCheckInput){
                check = false;
                return false;
            }
        }else{
            if(this.type == 'file'){
                let file = this.files[0];
                let idDivShowImg = isShowImg ? $(this).attr('id-img-preview') : this.name;
                let nameUrlInput = 'common-i-m-g-'+idDivShowImg;
                let inputImgOld = item.find(`input[type='hidden'][name='${nameUrlInput}']`);
                if(inputImgOld.length === 0) {
                    if($(this).val() == '' && isCheckFile){
                        check = false;
                        return false;
                    }
                }else{
                    if(inputImgOld[0].value == '' && typeof file == 'undefined' && isCheckFile){
                        check = false;
                        return false;
                    }
                }
            }
        }

    });
    return check;
};

$.fn.serializeObject = function (data, inElement, hasObjectKey, tagListId, isShowImg) {
    let els = inElement ? $(this).find(':input').get()
                    : $(this).find(':input').get().filter((e) => !e.closest('#'+tagListId));
    console.log(els);
    let item = $(this);
    if (typeof data != 'object') {
        data = {};
        $.each(els, function () {
            let key = hasObjectKey ? $(this).attr('object-key') : this.name;
            if (this.name && !this.disabled && ((this.checked && /radio/i.test(this.type)) || /select|textarea/i.test(this.nodeName) || /text|password|checkbox|number|hidden/i.test(this.type))) {
                data[key] = $(this).val();
                if (/text|hidden|password|number/i.test(this.type)) {
                    $(this).attr('value', $(this).val());
                }
                if (this.type == 'checkbox') {
                    if (this.checked) {
                        $(this).attr('checked', 'checked');
                        data[key] = $(this).val();
                    } else {
                        $(this).removeAttr('checked');
                    }
                }
                if (this.type == 'select-multiple') {
                    let valOption = $(this).val();
                    if (Array.isArray(valOption) && valOption.length > 0) {
                        let options = $(this).find('option').get();
                        $.each(options, function () {
                            $(this).removeAttr('selected');
                            if (valOption.includes($(this).val())) {
                                $(this).attr('selected', 'selected');
                            }
                        });
                    }
                }
                if (this.type == 'select-one') {
                    let valOption = $(this).val();
                    let options = $(this).find('option').get();
                    $.each(options, function () {
                        $(this).removeAttr('selected');
                        if ($(this).val() == valOption) {
                            $(this).attr('selected', 'selected');
                        }
                    });
                }
                if (this.type == 'radio' && this.checked) {
                    let radios = inElement ? item.find(`input[type='radio'][name='${this.name}']`).get()
                                            :item.find(`input[type='radio'][name='${this.name}']`).get().filter((e) => !e.closest('#'+tagListId));
                    $.each(radios, function () {
                        $(this).removeAttr('checked');
                    });
                    $(this).attr('checked', 'checked');
                }
            }else{
                if(this.type == 'file'){
                    let file = this.files[0];
                    let idDivShowImg = isShowImg ? $(this).attr('id-img-preview') : this.name;
                    let nameUrlInput = 'common-i-m-g-'+idDivShowImg;
                    let inputImgOld = item.find(`input[type='hidden'][name='${nameUrlInput}']`);
                    if(inputImgOld.length === 0){
                        if(typeof file == 'undefined'){
                            data[key] = '';
                        }else{
                            let urlImg = uploadFile(this.files[0]);
                            if(isShowImg) {
                                $('#'+idDivShowImg).empty();
                                addImgByUrl(idDivShowImg,urlImg,nameUrlInput);
                            }
                            item.append('<input type="hidden" name='+nameUrlInput+' id='+nameUrlInput+' value='+urlImg+'>');
                            data[key] = urlImg;
                        }
                    }else{
                        if(typeof file == 'undefined'){
                            data[key] = inputImgOld[0].value;
                        }else {
                            let urlImg = uploadFile(this.files[0]);
                            data[key] = urlImg;
                            if(isShowImg) {
                                $('#'+idDivShowImg).empty();
                                addImgByUrl(idDivShowImg,urlImg,nameUrlInput);
                            }
                            inputImgOld[0].value = urlImg;
                            inputImgOld[0].setAttribute('value',urlImg);
                        }
                    }

                }
            }

        });
        return data;
    }
};


/**
 Sử dụng function @getJsonByForm
 - template: mẫu

 <div id="data">
     <input type="text" object-key="a-name" id="fname" name="fname"><br>
     <div id="list">
         <div>
             <input object-key="a-file" type="file" name="file">
             <input object-key="a-desc" type="text" name="desc">
         </div>
         <div>
             <input object-key="a-file" type="file" name="file">
             <input object-key="a-desc" type="text" name="desc">
         </div>
     </div>
 </div>

 - return json:
 {
  "a-name": "test",
  "element": [
    {
      "a-desc": "desc1",
      "a-file": "url1"
    },
    {
      "a-desc": "desc2",
      "a-file": "url2"
    }
  ]
}

 - Lưu ý:
 các input không nằm trong list thì đặt ngoài div có id=list
 mỗi element là 1 div nằm trong div có id = list (list có thể là id động)

 --Sử dụng:
 getJsonByForm(tagId, tagListId, hasObjectKey, isCheckInput, isCheckFile, isOnlyValid, isShowImg)
 getJsonByForm('data', 'list', true, true, true, false, false);

 - tagId: id thẻ bao bọc tất cả các thẻ khác
 - tagListId: id thẻ chứa các phần tử giống nhau được xem như 1 list
 - hasObjectKey: + true: sử dụng object-key trong thẻ input làm key object
                 + false: sử dụng name input làm key object
 - isCheckInput: + valid input
                 + nếu có input chưa nhập thì hàm getJsonByForm trả về false;
 - isCheckFile: tương tự như isCheckInput
 - isOnlyValid: + true: validate form trả về true or false (ko trả về json)
                + false: validate form trả về false or json
 - isShowImg: + show img preview
              + input type file cần đặt attribute: id-img-preview = "id của div chứa ảnh preview"
  ví dụ: <div id="show-img-preview"></div>
         <input type="file" id-img-preview="show-img-preview" name="file"/>

 **/
function getJsonByForm(tagId, tagListId, hasObjectKey, isCheckInput, isCheckFile, isOnlyValid, isShowImg){
    if(typeof isCheckInput == 'undefined') isCheckInput = true;
    if(typeof isCheckFile == 'undefined') isCheckFile = true;
    if(typeof isOnlyValid == 'undefined') isOnlyValid = false;
    if(typeof isShowImg == 'undefined') isShowImg = false;
    let object = {};
    let data = [];
    let checkValid = true;

    if(isCheckInput || isCheckFile){
        checkValid = $('#'+tagId).validateJson(isCheckInput, isCheckFile, false, tagListId, isShowImg);
        if(!checkValid){
            return false;
        }
        $('#'+tagId).find("#"+tagListId+">div").each(function () {
            let res = $(this).validateJson(isCheckInput, isCheckFile, true, tagListId, isShowImg);
            if(!res){
                checkValid = false;
                return false;
            }
        });
    }

    if(isOnlyValid || !checkValid){
        return checkValid;
    }

    object = $('#'+tagId).serializeObject('', false, hasObjectKey, tagListId, isShowImg);
    $('#'+tagId).find("#"+tagListId+">div").each(function () {
        let res = $(this).serializeObject('', true, hasObjectKey, tagListId, isShowImg);
        data[data.length] = res;
    });

    if(data.length > 0){
        object[tagListId] = data;
    }
    return object;
}

function uploadFile(file){
    let formData = new FormData();
    formData.set('file',file);
    let res = ajaxCall(apiDomain + '/api/v1/admin/file/uploadFile',formData,'post',false);
    if(res.status=="success"){
        return res.data.fileDownloadUri;
    }
}

function cancel(url){
    $(location).attr('href', url);
}

function logout(){
    let resBe = ajaxCall(apiDomain + '/admin/logout', '', 'get');
    if(resBe.status === "success"){
        let resFe = ajaxCall('/logout', '', 'get');
        if(resFe.status === "success"){
            location.href = '/manage/login';
        }
    }
}

function getJsonByFormTobi(formId){
    let object = {};
    let data = [];
    let form = $('#'+formId);
    let formData = new FormData(form[0]);
    let check = true;
    form.find("input.form-control").each(function (e) {
        let input = $(this);
        data[data.length] = input.val();
    })

    form.find("input.form-control").each(function () {
        let input = $(this);
        data[data.length] = input.val();
    })
    for (let [key, value] of formData) {
        let isInList = $('#element').find('[name="'+key+'"]').val();
        if(typeof isInList == 'undefined'){
            if(typeof value.name == 'string'){
                if(value.name === ''){
                    check = false;
                    break;
                }else {
                    object[key] = uploadFile(value);
                }
            } else{
                if(value === ''){
                    check = false;
                    break;
                }else {
                    object[key] = value;
                }
            }
        }
    }
    if(!check){
        return false;
    }
    if(data.length > 0){
        object['element'] = data;
    }
    return object;
}
function convertDateToString(date){
    return date? date.split('T')[0] : '' ;
}
function convertDateToStringDateTime(dateTime){
    let time =  dateTime? dateTime.split('T')[1] : '';
    let date =  dateTime? dateTime.split('T')[0] : '';
    let timeFormat  = time ? time.split(':')[0] + ':' + time.split(':')[1] : ''
    let dateFormat = date ? date.split('-')[2] + '-' + date.split('-')[1] + '-' + date.split('-')[0] : ''
    return timeFormat + " "+ dateFormat  ;
}

function loadDataPopupTable(pageNo) {
    let formSelector = '#' + ((typeof popupParam.formId == 'undefined') ? 'popup-search' : popupParam.formId);
    let callUrl = ((typeof popupParam.dataListUrl == 'undefined') ?  $(formSelector).attr("action") : popupParam.dataListUrl);
    let method = $(formSelector).attr("method");
    let param = $(formSelector).serialize();
    let pageSize = $("#popup-page-size option:selected").val();
    param += '&pageNo=' + pageNo + '&pageSize=' + pageSize;
    let res = ajaxCall(callUrl, param, method);
    currentPage = res.pageNo;
    currentPageSize = res.pageSize;
    if(res.status==="success"){
        $('#data-table').html(getDataTable(res.data));
        let totalPages = Math.ceil(res.total / res.pageSize);
        $('#data-popup-pagination').html(addPopupPagination(totalPages, res.pageNo, res.pageSize))
    }else{
        alert(res.msg);
    }
}

function addPopupPage(start, end, currentPage){
    let listPage = '';
    for(let i = start; i <= end; i++){
        let active = '';
        if(currentPage === i){
            active = 'active pe-none';
        }
        listPage += '<li class="page-item '+active+'"><a class="page-link" href="#" onclick="loadDataPopupTable('+i+');return false;">'+i+'</a></li>';
    }
    return listPage;
}
function addPopupPagination(totalPage, currentPage, pageSize){
    if(totalPage === 0){
        return '';
    }
    let disableStart = currentPage === 1 ? 'disabled' : '';
    let disableEnd = currentPage === totalPage ? 'disabled' : '';

    let listPage = '';
    if(totalPage > 10){
        if(currentPage <= 5 || currentPage >= totalPage - 4){
            let pageMore = currentPage <= 5 ? 6 : (totalPage - 5);
            listPage += addPopupPage(1,5,currentPage);
            listPage += '<li class="page-item"><a class="page-link" href="#" onclick="loadDataPopupTable('+pageMore+');return false;">...</a></li>';
            listPage += addPopupPage(totalPage - 4, totalPage, currentPage);
        }
        if(currentPage > 5 && currentPage < totalPage - 4){
            listPage += '<li class="page-item"><a class="page-link" href="#" onclick="loadDataPopupTable('+(currentPage - 5)+');return false;">...</a></li>';
            listPage += addPopupPage(currentPage - 4, currentPage + 4, currentPage);
            listPage += '<li class="page-item"><a class="page-link" href="#" onclick="loadDataPopupTable('+(currentPage + 5)+');return false;">...</a></li>';
        }
    }
    else {
        listPage += addPopupPage(1, totalPage, currentPage);
    }

    return '<nav aria-label="Page navigation example">' +
        '                  <ul class="pagination">' +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableStart + '" href="#" onclick="loadDataPopupTable(1);return false;" aria-label="Previous">' +
        '                        <span aria-hidden="true">«</span>' +
        '                      </a>' +
        '                    </li>' +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableStart + '" href="#" onclick="loadDataPopupTable(' + (currentPage - 1) + ');return false;" aria-label="Previous">' +
        '                        <span aria-hidden="true">‹</span>' +
        '                      </a>' +
        '                    </li>' +
        listPage +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableEnd + '" href="#" onclick="loadDataPopupTable(' + (currentPage + 1) + ');return false;" aria-label="Previous">' +
        '                        <span aria-hidden="true">›</span>' +
        '                      </a>' +
        '                    </li>' +
        '                    <li class="page-item">' +
        '                      <a class="page-link ' + disableEnd + '" href="#" onclick="loadDataPopupTable(' + totalPage + ');return false;" aria-label="Next">' +
        '                        <span aria-hidden="true">»</span>' +
        '                      </a>' +
        '                    </li>' +
        '                  </ul>' +
        '                </nav>';
}

$("#popup-page-size").change(function(){
    loadDataPopupTable(1);
});

