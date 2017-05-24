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
    function get_user_data() {
        $('.user-container').html('');
        var page = params['page'];
        if (page == null)
            page = 1;
        $.ajaxSetup({
            url: '/library/getData',
            global: true,
            type: 'GET',
            dataType: "json",
            data: "tableName=User&action=GetAll&language=ru_RU"
        });
        $.ajax({
            success: function (msg_j) {
                if (msg_j.length > 0) {

                    console.log(msg_j);
                    var obj = msg_j;
                    for (var i = 0; i < obj.length; i++) {
                        $('.user-container').append('  <input type="text" name="table" value="User" hidden readonly/>'+
                            '<input type="text" name="userId" value="'+obj[i].id+'" readonly/>'+
                        '<input type="text" name="userLogin" value="'+obj[i].login+'"/>'+
                            '<input type="text" name="userPassword" value="'+obj[i].password+'"/>'+
                            '<input type="text" name="userName" value="'+obj[i].name+'"/>'+
                            '<input type="text" name="userSurname" value="'+obj[i].female+'"/>'+
                            '<input type="text" name="userGenderOld" value="'+obj[i].gender+'"readonly style="width: 120px"/>'+
                            '<select name="userGender">'+
                            '<option>1</option>'+
                            '<option>2</option>'+
                            '</select>'+
                            '<input type="number" name="userAge" value="'+obj[i].age+'"/>'+
                            '<input type="text" name="userStatusOld" value="'+obj[i].status+'" readonly style="width: 120px"/>'+
                            '<select name="userStatus">'+
                            '<option>1</option>'+
                            '<option>2</option>'+
                            '<option>3</option>'+
                            '<option>0</option>'+
                            '</select>'+
                            '<input type="checkbox" name="checkUser" value="'+i+'"/><br>');
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
    $('#update-user').click(function () {
        var formData = JSON.stringify($.formObject($(".user-container")));
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
                table: "User",
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
    $('#insert-user').click(function () {
        var userAge = $('#newUserAge').val();
        var userGender = $('#newUserGender').val();
        var userLogin = $('#newUserLogin').val();
        var userName = $('#newUserName').val();
        var userPassword = $('#newUserPassword').val();
        var userStatus = $('#newUserStatus').val();
        var userSurname = $('#newUserSurname').val();
        $.ajax({
            url: '/library/insertData',
            global: true,
            type: 'POST',
            data: {
                table: "User",
                action: "insert",
                userAge:userAge,
                userGender:userGender,
                userName:userName,
                userPassword:userPassword,
                userStatus:userStatus,
                userSurname:userSurname,
                userLogin:userLogin
            },
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                window.location.reload();

            }
        })
    })
    $('#delete-user').click(function () {
        var formData = JSON.stringify($.formObject($(".user-container")));
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
                table: "User",
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
        if ($('.user-container').val() != null) {
            get_user_data();
        }
    });
})