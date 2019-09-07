<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/common.css" rel="stylesheet">


    <script src="/resources/vendor/jquery/jquery.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>



</head>

<body>


<div class="container">
    <c:url var="registrationUrl" value="/registration" />
    <form:form action="${registrationUrl}" method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="First Name"
                            autofocus="true" value="jamileh" ></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="family">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="family" class="form-control" placeholder="Last Name"
                            autofocus="true" value="bahri" ></form:input>
                <form:errors path="family"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true" value="jbahri"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="Your Email"
                            autofocus="true" value="jamilebahri@yahoo.com"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="New Password" value="123"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Re-Enter password" value="123"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="phone">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="phone" class="form-control" placeholder="Phone"
                            autofocus="true" value="33445566"></form:input>
                <form:errors path="phone"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="mobile">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="mobile" class="form-control" placeholder="Mobile"
                            autofocus="true" value="9371653365" ></form:input>
                <form:errors path="mobile"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="nationalId">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="nationalId" class="form-control" placeholder="National Id"
                            autofocus="true" value="0480453225"></form:input>
                <form:errors path="nationalId"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="address" class="form-control" placeholder="Address"
                            autofocus="true" value="tehran"></form:input>
                <form:errors path="address"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="birthday">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" id="date-from" path="birthday"
                            class="form-control" placeholder="birthday"
                            autofocus="true"></form:input>
                <form:errors path="birthday"></form:errors>
            </div>

        </spring:bind>

        <spring:bind path="gender">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:label path="" value="Gender" />Gender :
                <form:radiobutton path="gender" value="MALE" />Male
                <form:radiobutton path="gender" value="FEMALE" />Female
                <form:errors path="gender"></form:errors>

            </div>

        </spring:bind>

        <spring:bind path="role">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:label path="" value="Role" />Role :
                <form:radiobutton path="role" value="admin" />ADMIN
                <form:radiobutton path="role" value="user" />USER
                <form:errors path="role"></form:errors>

            </div>

        </spring:bind>


        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        <button class="btn btn-lg btn-primary btn-block" type="button">Cancel</button>

    </form:form>

</div>


<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>


<script>
    $(function(){
        $("#date-from").datepicker();
    } );
</script>

</body>
</html>
