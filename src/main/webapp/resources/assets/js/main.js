console.log("resources/assets/main.js");

$("#account_handler").on("click", function (event) {
        console.log(event);
        var target = $( event.target );
        if (target.is(".account_delete")) {
            var url = target.attr('href');
            console.log("delete: ", url);
            var data = {}
            data.id = 1;
            $.ajax({
                url: url,
                type: 'DELETE',
                success: function (data) {
                    console.log("sucessfull delete: ", data);
                    return false;
                }
            });
        };
        return false;
    }
)