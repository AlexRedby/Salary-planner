<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
    <h1 class="text-center">Companies</h1>

    <table class="table table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${companies}" var="company">
            <tr onclick="selectRow(this);">
                <td scope="row">${company.id}</td>
                <td>${company.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button id="addButton" type="button" class="btn btn-primary" onclick="addNewCompany();">Add new</button>
    <button id="editButton" type="button" class="btn btn-secondary" onclick="editSelectedCompany();" disabled>Edit</button>
    <form id="deleteForm" action="${pageContext.request.contextPath}/companies/delete" method="post" style="display: inline-block;">
        <input type="hidden" name="id">
        <button id="deleteButton" type="submit" class="btn btn-secondary" disabled>Delete</button>
    </form>

<!-- Modal -->
<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalEditTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/companies/save" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalEditTitle">Add/Edit Modal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="id" id="id">
                    <div class="form-group">
                        <label for="companyName">Company Name</label>
                        <input type="text" class="form-control" id="companyName" name="name" placeholder="Enter company name">
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