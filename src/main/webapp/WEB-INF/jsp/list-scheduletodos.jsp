<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <div>
  <a type="button" class="btn btn-primary btn-md" href="/add-scheduletodo">Add Scheduler</a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of Schedule TODO's</h3>
  </div>
  <div class="panel-body">
   <table id="dtBasicExample" class="display compact" style="width:100%">
    <thead>
     <tr>
       <th>Description</th>
        <th>Priority</th>
        <th>Schedule Job</th>
        <th></th>
        <th></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${schuduletodos}" var="schuduletodos">
      <tr>
       <td>${schuduletodos.description}</td>
       <td>${schuduletodos.priority}</td>
       <td>${schuduletodos.scheduleJob}</td>
       <td><a type="button" class="btn btn-success"
        href="/update-scheduletodo?id=${schuduletodos.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
       <td><a type="button" class="btn btn-warning"
        href="/delete-scheduletodo?id=${schuduletodos.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>