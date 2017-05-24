/**
 * Created by Саша on 22.05.2017.
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
    var sectionList="";
    function get_book_catalog_data() {
        $('.book-catalog-container').html('');
        var page = params['page'];
        console.log(bookList)
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=BookCatalog&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    $.ajaxSetup({
                        url: '/library/getData',
                        global: true,
                        type: 'GET',
                        dataType: "json",
                        data: "tableName=Section&action=GetAll&language=ru_RU"
                    });
                    $.ajax({
                        success: function (msg_2) {
                            if (msg_2 != null) {
                                for (var j = 0; j < msg_2.length; j++) {
                                    sectionList += '<option>' + msg_2[j].id + '</option>';
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
                    console.log(msg_j);
                    var obj = msg_j;
                    var bookName = "";
                    for (var i = 0; i < obj.length; i++) {
                        $('.book-catalog-container').append('  <input type="text" name="bookCatalogId" value="' + obj[i].id + '" readonly/>' +
                            '<input type="number" name="bookCatalogStatus" value="' + obj[i].bookStatus + '"/>' +
                            '<input type="text" name="bookCatalogBookIdOld" value="' + obj[i].bookId + '" style="width:120px;"/>' +
                            '<select  name="bookCatalogBookId">' +bookList+'</select>'+
                            '<input type="text" name="bookCatalogSectionIdOld" readonly value="' + obj[i].sectionId + '" style="width:120px;"/><select  name="bookCatalogSectionId">'
                            + sectionList + '</select> <input type="checkbox" id="checkBookCatalog" value="' + i + '"/><br>');
                    }
                    $('#new-book-catalog-book-id').append(bookList);
                    $('#new-book-catalog-section-id').append(sectionList);
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
    $('#update-book-catalog-button').click(function () {
        var formData = JSON.stringify($.formObject($(".book-catalog-container")));
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
                table: "BookCatalog",
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
    $('#insert-new-data').click(function () {
        var bookCatalogStatus = $('#new-book-catalog-status').val();
        var bookCatalogBookId = $('#new-book-catalog-book-id').val();
        var bookCatalogSectionId = $('#new-book-catalog-section-id').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "BookCatalog",
                action: "insert",
                bookCatalogStatus:bookCatalogStatus,
                bookCatalogBookId:bookCatalogBookId,
                bookCatalogSectionId:bookCatalogSectionId
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-book-catalog-button').click(function () {
        var formData = JSON.stringify($.formObject($(".book-catalog-container")));
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
                table: "BookCatalog",
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
        if ($('.book-catalog-container').val() != null) {
            get_book_catalog_data();
        }
    });
})