/**
 * Created by popov on 13.11.18.
 */

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

function addNewEmployee() {
    clearEmployeeModal();
    $("#modalEdit").modal("show");
}

function editSelectedEmployee() {
    fillEmployeeModal();
    $("#modalEdit").modal("show");
}

function fillEmployeeModal() {
    document.getElementById('id').value = selectedRow.children[0].innerText;
    document.getElementById('firstName').value = selectedRow.children[1].innerText;
    document.getElementById('lastName').value = selectedRow.children[2].innerText;
    document.getElementById('birthDate').value = selectedRow.children[3].innerText;
    // TODO: Как-то переность компанию тоже
}

function clearEmployeeModal() {
    document.getElementById('id').value = "";
    document.getElementById('firstName').value = "";
    document.getElementById('lastName').value = "";
    document.getElementById('birthDate').value = "";
    document.getElementById('company').children[0].selected = true;
}

function addNewCompany() {
    clearCompanyModal();
    $("#modalEdit").modal("show");
}

function editSelectedCompany() {
    fillCompanyModal();
    $("#modalEdit").modal("show");
}

function fillCompanyModal() {
    document.getElementById('id').value = selectedRow.children[0].innerText;
    document.getElementById('companyName').value = selectedRow.children[1].innerText;
}

function clearCompanyModal() {
    document.getElementById('id').value = "";
    document.getElementById('companyName').value = "";
}