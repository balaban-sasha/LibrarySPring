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
    var genreList="";
    function get_book_genre_data() {
        $('.book-genre-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=BookGenre&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    $.ajaxSetup({
                        url: '/library/getData',
                        global: true,
                        type: 'GET',
                        dataType: "json",
                        data: "tableName=Genre&action=GetAll&language=ru_RU"
                    });
                    $.ajax({
                        success: function (msg_2) {
                            if (msg_2 != null) {
                                for (var j = 0; j < msg_2.length; j++) {
                                    genreList += '<option>' + msg_2[j].id + '</option>';
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
                        $('.book-genre-container').append(' <input type="text" name="table" value="BookGenre" hidden readonly/>'+
                            '<input type="text" name="bookGenreId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="bookGenreBookIdOld"readonly value="'+obj[i].bookId+'" style="width:85px;"/>'+
                            '<select  name="bookGenreBookId">'+bookList+
                            '</select>'+
                            '<input type="text" name="bookGenreGenreIdOld" readonly value="'+obj[i].genreId+'"style="width:85px;"/>'+
                            '<select  name="bookGenreGenreId">'+genreList+
                            '</select>'+
                            '<input type="checkbox" name="checkBookGenre" value="'+i+'"/><br>');
                    }
                    $('#new-book-genre-genre-id').append(genreList);
                    $('#new-book-genre-book-id').append(bookList);
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
    $('#update-book-genre').click(function () {
        var formData = JSON.stringify($.formObject($(".book-genre-container")));
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
                table: "BookGenre",
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
    $('#insert-book-genre').click(function () {
        var bookGenreBookId = $('#new-book-genre-book-id').val();
        var bookGenreGenreId = $('#new-book-genre-genre-id').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "BookGenre",
                action: "insert",
                bookGenreBookId:bookGenreBookId,
                bookGenreGenreId:bookGenreGenreId
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-book-genre').click(function () {
        var formData = JSON.stringify($.formObject($(".book-genre-container")));
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
                table: "BookGenre",
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
        if ($('.book-genre-container').val() != null) {
            get_book_genre_data();
        }
    });
})