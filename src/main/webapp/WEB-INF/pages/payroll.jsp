<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Payroll">
    <h1 class="text-center">Payroll</h1>
    <div class="card bg-light mb-3">
        <div class="card-body">
            <div class="form-row">
                <div class="col">
                    <select class="custom-select" name="department" id="department" onchange="displayEmployees(this)">
                        <option selected disabled>Select department</option>
                        <c:forEach items="${departments}" var="department">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <button id="btnCall" type="button" class="btn btn-primary" onclick="callNewEmployee()" disabled>Call</button>
                </div>
            </div>
        </div>
    </div>
    <form action="/" method="get">
    <t:table isHover="false">
        <jsp:attribute name="tableHeader">
            <th scope="col">Name</th>
            <th scope="col">Position</th>
            <th scope="col">Salary</th>
            <th scope="col"></th>
        </jsp:attribute>
    </t:table>
        <button type="submit">TEST</button>
    </form>
    <!-- Confirm Modal -->
    <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
         aria-labelledby="confirmModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmModalTitle">Confirm modal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure that you issued salary to
                    <span class="font-weight-bold" id="confirmModalName"></span>?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary mr-auto"
                            onclick="issueSalary(this);" id="confirmModalOkButton">OK</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        const employeeStatuses = {
            CALLED: 0,
            ISSUED: 1,
            WAITING: 2
        };
        var waitingEmpClass = "table-secondary";
        var issuedEmpClass = "table-success";

        // Переносит данные из кнопки в модальное окно
        $('#confirmModal').on('show.bs.modal', function (event) {
            // Button that triggered the modal
            var button = $(event.relatedTarget);
            // Extract info from data-* attributes
            $('#confirmModalName').text(button.data('name'));
            $('#confirmModalOkButton').val(button.data('id'));
        });

        function issueSalary(elem) {
            var id = elem.value;

            $.ajax({
                type: "POST",
                url: "payroll/issueSalary",
                data: {
                    "id" : id
                },
                success: function(data) {
                    var row = document.getElementById("employee-" + id);
                    row.parentNode.appendChild(row);
                    row.classList.add(issuedEmpClass);
                    row.lastElementChild.innerText = "Got paid";

                    $('#confirmModal').modal('hide');

                    updateCallBtn();
                }
            });
        }

        function callNewEmployee() {
            var checkboxes = document.getElementsByName("didNotCome");
            var truantsIds = [];

            for(var i in checkboxes)
                if(checkboxes[i].checked)
                    truantsIds.push(checkboxes[i].value);

            $.ajax({
                type: "POST",
                url: "payroll/callNewEmployees",
                data: {
                    "truants" : truantsIds
                },
                success: function(employees) {
                    console.log(employees);

                    if(employees.length > 0)
                        document.getElementById("btnCall").disabled = true;

                    for(var i in employees) {
                        var row = document.getElementById("employee-" + employees[i].id);
                        row.parentNode.insertBefore(row, row.parentNode.firstChild);
                        customizeEmployeeRow(row, employees[i], employeeStatuses.CALLED);
                    }
                    // truants убрать
                    for(var i in truantsIds) {
                        var row = document.getElementById("employee-" + truantsIds[i]);
                        customizeEmployeeRow(row, null, employeeStatuses.WAITING);
                    }
                }
            });
        }

        function displayEmployees(selectE) {
            $.ajax({
                type: "POST",
                url: "payroll/employeesByDepartment",
                data: {
                    "department": selectE.value
                },
                success: function(payrollQueue) {
                    if(payrollQueue.calledEmployees.length > 0)
                        document.getElementById("btnCall").disabled = true;
                    else
                        document.getElementById("btnCall").disabled = false;

                    document.getElementById("tbodyId").innerHTML = "";
                    printEmployees(payrollQueue.calledEmployees, employeeStatuses.CALLED);
                    printEmployees(payrollQueue.waitingEmployees, employeeStatuses.WAITING);
                    printEmployees(payrollQueue.issuedEmployees, employeeStatuses.ISSUED);
                }
            });
        }

        function printEmployees(employees, status) {
            var tbody = document.getElementById("tbodyId");

            for(var i in employees) {
                var row = document.createElement('tr');
                row.id = "employee-" + employees[i].id;

                // Employee name column
                var cell = document.createElement('td');
                cell.innerHTML = employees[i].firstName + " " + employees[i].lastName;
                row.appendChild(cell);

                // Employee position name column
                cell = document.createElement('td');
                cell.innerHTML = employees[i].position.name;
                row.appendChild(cell);

                // Employee salary column
                cell = document.createElement('td');
                cell.innerHTML = employees[i].salary;
                row.appendChild(cell);

                // Last column
                cell = document.createElement('td');
                row.appendChild(cell);

                customizeEmployeeRow(row, employees[i], status);

                // Добавить строку в таблицу
                tbody.appendChild(row);
            }
        }

        function customizeEmployeeRow(row, employee, status) {
            row.className = "";
            row.lastElementChild.innerHTML = "";

            switch (status) {
                case employeeStatuses.WAITING:
                    row.classList.add(waitingEmpClass);
                    row.lastElementChild.innerText = "Waiting salary";
                    break;
                case employeeStatuses.CALLED:
                    var elem = document.createElement('button');
                    elem.setAttribute("type", "button");
                    elem.setAttribute("data-toggle", "modal");
                    elem.setAttribute("data-target", "#confirmModal");
                    elem.setAttribute("data-id", employee.id);
                    elem.setAttribute("data-name", employee.firstName + employee.lastName);
                    elem.setAttribute("class", "btn btn-success mr-3");
                    elem.innerText = "Issued";
                    row.lastElementChild.appendChild(elem);

                    var input = document.createElement('input');
                    input.setAttribute("type", "checkbox");
                    input.setAttribute("name", "didNotCome");
                    input.setAttribute("value", employee.id);
                    input.setAttribute("style", "visibility: hidden; position: absolute;");
                    input.setAttribute("onChange", "updateCallBtn()");

                    var label = document.createElement('label');
                    label.setAttribute("class", "btn btn-outline-danger mb-0");
                    label.appendChild(input);
                    label.append("Did not come");

                    elem = document.createElement('div');
                    elem.setAttribute("data-toggle", "buttons");
                    elem.setAttribute("style", "display: inline-block;");
                    elem.appendChild(label);
                    row.lastElementChild.appendChild(elem);
                    break;
                case employeeStatuses.ISSUED:
                    row.classList.add(issuedEmpClass);
                    row.lastElementChild.innerText = "Got paid";
                    break;
            }
        }

        function updateCallBtn() {
            var checkboxes = document.getElementsByName("didNotCome");

            for (var i = 0; i < checkboxes.length; i++)
                if (!checkboxes[i].checked) {
                    document.getElementById("btnCall").disabled = true;
                    return;
                }

            document.getElementById("btnCall").disabled = false;
        }
    </script>
</t:page>
