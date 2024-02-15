function updateOption() {
    const txt = inputText.value;
    $.ajax({
        url: '/staff/confirmation/getdata',
        data: {
            pattern: txt
        },
        type: 'POST',
        success: function (data) {
            $("#SearchResultTable tbody tr").remove();
            for (let i = 0; i < data.length; i++) {
                $('#SearchResultTable > tbody:last-child').append(
                    '<tr><th scope="row">'
                    + data[i].client.id + '</td><td>'
                    + data[i].client.firstName + '</td><td>'
                    + data[i].client.lastName + '</td><td>'
                    + data[i].book.id + '</td><td>'
                    + data[i].book.name + '</td>'
                );
            }
        }
    });
}

