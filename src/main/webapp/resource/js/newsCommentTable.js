/**
 * Created by Саша on 23.05.2017.
 */
$(document).ready(function () {
    var params = window
        .location
        .search
        .replace('?', '')
        .split('&')
        .reduce(
            function (p, e) {
                var a = e.split('=');
                p[decodeURIComponent(a[0])] = decodeURIComponent(a[1]);
                return p;
            },
            {}
        );
    var newsList = "";
    var userList="";
    function get_news_comment_data() {
        $('.news-comment-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=NewsComment&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    $.ajaxSetup({
                        url: '/library/getData',
                        global: true,
                        type: 'GET',
                        dataType: "json",
                        data: "tableName=User&action=GetAll&language=ru_RU"
                    });
                    $.ajax({
                        success: function (msg_2) {
                            if (msg_2 != null) {
                                for (var j = 0; j < msg_2.length; j++) {
                                    userList += '<option>' + msg_2[j].id + '</option>';
                                }
                            }
                        }, async: false
                    });
                    $.ajaxSetup({
                        url: '/library/getData',
                        global: true,
                        type: 'GET',
                        dataType: "json",
                        data: "tableName=News&action=GetAll&language=ru_RU"
                    });
                    $.ajax({
                        success: function (msg_2) {
                            if (msg_2 != null) {
                                for (var j = 0; j < msg_2.length; j++) {
                                    newsList += '<option>' + msg_2[j].id + '</option>';
                                }
                            }
                        }, async: false
                    });
                    var obj = msg_j;
                    var bookName = "";
                    for (var i = 0; i < obj.length; i++) {
                        $('.news-comment-container').append('<input type="text" name="table" value="NewsComment" hidden readonly/>'+
                            '<input type="text" name="newsCommentId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="newsCommentText" value="'+obj[i].text+'"/>'+
                            '<input type="text" readonly name="newsCommentPublicateDate" value="'+obj[i].publicateDate+'"/>'+
                            '<input type="text" readonly name="newsCommentNewsIdOld" value="'+obj[i].newsId+'" style="width: 85px"/>'+
                            '<select name="newsCommentNewsId">'+newsList+
                            '</select>'+
                            '<input type="text" readonly name="newsCommentUserIdOld" value="'+obj[i].userId+'" style="width: 85px"/>'+
                            '<select name="newsCommentUserId">'+userList+'</select>'+
                            '<input type="checkbox" name="checkNewsComment" value="'+i+'"/><br>');
                    }
                    $('#new-news-comment-user-id').append(userList);
                    $('#new-news-comment-news-id').append(newsList);
                }
            }
        })
    }

    $.formObject = function ($o) {
        var o = {},
            real_value = function ($field) {
                var val = $field.val() || "";

                // additional cleaning here, if needed

                return val;
            };

        if (typeof o != "object") {
            $o = $(o);
        }

        $(":input[name]", $o).each(function (i, field) {
            var $field = $(field),
                name = $field.attr("name"),
                value = real_value($field);

            if (o[name]) {
                if (!$.isArray(o[name])) {
                    o[name] = [o[name]];
                }

                o[name].push(value);
            }

            else {
                o[name] = value;
            }
        });

        return o;
    }
    $('#update-news-comment').click(function () {
        var formData = JSON.stringify($.formObject($(".news-comment-container")));
        var list = null, res = '';
        list = $(':checkbox:checked');
        list.each(function (ind) {
            res += $(this).val();
            if (ind < list.length - 1) res += ','; // например через запятую
        });
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "NewsComment",
                action: "update",
                formData: formData,
                checkBox: res
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    });
    $('#insert-news-comment').click(function () {
        var newsCommentText = $('#newNewsCommentText').val();
        var newsCommentTextEn = $('#newNewsCommentTextEn').val();
        var newsCommentNewsId = $('#new-news-comment-news-id').val();
        var newsCommentUserId = $('#new-news-comment-user-id').val();
        $.ajax({
            url: '/library/insertData',
            global: true,
            type: 'POST',
            data: {
                table: "NewsComment",
                action: "insert",
                newsCommentText:newsCommentText,
                newsCommentTextEn:newsCommentTextEn,
                newsCommentNewsId:newsCommentNewsId,
                newsCommentUserId:newsCommentUserId
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-news-comment').click(function () {
        var formData = JSON.stringify($.formObject($(".news-comment-container")));
        var list = null, res = '';
        list = $(':checkbox:checked');
        list.each(function (ind) {
            res += $(this).val();
            if (ind < list.length - 1) res += ','; // например через запятую
        });
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "NewsComment",
                action: "delete",
                formData: formData,
                checkBox: res
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    });
    $(window).load(function () {
        if ($('.news-comment-container').val() != null) {
            get_news_comment_data();
        }
    });
})