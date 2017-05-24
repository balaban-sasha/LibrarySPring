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
    var authorList = "";
    function get_book_data() {
        $('.book-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Book&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    $.ajaxSetup({
                        url: '/library/getData',
                        global: true,
                        type: 'GET',
                        dataType: "json",
                        data: "tableName=Author&action=GetAll&language=ru_RU"
                    });
                    $.ajax({
                        success: function (msg_2) {
                            if (msg_2 != null) {
                                for (var j = 0; j < msg_2.length; j++) {
                                    authorList += '<option>' + msg_2[j].id + '</option>';
                                }
                            }
                        }, async: false
                    });
                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.book-container').append('<input type="text" name="table" value="Book" hidden readonly/>'+
                            '<input type="text" name="bookId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="bookName" value="'+obj[i].bookName+'"/>'+
                            '<input type="text" name="bookDate" readonly value="'+obj[i].bookDate+'"/>'+
                           ' <input type="text" name="bookDescription" value="'+obj[i].bookDescription+'"/>'+
                            '<input type="text" name="bookTextLink" value="'+obj[i].bookTextLink+'"/>'+
                            '<input type="number" name="bookAuthorIdOld" value="'+obj[i].authorId+'" readonly style="width:100px;"/>'+
                            '<select  name="bookAuthorId">'+authorList+'</select>'+
                        '<input type="checkbox" name="checkBook" value="'+i+'"/><br>');
                    }
                    $('#new-book-author-id').append(authorList);
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
    $('#update-book').click(function () {
        var formData = JSON.stringify($.formObject($(".book-container")));
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
                table: "Book",
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
    $('#insert-book').click(function () {
        var bookName = $('#newBookName').val();
        var bookDescription = $('#newBookDescription').val();
        var bookTextLink = $('#newBookTextLink').val();
        var bookNameEn = $('#newBookNameEn').val();
        var bookDescriptionEn = $('#newBookDescriptionEn').val();
        var bookTextLinkEn = $('#newBookTextLinkEn').val();
        var bookAuthorId = $('#new-book-author-id').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "Book",
                action: "insert",
                bookName:bookName,
                bookDescription:bookDescription,
                bookTextLink:bookTextLink,
                bookNameEn:bookNameEn,
                bookDescriptionEn:bookDescriptionEn,
                bookTextLinkEn:bookTextLinkEn,
                bookAuthorId:bookAuthorId
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-book').click(function () {
        var formData = JSON.stringify($.formObject($(".book-container")));
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
                table: "Book",
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
        if ($('.book-container').val() != null) {
            get_book_data();
        }
    });
})