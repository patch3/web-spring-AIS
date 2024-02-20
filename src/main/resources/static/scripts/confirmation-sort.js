let csrfToken = $("meta[name='_csrf']").attr("content");
let csrfHeader = $("meta[name='_csrf_header']").attr("content");

let filterInput = document.getElementById("filterInput");
let columnSelector = document.getElementById("columnSelector");


$(document).ready(function () {
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(csrfHeader, csrfToken);
    });

    filterInput.addEventListener("input", updateOption)
    columnSelector.addEventListener("change", updateOption)
});


function updateOption() {
    const column = columnSelector.value;
    const pattern = filterInput.value;

    $.ajax({
        url: '/staff/confirmation/filtered-data',
        type: 'post',
        data: {
            column: column,
            pattern: pattern
        },
        success: function (data) {
            $("#search-result-table tbody tr").remove();
            for (let i = 0; i < data.length; i++) {
                const row = data[i]
                $('#search-result-table > tbody:last-child').append(
                    '<tr><th scope="row">'
                    + row.fullName + '</td><td>'
                    + row.email + '</td><td>'
                    + row.age + '</td><td>'
                    + createHtmlSimplePostButton("/staff/confirmation/accept", row.id) + '</td><td>'
                    + createHtmlSimplePostButton("/staff/confirmation/reject", row.id) + '</td>'
                );
            }
        }
    });
}

function createHtmlSimplePostButton(url, id) {
    const form = createForm(url);
    form.append($('<input/>', {
        type: 'hidden',
        name: csrfHeader,
        value: csrfToken
    }));
    form.append($('<input/>', {
        type: 'hidden',
        name: 'id',
        value: id
    }));
    form.append($('<input/>', {
        type: 'submit',
        value: 'accept',
        class: 'btn btn-success'
    }));
    return form.html()
}
function createForm(url) {
    return $('<form/>', {
        method: 'post',
        action: url
    })
}
