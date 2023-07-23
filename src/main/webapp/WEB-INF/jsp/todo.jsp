<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-md-6 col-md-offset-3 ">
   <div class="panel panel-primary">
    <div class="panel-heading">Add TODO</div>
    <div class="panel-body">
     <form:form method="post" modelAttribute="todo">
      <form:hidden path="id" />
      <fieldset class="form-group">
       <form:label path="description">Description</form:label>
       <form:input path="description" type="text" class="form-control" autocomplete="off"
        required="required" />
       <form:errors path="description" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
        <form:label path="priority">Priority</form:label>
        <form:input path="priority" type="number" class="form-control" autocomplete="off"
                required="required" />
         <form:errors path="priority" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
           <form:label path="targetDate">Target Date</form:label>
           <form:input path="targetDate" type="text" class="form-control" autocomplete="off"
            required="required" />
           <form:errors path="targetDate" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
          <form:label path="done">Is Done : </form:label>
          &nbsp;&nbsp;&nbsp;<form:radiobutton path="done" value="true" />&nbsp; Yes
          &nbsp;&nbsp;&nbsp;<form:radiobutton path="done" value="false" />&nbsp; No
          <form:errors path="done" cssClass="text-warning" />
      </fieldset>

      <button type="submit" class="btn btn-success">Save</button>
     </form:form>
    </div>
   </div>
  </div>
 </div>
</div>
<%@ include file="common/footer.jspf"%>