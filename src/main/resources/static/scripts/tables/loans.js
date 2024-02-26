const columnSelector = document.getElementById("columnSelector");

const filterInputName = document.getElementById("filterInputName")
const filterInputRate = document.getElementById("filterInputRate")
const filterInputTern = document.getElementById("filterInputTern")

columnSelector.addEventListener("change", updateTableEvent)
filterInputName.addEventListener("filterInputName", updateTableEvent)
filterInputRate.addEventListener("filterInputRate", updateTableEvent)
filterInputTern.addEventListener("filterInputTern", updateTableEvent)


function updateTableEvent() {
    $.ajax({
        url: '/staff/confirmation/filtered-data',
        type: 'post',
        data: {
            columnFilter: columnSelector.value,
            filterInputName: filterInputName.value,
            filterInputRate: filterInputRate.value,
            filterInputTern: filterInputTern.value,
        },
        success: function (data) {
            $("#search-result-table tbody tr").empty();
            for (let i = 0; i < data.length; ++i) {
                const row = data[i]
                $('#search-result-table > tbody:last-child').append(
                    `<tr>
                        <th scope="row">${row.name}</th>
                        <td>${row.interestRate}</td>
                        <td>${row.interestRate}</td>
                        <td>${row.loanTerm}</td>
                        <td><a href='/client/loans/${row.id}'>go to the info....</a></td>
                    </tr>`
                );
            }
        }
    });
}