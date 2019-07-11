<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
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

    <table class="table table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Birth Date</th>
            <th scope="col">Company</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employee}" var="employee">
            <tr onclick="selectRow(this);">
                <td scope="row">${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td><fmt:formatDate value="${employee.birthDate}" pattern="yyyy-MM-dd"/></td>
                <td>${employee.company.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button id="addButton" type="button" class="btn btn-primary" onclick="addNewEmployee();">Add new</button>
    <button id="editButton" type="button" class="btn btn-secondary" onclick="editSelectedEmployee();" disabled>Edit</button>
    <form id="deleteForm" action="${pageContext.request.contextPath}/employee/delete" method="post" style="display: inline-block;">
        <input type="hidden" name="id">
        <button id="deleteButton" type="submit" class="btn btn-secondary" disabled>Delete</button>
    </form>

    <!-- TODO: Вынести в отдельный тег -->
<!-- Modal -->
<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalEditTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/employee/save" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalEditTitle">Add/Edit Modal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
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
                        <label for="company">Company</label>
                        <select class="custom-select" name="company" id="company">
                            <option selected disabled>Select company</option>
                            <c:forEach items="${companies}" var="company">
                                <option value="${company.id}">${company.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

</t:page>