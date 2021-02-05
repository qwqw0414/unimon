
function setModal(title, msg, id){

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
        html += '<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>';
        html += '<button type="button" id="modal-ok" class="btn btn-primary" data-dismiss="modal">확인</button>';
    }

    html += '</div></div></div></div>';

    $("#modalContents").append(html);
}

function showModal(id){
    $("#modalContents #" + id).modal();
}

function msgModal(title,msg){

    if(title !== undefined){
        $("#modalContents #msgModal").find(".modal-title").html(title);
    }

    if(msg !== undefined){
        $("#modalContents #msgModal").find(".modal-body").html(msg);
    }

    $("#modalContents #msgModal").modal();
}

$(()=>{
    setModal();
});


$(() => {
    // navbar scroll event
    const $navbar = $("#navbar");
    const $navtop = $("#navtop");
    const navbar_loc = $navtop.height() + 10;
    let isScroll;
    let isFix = false;

    $(window).scroll((event) => {
        isScroll = true;
    });

    setInterval(() => {
        if (isScroll) {
            navFix();
            isScroll = false;
        }
    }, 10);

    function navFix() {
        var st = $(this).scrollTop();
        if (isFix !== st > navbar_loc) {
            if (isFix) {
                $navbar.removeClass("fixed-top");
                $navtop.css("margin-bottom", "0px");
            }
            else {
                $navbar.addClass("fixed-top");
                $navtop.css("margin-bottom", "60px");
            }
        }
        isFix = st > navbar_loc;
    }
});
