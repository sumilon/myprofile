<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-lg-8 col-lg-offset-2 ">
   <div class="panel panel-primary">
    <div class="panel-heading">Add Diary Notes</div>
    <div class="panel-body">
     <form:form method="post" modelAttribute="add-diary">
      <form:hidden path="id" />

      <fieldset class="form-group">
        <form:label path="createdDate">Date</form:label>
        <form:input path="createdDate" type="text" class="form-control" autocomplete="off"
                required="required" />
        <form:errors path="createdDate" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
       <form:label path="notes">Notes</form:label>
       <form:textarea path="notes" rows="10" class="form-control" required="required" cssStyle="font-style:italic"/>
       <form:errors path="notes" cssClass="text-warning" />
      </fieldset>

      <button type="submit" class="btn btn-success">Save</button>
     </form:form>
    </div>
   </div>
  </div>
 </div>
</div>
<%@ include file="common/footer.jspf"%>