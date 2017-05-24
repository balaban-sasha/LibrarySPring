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
    var senderList = "";
    function get_chat_data() {
        $('.chat-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Chat&action=GetAll&language=ru_RU"
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
                                    senderList += '<option>' + msg_2[j].id + '</option>';
                                }
                            }
                        }, async: false
                    });
                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.chat-container').append('<input type="text" name="table" value="Chat" hidden readonly/>'+
                            '<input type="text" name="chatId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="chatText" value="'+obj[i].text+'"/>'+
                            '<input type="text" name="chatPublicateDate" value="'+obj[i].publicateDate+'"/>'+
                            '<input type="text" name="chatSenderIdOld" value="'+obj[i].senderId+'" style="width: 120px"/>'+
                            '<select  name="chatSenderId">'+senderList+
                            '</select>'+
                            '<input type="checkbox" name="checkChat" value="'+i+'"/><br>');
                    }
                    $('#new-chat-sender-id').append(senderList);
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
    $('#update-chat').click(function () {
        var formData = JSON.stringify($.formObject($(".chat-container")));
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
                table: "Chat",
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
    $('#insert-chat').click(function () {
        var chatText = $('#newChatText').val();
        var chatTextEn = $('#newChatTextEn').val();
        var chatSenderId = $('#new-chat-sender-id').val();
        $.ajax({
            url: '/library/insertData',
            global: true,
            type: 'POST',
            data: {
                table: "Chat",
                action: "insert",
                chatText:chatText,
                chatTextEn:chate=chatTextEn,
                chatSenderId:chatSenderId
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-chat').click(function () {
        var formData = JSON.stringify($.formObject($(".chat-container")));
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
                table: "Chat",
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
        if ($('.chat-container').val() != null) {
            get_chat_data();
        }
        $.ajax({
            url: '/documents/download',
            global: true,
            type: 'GET',
            data: {
                mime: "pdf",
                ent: "chat"
            },
            success: function (response) {
            },
            error: function (response) {

            }
        }) ;
        $.ajax({
            url: '/documents/download',
            global: true,
            type: 'GET',
            data: {
                mime: "excel",
                ent: "chat"
            },
            success: function (response) {
                console.log(response);
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
                ent: "chat"
            },
            success: function (response) {
                console.log(response);
            },
            error: function (response) {

            }
        })
    });
})