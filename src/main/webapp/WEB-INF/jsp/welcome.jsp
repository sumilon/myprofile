<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">

 <div class="panel panel-primary">
     <div class="panel-heading">Home Page</div>
        <div class="panel-body">
            <jsp:useBean id="now" class="java.util.Date" />
            <fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" />
            <br>
           Welcome ${name}, Have a nice day!!!
        </div>
     </div>
 </div>
<%@ include file="common/footer.jspf"%>