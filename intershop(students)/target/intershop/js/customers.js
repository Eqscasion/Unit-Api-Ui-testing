$(document).ready(function(){
    $("#add_new_customer").click(function() {
        $.redirect('/intershop/add_customer.html', {'login': 'admin', 'pass': 'setup', 'role': 'ADMIN'}, 'GET');
    });

    $.get({
        url: 'rest/customers',
        headers: {
            'Authorization': 'Basic ' + btoa('admin' + ':' + 'setup')
        }
    }).done(function(data) {
        var dataSet = [];
        for(var i = 0; i < data.length; i++) {
            var obj = data[i];
            dataSet.push([obj.firstName, obj.lastName, obj.login, obj.pass, obj.balance])
        }

        $('#customer_list_id')
            .DataTable({
                data: dataSet,
                columns: [
                    { title: "Fist Name" },
                    { title: "Last Name" },
                    { title: "Email" },
                    { title: "Pass" },
                    { title: "Balance" }
                ]
            });
    });
});