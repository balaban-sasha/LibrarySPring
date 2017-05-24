/**
 * Created by Саша on 23.05.2017.
 */
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
    function get_genre_data() {
        $('.genre-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Genre&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {

                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.genre-container').append(' <input type="text" name="table" value="Genre" hidden readonly/>'+
                            '<input type="text" name="genreId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="genreGenre" value="'+obj[i].genre+'"/>'+
                            '<input type="checkbox" name="checkGenre" value="'+i+'"/><br>');
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
    $('#update-genre').click(function () {
        var formData = JSON.stringify($.formObject($(".genre-container")));
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
                table: "Genre",
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
    $('#insert-genre').click(function () {
        var genreGenre = $('#newGenreGenre').val();
        var genreGenreEn = $('#newGenreGenreEn').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "Genre",
                action: "insert",
                genreGenre:genreGenre,
                genreGenreEn:genreGenreEn
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-genre').click(function () {
        var formData = JSON.stringify($.formObject($(".genre-container")));
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
                table: "Genre",
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
        if ($('.genre-container').val() != null) {
            get_genre_data();
        }
    });
})