/**
 * Created by Саша on 09.05.2017.
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

    function get_author_page_content() {
        $('.content-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            timeout: 1000,
            dataType: "json",
            data: "action=getLimitedNumber&tableName=Author&limit=12&page=" + page
        });
        $.ajax({
            success: function (msg_j) {
                console.log(msg_j);
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].authorName != "") {
                            var bookName = "";
                            if ((obj[i].bookList != null) && (obj[i].bookList.length != 0)) {
                                bookName += "<font style='font-size:18px'>Книги:</font>";
                                for (var j = 0; j < obj[i].bookList.length; j++) {
                                    bookName += '<a href="books?id=' + obj[i].bookList[j].id + '">' + obj[i].bookList[j].bookName + '</a>';
                                    if (j + 1 < obj[i].bookList.length)
                                        bookName += ", ";
                                }
                            }
                            $('.content-container').append('<div class="col-md-4 book-content-table"> <h2>' + obj[i].authorName + '</h2><p>' + (obj[i].authorBiography).substring(0, 255) + '...</p>'
                                + '<p>' + bookName + '</p>' +
                                '<p><a class="btn btn-default" href="author?id=' + obj[i].id + '" role="button">Подробнее»</a></p></div>'
                            )
                        }
                    }
                }
            }
        })

    }
    function get_one_author_page_content() {
        $('.content-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getOneSectionById&tableName=Author&id=" + params['id']
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    console.log(msg_j);
                    var obj = msg_j;
                    var bookName = "";
                    $('.one-author-snp').html(obj[0].authorFemale+' '+obj[0].authorName+' '+obj[0].authorPatronymic);
                    $('.one-author-biography').html(obj[0].authorBiography);
                    if ((obj[0].bookList != null) && (obj[0].bookList.length != 0)) {
                        for (var j = 0; j < obj[0].bookList.length; j++) {
                            bookName += '<a href="books?id=' + obj[0].bookList[j].id + '">' + obj[0].bookList[j].bookName + '</a>';
                            if (j + 1 < obj[0].bookList.length)
                                bookName += "<br> ";
                        }
                    }
                    $('.one-author-books').html(bookName);
                }
            }
        })
    }
    function getPagination() {
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            timeout: 1000,
            dataType: "json",
            data: "tableName=Author&action=GetAll"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    console.log(msg_j.length);
                    $(function () {
                        $('.pagination').pagination({
                            items: msg_j.length,
                            itemsOnPage: 12,
                            cssStyle: 'light-theme'
                        });
                    });
                    if (params['page'] != null) {
                        $(function () {
                            $('.pagination').pagination('selectPage', params['page']);
                        });
                    }
                }
            },
            error: function (msg_j) {
                console.log(msg_j);
            }

        })
    }

    $(window).load(function () {
        if ($('.one-author-container').val() != null) {
            get_one_author_page_content();
        }
        else {
            getPagination();
            get_author_page_content();
        }
    });
})