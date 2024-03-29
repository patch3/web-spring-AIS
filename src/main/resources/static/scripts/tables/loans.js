const columnSelector = document.getElementById("columnSelector");

const filterInputName = document.getElementById("filterInputName")
const filterInputDescription = document.getElementById("filterInputDescription")
const filterInputInterestRate = document.getElementById("filterInputInterestRate")
const filterInputDuration = document.getElementById("filterInputDuration")
const filterInputAmount = document.getElementById("filterInputAmount")

columnSelector.addEventListener("change", updateTableEvent)
filterInputName.addEventListener("filterInputName", updateTableEvent)
filterInputDescription.addEventListener("filterInputDescription", updateTableEvent)
filterInputInterestRate.addEventListener("filterInputInterestRate", updateTableEvent)
filterInputDuration.addEventListener("filterInputDuration", updateTableEvent)
filterInputAmount.addEventListener("filterInputAmount", updateTableEvent)



function updateTableEvent() {
    $.ajax({
        url: '/staff/confirmation/filtered-data',
        type: 'post',
        data: {
            columnFilter: columnSelector.value,
            patternName: filterInputName.value,
            patternInterestRate: filterInputInterestRate.value,
            patternDuration: filterInputDuration.value,
            patternAmount: filterInputAmount.value
        },
        success: function (data) {
            $("#search-result-table tbody tr").empty();
            for (let i = 0; i < data.length; ++i) {
                const row = data[i]
                $('#search-result-table > tbody:last-child').append(
                    `<tr>
                        <th scope="row">${row.name}</th>
                        <td>${row.description}</td>
                        <td>${row.interestRate}</td>
                        <td>${row.duration}</td>
                        <td>${row.amount}</td>
                        <td><a href='/client/loans/${row.id}'>go to the info....</a></td>
                    </tr>`
                );
            }
        }
    });
}