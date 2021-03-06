(function($, undefined) {
"use strict";
console.log("resources/assets/main.js");

$("#account_handler").on("click", function (event) {
    //console.log(event);
    var target = $(event.target);
    var data = {};
        
    if (target.is(".account_delete")) {
        var url = target.attr('href');
        console.log("delete: ", url);
        data.id = 1;
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function () {
                console.log("successful delete: ");
                target.parent().remove();
                return false;
            },
            error: function (e) {
                console.log("error: ", data, e);
            }
        });
    }
        
    if (target.is(".account_username")) {
        var id = parseInt(target.parent().children(".account_id").text(), 10);
        console.log(id);
        var text = target.text();
        var input = null;
        target.text("");

        target.after(function () {
            input = $('<input type="text" value="' + text + '" />');
            return input;
        });
        input.on("blur", function (event) {
            text = $(event.target).val();
            data.id = id;
            data.username = text;
            $.ajax({
                url: "/account/" + id,
                type: 'PUT',
                data: data,
                success: function (result) {
                    console.log("successful update: ", data, result);
                    target.text(text);
                    return false;
                },
                error: function (e) {
                    console.log("error: ", data, e);
                }
            });
            input.remove();
            
        });
    }

    if (target.is("a.account_add")) {
        data.username = $("input.account_add").val();
        console.log("add: " + data.username);
        $.ajax({
            url: "/account/",
            type: 'POST',
            data: data,
            success: function (result) {
                console.log("successful add: ", data, result);
                $("#account_handler>p:last-child").before(function() {
                    return "<p>" +
                        "<span class=\"account_id\">" + result.id + "</span>," +
                        "<span class=\"account_username\">" + result.username + "</span>," +
                        "<a class=\"account_delete\" href=\"/account/" + result.id + "\">Delete</a>" +
                        "</p>"
                });
                return false;
            },
            error: function (e) {
                console.log("error: ", data, e);
            }
        });
    }
    
    return false;
})
})(jQuery);