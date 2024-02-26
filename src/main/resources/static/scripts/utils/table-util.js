function createHtmlSimplePostButton(name, url, id) {
    const form = createForm(url);
    form.append($('<input/>', {
        type: 'hidden',
        name: 'id',
        value: id
    }));
    form.append($('<input/>', {
        type: 'submit',
        value: name,
        class: 'btn btn-success'
    }));
    return form.html()
}

function createForm(url) {
    const form = $('<form/>', {
        method: 'post',
        action: url
    })
    form.append($('<input/>', {
        type: 'hidden',
        name: csrfHeader,
        value: csrfToken
    }));
    return form
}


function updateOptionTableEvent(url, successFun) {
    return function () {
        const filterInput = document.getElementById("filterInput");
        const columnSelector = document.getElementById("columnSelector");

        const column = columnSelector.value;
        const pattern = filterInput.value;

        $.ajax({
            url: url,
            type: 'post',
            data: {
                column: column,
                pattern: pattern
            },
            success: function (data) {
                successFun(data);
            }
        });
    }
}
