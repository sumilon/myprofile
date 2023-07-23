<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <h2>File Upload & Data Save</h2>

    <form action ="uploadFile" method = "POST" enctype = "multipart/form-data">
        <br /> <br />
         Please select a file to upload :
         <input type = "file" name = "file" value = "Browse File" /> <br /> <br />
         Press here to upload the file :
         <input type = "submit" value = "upload" class="btn btn-primary" /> <br /> <br />

         <h4 style="color: green">${message}</h4> <br />

         Do you want to save excel data into database ? <a href="${pageContext.request.contextPath}/saveData"><b>Yes</b></a> &nbsp &nbsp <a href="/"><b>No</b></a>
    </form>
</div>
<%@ include file="common/footer.jspf"%>

