let indexComponent = 1;
function getComponent(index){
    return `<div class="row mb-2" id="component-item${index}">
                    <label class="col-sm-2"></label>
                    <div class="col-sm-10">
                      <div class="row">
                        <div class="col-sm-10">
                          <select class="form-select" name="components" id="list-component${index}" aria-label="">

                          </select>
                        </div>
                        <div class="col-sm-2">
                          <i style="font-size: 25px; cursor: pointer" class="bi bi-plus-circle" onclick="addComponent()"></i>
                          <i style="font-size: 25px; cursor: pointer" class="bi bi-dash-circle" onclick="deleteComponent('#component-item${index}')"></i>
                        </div>
                      </div>
                     </div>
               </div>`;
}
function addComponent(value){
    $('#listComponent').append(getComponent(indexComponent));
    addOptionComponent('list-component'+indexComponent);
    if(value){
        $('#list-component'+indexComponent).val(value).change();
    }
    indexComponent ++;
}

function deleteComponent(id){
    $(id).remove();
}

function addOptionComponent(id){
    let result = ajaxCall(apiDomain + '/api/v1/admin/config/component/all','','get');
    if(result.status=="success"){
        let listOption = '';
        result.data.forEach((item, index) => {
            listOption += `<option value="${item.id}">${item.name}</option>`
        });
        $('#'+id).append(listOption);
    }
}

$("#form-page").validate({
    invalidHandler: function(form, validator) {
        let errors = validator.numberOfInvalids();
        if (errors) {
            validator.errorList[0].element.focus();
        }
    },
    highlight: function(element, errorClass) {
        $(element).removeClass(errorClass);
    },
    rules: {
        pageName: "required",
    },
    messages: {
        pageName: "Vui lòng nhập tên",
    }
});

function buildJsonHome(){
    let pageJson = {};
    let checkHead = getJsonByForm('headBanner','list',false,true,true,true,true);
    if(!checkHead){
        alert('Vui lòng nhập đầy đủ thông tin head banner');
        return false;
    }
    let checkMain = getJsonByForm('mainBanner','list',false,true,true,true,true);
    if(!checkMain){
        alert('Vui lòng nhập đầy đủ thông tin main banner');
        return false;
    }
    let checkSub = getJsonByForm('subBanner','listSubBanner',false,true,true,true,true);
    if(!checkSub){
        alert('Vui lòng nhập đầy đủ thông tin sub banner');
        return false;
    }
    let checkPromote = getJsonByForm('promote','listPromote',false,true,true,true, true);
    if(!checkPromote){
        alert('Vui lòng nhập đầy đủ thông tin gợi ý');
        return false;
    }
    let headBanner = getJsonByForm('headBanner','list',false,false,false,false,true);
    let mainBanner = getJsonByForm('mainBanner','list',false,false,false,false,true);
    let subBanner = getJsonByForm('subBanner','listSubBanner',false,false,false,false,true);
    let promote = getJsonByForm('promote','listPromote',false,false,false,false,true);
    pageJson['headBanner'] = headBanner;
    pageJson['mainBanner'] = mainBanner;
    pageJson['listSubBanner'] = subBanner.listSubBanner;
    pageJson['listPromote'] = promote.listPromote;
    return pageJson;
}

function buildJson(pageType){
    let json = {};
    switch(pageType) {
        case 'HOME':
            json = buildJsonHome();
            break;
        default:
    }
    return json;
}