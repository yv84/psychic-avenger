(function() {
"use strict";
console.log("resources/assets/main.js");

$("#account_handler").on("click", function (event) {
    console.log(event);
    var target = $(event.target);
    if (target.is(".account_delete")) {
        var url = target.attr('href');
        console.log("delete: ", url);
        var data = {}
        data.id = 1;
        $.ajax({
            url: url,
            type: 'DELETE',
            success: function () {
                console.log("successful delete: ", data);
                target.remove();
                return false;
            }
        });
    }
    ;
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
            data = {};
            text = $(event.target).val();
            data.id = id;
            data.username = text;
            $.ajax({
                url: "/account/" + id,
                type: 'PUT',
                data: data,
                success: function () {
                    console.log("successful update: ", data);
                    target.text(text);
                    return false;
                },
                error: function () {
                    console.log("error: ", data);
                }
            });
            input.remove();
            
        });

    }
    ;
    return false;
    }
)
})();