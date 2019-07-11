"use strict";

var selectedRow;

function selectRow(row) {
    if (selectedRow !== undefined)
        selectedRow.classList.remove('table-active');
    row.classList.add('table-active');
    selectedRow = row;

    document.getElementById('deleteButton').disabled = false;
    document.getElementById('deleteForm').children[0].value = row.children[0].innerText;
    document.getElementById('editButton').disabled = false;
}

function addNewEntity(clearModalFunction) {
    clearModalFunction();
    $("#modalEdit").modal("show");
}

function editSelectedEntity(fillModalFunction) {
    fillModalFunction();
    $("#modalEdit").modal("show");
}