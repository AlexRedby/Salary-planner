<%@ tag description="Data Table" pageEncoding="UTF-8"%>
<%@ attribute name="tableHeader" fragment="true" %>

<table class="table table-hover">
    <thead class="thead-light">
        <tr>
            <jsp:invoke fragment="tableHeader"/>
        </tr>
    </thead>
    <tbody>
        <jsp:doBody/>
    </tbody>
</table>