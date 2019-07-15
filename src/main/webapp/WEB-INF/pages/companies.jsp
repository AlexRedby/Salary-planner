<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:page title="Companies">
    <h1 class="text-center">Companies</h1>

    <t:table>
        <jsp:attribute name="tableHeader">
            <th scope="col">ID</th>
            <th scope="col">Name</th>
        </jsp:attribute>
        <jsp:body>
            <c:forEach items="${companies}" var="company">
                <tr onclick="selectRow(this);">
                    <td scope="row">${company.id}</td>
                    <td>${company.name}</td>
                </tr>
            </c:forEach>
        </jsp:body>
    </t:table>

    <button id="addButton" type="button" class="btn btn-primary" onclick="addNewEntity(clearCompanyModal);">Add new</button>
    <button id="editButton" type="button" class="btn btn-secondary" onclick="editSelectedEntity(fillCompanyModal);" disabled>Edit</button>
    <form id="deleteForm" action="${pageContext.request.contextPath}/companies/delete" method="post" style="display: inline-block;">
        <input type="hidden" name="id">
        <button id="deleteButton" type="submit" class="btn btn-secondary" disabled>Delete</button>
    </form>

    <t:modal title="Add/Edit company window" formAction="${pageContext.request.contextPath}/companies/save" formAttribute="company">
        <form:hidden path="id" id="id"/>
<%--        <input type="hidden" name="id" id="id">--%>
        <div class="form-group">
            <label for="name">Company Name</label>
            <form:input path="name" id="name" cssClass="form-control" placeholder="Enter company name"/>
            <small><form:errors path="name" cssClass="error"/></small>
<%--            <input type="text" class="form-control" id="companyName" name="name" placeholder="Enter company name">--%>
        </div>
    </t:modal>
    <script>
        function fillCompanyModal() {
            document.getElementById('id').value = selectedRow.children[0].innerText;
            document.getElementById('name').value = selectedRow.children[1].innerText;
        }

        function clearCompanyModal() {
            document.getElementById('id').value = "";
            document.getElementById('name').value = "";
        }
    </script>
</t:page>