<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <p>Click on the download button for exporting data into the excel file.</p>
    <a href="${pageContext.request.contextPath}/export-to-excel" class="btn btn-primary">Download the Excel File</a>
</div>
<%@ include file="common/footer.jspf"%>