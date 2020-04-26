$(function(){
    $(".clear-btn").click(clear);
    $(".googlecalendar-btn").click(googlecalendar);
    $(".local-btn").click(localbtn)
});

function googlecalendar() {
    var btn = this;
    if($(btn).hasClass("btn-success")) {
        // 添加到谷歌日历
        $.post(
            "/googlecalendar",
            {"cid1": $(btn).prev().val()},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }
}

function localbtn() {
    var btn = this;
    if($(btn).hasClass("btn-success")) {
        // 添加到本地
        $.post(
            "/local",
            {"cid2": $(btn).prev().val()},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }
}

function clear() {
    var btn = this;
    if($(btn).hasClass("btn-info")) {
        // 取消收藏
        $.post(
            "/clear",
            {"conferenceId": $(btn).prev().val()},
            function (data) {
                data = $.parseJSON(data);
                if (data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
    }
}