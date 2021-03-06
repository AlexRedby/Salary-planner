<%@ tag description="Data Table" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="title" required="true"%>
<%@ attribute name="formAction" required="true"%>
<%@ attribute name="formAttribute" required="true"%>

<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="modalEditTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form:form action="${formAction}" modelAttribute="${formAttribute}" method="post">
<%--            <form action="${formAction}" method="post">--%>
                <div class="modal-header">
                    <h5 class="modal-title" id="modalEditTitle">${title}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <jsp:doBody/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
<%--            </form>--%>
            </form:form>
        </div>
    </div>
</div>