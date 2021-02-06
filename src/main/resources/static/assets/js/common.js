
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
        html += '<button class="btn btn-secondary" style="width:80px;" type="button" data-dismiss="modal">Cancel</button>';
        html += '<a class="btn btn-info text-light" style="width:80px;" data-dismiss="modal" id="modal-ok">Ok</a>';
    }

    html += '</div></div></div></div>';

    $("#modal-contents").append(html);
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
