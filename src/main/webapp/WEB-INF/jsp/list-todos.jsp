<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <div>
  <a type="button" class="btn btn-primary btn-md" href="/add-todo">Add Todo</a>
  <a type="button" class="btn btn-primary btn-md" href="/listall-todos">Fetch All Data</a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of TODO's</h3>
  </div>
  <div class="panel-body">
   <table id="dtBasicExample" class="display compact" style="width:100%">
    <thead>
     <tr>
       <th>Description</th>
        <th>Priority</th>
        <th>Target Date</th>
        <th>Done</th>
        <th></th>
      <th></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${todos}" var="todo">
      <tr>
       <td>${todo.description}</td>
       <td>${todo.priority}</td>
       <td><fmt:formatDate value="${todo.targetDate}"
         pattern="dd/MM/yyyy" /></td>
       <td>${todo.done=='true'?'Yes':'No'}</td>
       <td><a type="button" class="btn btn-success"
        href="/update-todo?id=${todo.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
       <td><a type="button" class="btn btn-warning"
        href="/delete-todo?id=${todo.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>