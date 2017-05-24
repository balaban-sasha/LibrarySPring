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
    function get_author_data() {
        $('.author-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Author&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {

                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.author-container').append('<input type="text" name="table" value="Author" hidden readonly/>'
                           +'<input type="text" name="authorId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="authorName" value="'+obj[i].authorName+'"/>'+
                            '<input type="text" name="authorSurname" value="'+obj[i].authorFemale+'"/>'+
                            '<input type="text" name="authorPatronymic" value="'+obj[i].authorPatronymic+'"/>'+
                            '<input type="text" name="authorBiography" value="'+obj[i].authorBiography+'"/>'+
                            '<input type="checkbox" name="checkAuthor" value="'+i+'"/><br>');
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
    $('#update-author').click(function () {
        var formData = JSON.stringify($.formObject($(".author-container")));
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
                table: "Author",
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
    $('#insert-author').click(function () {
        var authorName = $('#newAuthorName').val();
        var authorSurname = $('#newAuthorSurname').val();
        var authorPatronymic = $('#newAuthorPatronymic').val();
        var authorBiography = $('#newAuthorBiography').val();
        var authorNameEn = $('#newAuthorNameEn').val();
        var authorSurnameEn = $('#newAuthorSurnameEn').val();
        var authorPatronymicEn = $('#newAuthorPatronymicEn').val();
        var authorBiographyEn = $('#newAuthorBiographyEn').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "Author",
                action: "insert",
                authorName:authorName,
                authorSurname:authorSurname,
                authorBiography:authorBiography,
                authorPatronymic:authorPatronymic,
                authorNameEn:authorNameEn,
                authorSurnameEn:authorSurnameEn,
                authorPatronymicEn:authorPatronymicEn,
                authorBiographyEn:authorBiographyEn
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-author').click(function () {
        var formData = JSON.stringify($.formObject($(".author-container")));
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
                table: "Author",
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
        if ($('.author-container').val() != null) {
            get_author_data();
        }
    });
})