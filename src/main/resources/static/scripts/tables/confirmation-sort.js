const columnSelector = document.getElementById("columnSelector");
const filterInputFullName = document.getElementById("filterInputFullName");
const filterInputEmail = document.getElementById("filterInputEmail");

columnSelector.addEventListener("change", updateTableEvent)
filterInputFullName.addEventListener("input", updateTableEvent)
filterInputEmail.addEventListener("input", updateTableEvent)


function updateTableEvent() {
    $.ajax({
        url: '/staff/confirmation/filtered-data',
        type: 'post',
        data: {
            columnFilter: columnSelector.value,
            patternFullName: filterInputFullName.value,
            patternEmail: filterInputEmail.value
        },
        success: function (data) {
            $("#search-result-table tbody tr").empty();
            for (let i = 0; i < data.length; ++i) {
                const row = data[i];
                $('#search-result-table > tbody').append(
                    `<tr>
                        <th scope="row">${row.fullName}</th>
                        <td>${row.email}</td>
                        <td><a href="/staff/img/passport/${row.id}">go to photo</a></td>
                        <td>${createHtmlSimplePostButton("accept", "/staff/confirmation/accept", row.id)}</td>
                        <td>${createHtmlSimplePostButton("reject", "/staff/confirmation/reject", row.id)}</td>
                    </tr>`
                );
            }
        }
    })
}





