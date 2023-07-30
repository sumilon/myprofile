<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <p>Click on the download button for exporting data into the excel file.</p>
    <a href="${pageContext.request.contextPath}/export-to-excel" class="btn btn-primary">Download the Excel File</a>
    <br/><br/><br/>

     <div class="panel panel-primary">
      <div class="panel-heading">
       <h3>User Details</h3>
      </div>
      <div class="panel-body">
       <table id="dtBasicExample" class="display compact" style="width:100%">
        <thead>
         <tr>
          <th>Username</th>
          <th>Message Count</th>
         </tr>
        </thead>
        <tbody>
         <c:forEach items="${userdetails}" var="userdetails">
          <tr>
           <td>${userdetails.username}</td>
           <td>${userdetails.messageCount}</td>
          </tr>
         </c:forEach>
        </tbody>
       </table>
      </div>
     </div>

</div>
<%@ include file="common/footer.jspf"%>