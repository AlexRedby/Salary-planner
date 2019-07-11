<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Employees">
    <h1 class="text-center">Employees</h1>

    <div class="card bg-light mb-3">
        <div class="card-header">
            Filtering
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link active" id="filter-by-name-tab" data-toggle="tab" href="#filter-by-name" role="tab" aria-controls="filter-by-name" aria-selected="true">By Name</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="filter-by-birth-date-tab" data-toggle="tab" href="#filter-by-birth-date" role="tab" aria-controls="filter-by-birth-date" aria-selected="false">By Birth Date</a>
                </li>
            </ul>
        </div>
        <div class="card-body">
            <div class="tab-content">
                <div class="tab-pane active" id="filter-by-name" role="tabpanel" aria-labelledby="filter-by-name-tab">
                    <form action="${pageContext.request.contextPath}/employee/findByName" method="post">
                        <div class="form-row">
                            <div class="col">
                                <input type="text" class="form-control" name="firstName" placeholder="First name">
                            </div>
                            <div class="col">
                                <input type="text" class="form-control" name="lastName" placeholder="Last name">
                            </div>
                            <div class="col">
                                <button type="submit" class="btn btn-primary">Find</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="tab-pane" id="filter-by-birth-date" role="tabpanel" aria-labelledby="filter-by-birth-date-tab">
                    <form action="${pageContext.request.contextPath}/employee/findByAge" method="post" class="form-inline">

                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <label class="btn btn-secondary active">
                                <input class="form-check-input" type="radio" value="younger" name="compare" checked>
                                younger
                            </label>
                            <label class="btn btn-secondary">
                                <input class="form-check-input" type="radio" value="older" name="compare">
                                older
                            </label>
                        </div>

                        <div class="ml-2 mr-2">than</div>
                        <input class="form-control" type="number" min="0" name="age" placeholder="Enter age">
                        <div class="ml-2 mr-2">years</div>

                        <button type="submit" class="btn btn-primary">Filter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <t:table>
        <jsp:attribute name="tableHeader">
            <th scope="col">ID</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Birth Date</th>
            <th scope="col">Salary</th>
            <th scope="col">Position</th>
            <th scope="col">Department</th>
        </jsp:attribute>
        <jsp:body>
            <c:forEach items="${employees}" var="employee">
                <tr onclick="selectRow(this);">
                    <td scope="row">${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td><fmt:formatDate value="${employee.birthDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${employee.salary}</td>
                    <td style="display:none;">${employee.position.id}</td>
                    <td>${employee.position.name}</td>
                    <td style="display:none;">${employee.department.id}</td>
                    <td>${employee.department.name}</td>
                </tr>
            </c:forEach>
        </jsp:body>
    </t:table>

    <button id="addButton" type="button" class="btn btn-primary" onclick="addNewEntity(clearEmployeeModal);">Add new</button>
    <button id="editButton" type="button" class="btn btn-secondary" onclick="editSelectedEntity(fillEmployeeModal);" disabled>Edit</button>
    <form id="deleteForm" action="${pageContext.request.contextPath}/employee/delete" method="post" style="display: inline-block;">
        <input type="hidden" name="id">
        <button id="deleteButton" type="submit" class="btn btn-secondary" disabled>Delete</button>
    </form>

    <t:modal title="Add/Edit employee window" formAction="${pageContext.request.contextPath}/employee/save">
        <input type="hidden" name="id" id="id">
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter employee first name">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter employee last name">
        </div>
        <div class="form-group">
            <label for="birthDate">Birth Date</label>
            <input type="date" class="form-control" id="birthDate" name="birthDate" placeholder="Enter employee birth date">
        </div>
        <div class="form-group">
            <label for="lastName">Salary</label>
            <input type="number" min="0" class="form-control" id="salary" name="salary" placeholder="Enter employee salary">
        </div>
        <div class="form-group">
            <label for="position">Position</label>
            <select class="custom-select" name="position" id="position">
                <option selected disabled>Select position</option>
                <c:forEach items="${positions}" var="position">
                    <option value="${position.id}">${position.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="department">Department</label>
            <select class="custom-select" name="department" id="department">
                <option selected disabled>Select department</option>
                <c:forEach items="${departments}" var="department">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select>
        </div>
    </t:modal>
    <script>
        function fillEmployeeModal() {
            document.getElementById('id').value = selectedRow.children[0].innerText;
            document.getElementById('firstName').value = selectedRow.children[1].innerText;
            document.getElementById('lastName').value = selectedRow.children[2].innerText;
            document.getElementById('birthDate').value = selectedRow.children[3].innerText;
            document.getElementById('salary').value = selectedRow.children[4].innerHTML;
            document.getElementById('position').value = selectedRow.children[5].innerHTML;
            document.getElementById('department').value = selectedRow.children[7].innerHTML;
        }

        function clearEmployeeModal() {
            document.getElementById('id').value = "";
            document.getElementById('firstName').value = "";
            document.getElementById('lastName').value = "";
            document.getElementById('birthDate').value = "";
            document.getElementById('salary').value = 0;
            document.getElementById('position').children[0].selected = true;
            document.getElementById('department').children[0].selected = true;
        }
    </script>
</t:page>