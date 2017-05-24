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
    var bookList = "";
    var userList="";
    function get_comment_data() {
        $('.comment-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Comment&action=GetAll&language=ru_RU"
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
                        data: "tableName=Book&action=GetAll&language=ru_RU"
                    });
                    $.ajax({
                        success: function (msg_2) {
                            if (msg_2 != null) {
                                for (var j = 0; j < msg_2.length; j++) {
                                    bookList += '<option>' + msg_2[j].id + '</option>';
                                }
                            }
                        }, async: false
                    });
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.comment-container').append('<input type="text" name="table" value="Comment" hidden readonly/>'+
                            '<input type="text" name="commentId" value="'+obj[i].id+'" readonly/>'+
                            '<input type="text" name="commentText" value="'+obj[i].text+'"/>'+
                            '<input type="text" readonly name="commentPublicateDate" value="'+obj[i].publicateDate+'"/>'+
                            '<input type="text" readonly name="commentBookIdOld" value="'+obj[i].bookId+'" style="width: 85px"/>'+
                            '<select name="commentBookId">'+bookList+
                            '</select>'+
                            '<input type="text" readonly name="commentSenderIdOld" value="'+obj[i].senderId+'" style="width: 85px"/>'+
                            '<select name="comentSenderId">'+userList+'</select>'+
                            '<input type="checkbox" name="checkComment" value="'+i+'"/><br>');
                    }
                    $('#new-comment-sender-id').append(userList);
                    $('#new-comment-book-id').append(bookList);
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
    $('#update-comment').click(function () {
        var formData = JSON.stringify($.formObject($(".comment-container")));
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
                table: "Comment",
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
    $('#insert-comment').click(function () {
        var commentText = $('#newCommentText').val();
        var commentTextEn = $('#newCommentTextEn').val();
        var commentBookId = $('#new-comment-book-id').val();
        var commentUserId = $('#new-comment-sender-id').val();
        $.ajax({
            url: '/library/insertData',
            global: true,
            type: 'POST',
            data: {
                table: "Comment",
                action: "insert",
                commentText:commentText,
                commentTextEn:commentTextEn,
                commentUserId:commentUserId,
                commentBookId:commentBookId
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-comment').click(function () {
        var formData = JSON.stringify($.formObject($(".comment-container")));
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
                table: "Comment",
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
        if ($('.comment-container').val() != null) {
            get_comment_data();
        } $.ajax({
            url: '/documents/download',
            global: true,
            type: 'GET',
            data: {
                mime: "pdf",
                ent: "comment"
            },
            success: function (response) {
            },
            error: function (response) {

            }
        })
        $.ajax({
            url: '/documents/download',
            global: true,
            type: 'GET',
            data: {
                mime: "excel",
                ent: "comment"
            },
            success: function (response) {
            },
            error: function (response) {

            }
        })
        $.ajax({
            url: '/documents/download',
            global: true,
            type: 'GET',
            data: {
                mime: "csv",
                ent: "comment"
            },
            success: function (response) {
            },
            error: function (response) {

            }
        })
    });
})