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
    function get_news_data() {
        $('.news-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=News&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {

                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.news-container').append('<input type="text" name="table" value="News" hidden readonly/>'+
                        '<input type="text" name="newsId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="newsHeader" value="'+obj[i].header+'"/>'+
                            '<input type="text" name="newsText" value="'+obj[i].text+'"/>'+
                            '<input type="text" name="newsPublicateDate" readonly value="'+obj[i].publicateDate+'"/>'+
                            '<input type="checkbox" name="checkNews" value="'+i+'"/><br>');
                    }
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
    $('#update-news').click(function () {
        var formData = JSON.stringify($.formObject($(".news-container")));
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
                table: "News",
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
    $('#insert-news').click(function () {
        var newsHeader = $('#newNewsHeader').val();
        var newsHeaderEn = $('#newNewsHeaderEn').val();
        var newsText = $('#newNewsText').val();
        var newsTextEn = $('#newNewsTextEn').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "News",
                action: "insert",
                newsHeader:newsHeader,
                newsHeaderEn:newsHeaderEn,
                newsTextEn:newsTextEn,
                newsText:newsText
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-news').click(function () {
        var formData = JSON.stringify($.formObject($(".news-container")));
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
                table: "News",
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
        if ($('.news-container').val() != null) {
            get_news_data();
        }$.ajax({
            url: '/documents/download',
            global: true,
            type: 'GET',
            data: {
                mime: "pdf",
                ent: "news"
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
                ent: "news"
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
                ent: "news"
            },
            success: function (response) {
            },
            error: function (response) {

            }
        })
    });
})