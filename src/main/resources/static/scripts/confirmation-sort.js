$(document).ready(function () {
    $('#columnSelector, #filterInput').on('change input', function () {
        applyFilter();
    });

    function applyFilter() {
        const selectedColumn = $('#columnSelector').val();
        const filterText = $('#filterInput').val().toLowerCase();

        $('.client-row').each(function () {
            const rowText = $(this).find('td:eq(' + getColumnIndex(selectedColumn) + ')').text().toLowerCase();
            if (rowText.startsWith(filterText)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }

    function getColumnIndex(columnName) {
        let columnIndex = -1;
        $('th').each(function (index) {
            if ($(this).text() === columnName) {
                columnIndex = index;
                return false; // break the loop
            }
        });
        return columnIndex;
    }
});