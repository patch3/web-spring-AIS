function updateOption() {
    const column = columnSelector.value;
    const pattern = filterInput.value;

    $.ajax({
        url: '/staff/confirmation/filtered-data',
        type: 'POST',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
        },
        data: {
            column: column,
            pattern: pattern
        },
        success: function (data) {
            $("#search-result-table tbody tr").remove();
            for (let i = 0; i < data.length; i++) {
                $('#search-result-table > tbody:last-child').append(
                    '<tr><th scope="row">'
                    + data[i].fullName + '</td><td>'
                    + data[i].email + '</td><td>'
                    + data[i].age + '</td><td>'
                    + data[i].book.id + '</td><td>'
                    + data[i].book.name + '</td>'
                );
            }
        }
    });
}

let filterInput = document.getElementById("filterInput");
let columnSelector = document.getElementById("columnSelector");

filterInput.addEventListener("input", updateOption)
columnSelector.addEventListener("change", updateOption)


