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
    function get_section_data() {
        $('.section-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=Section&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {

                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.section-container').append('<input type="text" name="table" value="Section" hidden readonly/>'+
                            '<input type="text" name="sectionId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="sectionName" value="'+obj[i].name+'"/>'+
                            '<input type="text" name="sectionHeader" value="'+obj[i].header+'"/>'+
                            '<input type="text" name="sectionDescription" value="'+obj[i].description+'"/>'+
                            '<input type="number" name="sectionNumber" value="'+obj[i].number+'"/>'+
                            '<input type="checkbox" name="checkSection" value="'+i+'"/><br>');
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
    $('#update-section').click(function () {
        var formData = JSON.stringify($.formObject($(".section-container")));
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
                table: "Section",
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
    $('#insert-section').click(function () {
        var sectionName = $('#newSectionName').val();
        var sectionHeader = $('#newSectionHeader').val();
        var sectionDescription = $('#newSectionDescription').val();
        var sectionNameEn = $('#newSectionNameEn').val();
        var sectionHeaderEn = $('#newSectionHeaderEn').val();
        var sectionDescriptionEn = $('#newSectionDescriptionEn').val();
        var sectionNumber = $('#newSectionNumber').val();
        $.ajax({
            url: '/tables/action',
            global: true,
            type: 'POST',
            data: {
                table: "Section",
                action: "insert",
                sectionName:sectionName,
                sectionHeader:sectionHeader,
                sectionDescription:sectionDescription,
                sectionNameEn:sectionNameEn,
                sectionHeaderEn:sectionHeaderEn,
                sectionDescriptionEn:sectionDescriptionEn,
                sectionNumber:sectionNumber
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-section').click(function () {
        var formData = JSON.stringify($.formObject($(".section-container")));
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
                table: "Section",
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
        if ($('.section-container').val() != null) {
            get_section_data();
        }
    });
})