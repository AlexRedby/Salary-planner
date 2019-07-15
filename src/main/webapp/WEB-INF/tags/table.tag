<%@ tag description="Data Table" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="tableHeader" fragment="true"%>
<%@ attribute name="isHover" required="false" %>

<table class="table <c:if test="${!((!empty isHover) and (isHover == 'false'))}">table-hover</c:if>">
    <thead class="thead-light">
        <tr>
            <jsp:invoke fragment="tableHeader"/>
        </tr>
    </thead>
    <tbody id="tbodyId">
        <jsp:doBody/>
    </tbody>
</table>