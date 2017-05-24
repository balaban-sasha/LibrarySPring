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

    function get_news_page_content() {
        $('.content-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getLimitedNumber&tableName=News&limit=10&page=" + page
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].header != "")
                            $('.content-container').append('<div class="col-md-4 news-block"> <h2>' + obj[i].header + '</h2><p>' + (obj[i].text).substring(0, 255) + '</p>' + '' +
                                '<p><a class="btn btn-default" href="news?id=' + obj[i].id + '" role="button">Подробнее»</a></p></div>')
                    }
                }
            }
        })
        $.ajaxSetup({
            url: 'library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=News&action=GetAll"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    $(function () {
                        $('.pagination').pagination({
                            items: msg_j.length,
                            itemsOnPage: 10,
                            cssStyle: 'light-theme'
                        });
                    });
                    if (params['page'] != null) {
                        $(function () {
                            $('.pagination').pagination('selectPage', params['page']);
                        });
                    }
                }
            }
        })

    }

    function get_one_news_page_content() {
        $('.content-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getOneSectionById&tableName=News&id=" + params['id']
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {
                    console.log(msg_j);
                    var obj = msg_j;
                    $('.one-news-header').html(obj[0].header);
                    $('.one-news-text').html(obj[0].text);
                    $('.one-news-publicate-date').html(obj[0].publicateDate);
                }
            }
        })
    }

    function get_news_comment_content() {
        $('.news-comment-container').html('');
        var id = params['id'];
        console.log(1);
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "action=getLimitedNumber&tableName=NewsComment&limit=20&id=" + id
        });
        $.ajax({
            success: function (msg_j) {
                console.log(msg_j);
                if (msg_j.length > 0) {
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].text != "")
                            $('.news-comment-container').append('<blockquote style="    background: darkkhaki;"><font style="font-size: 20px;' +
                                'font-weight: 700;">Автор:</font>' + obj[i].userName + '<br><font style=" font-size: 20px;font-weight: 700;">Дата:</font>' +
                                obj[i].publicateDate + '<br>' + obj[i].text + '</blockquote>')
                    }
                }
            },
            error: function (msg_j) {

                console.log(msg_j);

            }
        })

    }

    $('#add_new_comment').click(function () {
        var message = $('#message').val();
        var id = params['id'];
        $.ajax({
            type: 'POST',
            url: '/library/insertData',
            data: {
                newsCommentNewsId: id,
                newsCommentText: message,
                newsCommentTextEn: "",
                action: "insert",
                newsCommentUserId: 0,
                table: "NewsComment"
            },
            success: function (response) {
                $('#message').val('');
                get_news_comment_content();
            },
            error: function (response) {
                $('#message').val('');
                get_news_comment_content();

            }
        });
    });
    $(window).load(function () {
        if ($('.one-news-container').val() != null) {
            get_one_news_page_content();
        }
        else {
            get_news_page_content();
        }
        if ($('.news-comment-container').val() != null)
            get_news_comment_content();
    });

})