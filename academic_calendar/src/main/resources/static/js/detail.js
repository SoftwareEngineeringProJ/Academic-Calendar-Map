$(function(){
    $(".add-btn").click(store);
});

function store() {
    var btn = this;
    if($(btn).hasClass("btn-info")) {
        // 添加收藏
        $.post(
            "/store",
            {"conferenceId":$(btn).prev().val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    } else {
        // 取消收藏
        $.post(
            "/clear",
            {"conferenceId":$(btn).prev().val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }
}