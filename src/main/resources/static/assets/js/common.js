function setModal(title, msg, id, event){

    var html = '<div class="modal fade" id="' + (id === undefined ? 'msgModal' : id) + '">';
    html += '<div class="modal-dialog ' + (id === undefined ? '' : 'modal-dialog-centered') + '"><div class="modal-content">';
    html += '<div class="modal-header">';
    html += '<h4 class="modal-title">' + title + '</h4>';
    html += '<button type="button" class="close" data-dismiss="modal">&times;</button></div>';
    html += '<div class="modal-body">' + msg + '</div>';
    html += '<div class="modal-footer">';

    if(id === undefined){
        html += '<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>';
    }
    else{
        html += '<button class="btn btn-secondary" style="width:80px;" type="button" data-dismiss="modal">Cancel</button>';
        html += '<button class="btn btn-info text-light" style="width:80px;" data-dismiss="modal" id="modal-ok">Ok</button>';
    }

    html += '</div></div></div></div>';

    $("#modal-contents").append(html);

    if(event !== undefined){
        $("#modal-contents #" + id + " #modal-ok").on("click", event);
    }

}

function showModal(id){
    $("#modal-contents #" + id).modal();
}

function msgModal(title,msg){

    if(title !== undefined){
        $("#modal-contents #msgModal").find(".modal-title").html(title);
    }

    if(msg !== undefined){
        $("#modal-contents #msgModal").find(".modal-body").html(msg);
    }

    $("#modal-contents #msgModal").modal();
}

$(()=>{
    setModal();
});


// 타입 컬러 
function getColor(type){
// 방법 1 - Array
    // var typeArr = {
    //     '풀': 'style="background-color: #7ab766;"',
    //     '불꽃': 'style="background-color: #efa061;"',
    //     '물': 'style="background-color: #608fcf;"',
    //     '벌레': 'style="background-color: #9bbf48"',
    //     '노말': 'style="background-color: #92999f;"',
    //     '독': 'style="background-color: #a16ec2;"',
    //     '전기': 'style="background-color: #edd259;"',
    //     '땅': 'style="background-color: #ca7c50;"',
    //     '얼음': 'style="background-color: #8dccc0;"',
    //     '페어리': 'style="background-color: #dc94e1;"',
    //     '악': 'style="background-color: #585365;"',
    //     '격투': 'style="background-color: #bc4b6a;"',
    //     '바위': 'style="background-color: #c3b78f;"',
    //     '에스퍼': 'style="background-color: #e57879;"',
    //     '고스트': 'style="background-color: #5769a7;"',
    //     '비행': 'style="background-color: #95a8d9;"',
    //     '강철': 'style="background-color: #678d9e;"',
    //     '드래곤': 'style="background-color: #336cbe;"'
    // }
    // return typeArr[type];

// 방법 2 - switch
    switch(type) {
    case '풀':
        return 'style="background-color: #7ab766;"'
    case '불꽃':
        return 'style="background-color: #efa061;"'
    case '물':
        return 'style="background-color: #608fcf;"'
    case '벌레':
        return 'style="background-color: #9bbf48"'
    case '노말':
        return 'style="background-color: #92999f;"'
    case '독':
        return 'style="background-color: #a16ec2;"'
    case '전기':
        return 'style="background-color: #edd259;"'
    case '땅':
        return 'style="background-color: #ca7c50;"'
    case '얼음':
        return 'style="background-color: #8dccc0;"'
    case '페어리':
        return 'style="background-color: #dc94e1;"'
    case '악':
        return 'style="background-color: #585365;"'
    case '격투':
        return 'style="background-color: #bc4b6a;"'
    case '바위':
        return 'style="background-color: #c3b78f;"'
    case '에스퍼':
        return 'style="background-color: #e57879;"'
    case '고스트':
        return 'style="background-color: #5769a7;"'
    case '비행':
        return 'style="background-color: #95a8d9;"'
    case '강철':
        return 'style="background-color: #678d9e;"'
    case '드래곤':
        return 'style="background-color: #336cbe;"'
    }
}
