<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Register User</title>
</head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>

    <div class="container">
        <h2 class="form-heading">Register New User</h2>
        <div id="registerEmployee" class="form-group">
            <form:form action="/register" method="post" modelAttribute="user">
                <span style="color:red">${message}</span>
                <p>
                    <label>Enter username</label>
                    <form:input path="username" autocomplete="off" class="form-control" />
                </p>
                <p>
                    <label>Enter password</label>
                    <form:password path="password" autocomplete="off" class="form-control" />
                </p>
                <p hidden>
                    <label>Enter role</label>
                    <form:select path="role" class="form-control">
                        <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                        <option value="ROLE_USER" selected >ROLE_USER</option>
                    </form:select>
                </p>
                <input type="SUBMIT" class="btn btn-lg btn-primary btn-block" value="Register" />
            </form:form>
        </div>
    </div>
</body>
</html>
