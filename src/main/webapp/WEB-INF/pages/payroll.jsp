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
                    <button type="submit" class="btn btn-primary">Call</button>
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
        <jsp:body>
            <c:forEach items="${calledEmployees}" var="employee">
                <tr id="employee-${employee.id}">
                    <td>${employee.firstName} ${employee.lastName}</td>
                    <td>${employee.position.name}</td>
                    <td>${employee.salary}</td>
                    <td>
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <label class="btn btn-outline-success">
                                <input class="form-check-input" type="radio" value="yes" name="issueStatus">
                                Issued
                            </label>
                            <label class="btn btn-outline-warning">
                                <input class="form-check-input" type="radio" value="no" name="issueStatus">
                                Did not come
                            </label>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach items="${employees}" var="employee">
                <tr class="table-secondary">
                    <td>${employee.firstName} ${employee.lastName}</td>
                    <td>${employee.position.name}</td>
                    <td>${employee.salary}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </jsp:body>
    </t:table>
        <button type="submit">TEST</button>
    </form>
    <script>
        var uncalledEmpClass = "table-secondary";

        function displayEmployees(selectE) {
            $.ajax({
                type: "POST",
                url: "payroll/employeesByDepartment",
                data: {
                    "department": selectE.value
                },
                success: function(data) {
                    printEmployees(data);
                }
            });
        }

        function printEmployees(employees) {
            var tbody = document.getElementById("tbodyId");
            tbody.innerHTML = "";

            for(var i in employees) {
                var row = document.createElement('tr');
                row.id = "employee-" + employees[i].id;
                row.classList.add(uncalledEmpClass);

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

                // Empty column
                cell = document.createElement('td');
                row.appendChild(cell);

                // Добавить строку в таблицу
                tbody.appendChild(row);
            }
        }
        
        function xxx(employees) {

        }
    </script>
</t:page>
