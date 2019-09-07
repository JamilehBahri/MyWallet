<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>User Panel</title>

    <script src="/resources/vendor/jquery/jquery.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>


    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/sb-admin.css" rel="stylesheet">
    <link href="/resources/css/plugins/morris.css" rel="stylesheet">
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">



<%--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>--%>


        <style type="text/css">
            span.error{
                color: red;
                margin-left: 5px;
            }
        </style>
</head>
<body id="page-top" >
<div>
<nav class="navbar navbar-expand navbar-dark bg-dark static-top m-0">

    <ul class="nav navbar-right top-nav ">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Administrator </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#" id="logoutAdmin"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>

    <%--<a class="navbar-brand mr-1" href="index.html">Start Bootstrap</a>--%>

    <!-- Navbar Search -->
    <%--<form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">--%>
        <%--<div class="input-group">--%>
            <%--<input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">--%>
            <%--<div class="input-group-append">--%>
                <%--<button class="btn btn-primary" type="button">--%>
                    <%--<i class="fas fa-search"></i>--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</form>--%>
</nav>
</div>


<div id="wrapper">

    <div class="collapse navbar-collapse navbar-ex1-collapse " >
        <!-- Sidebar -->
        <ul class="sidebar navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/index">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/users">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Users</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/merchants">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Merchants</span></a>
            </li>
        </ul>
    </div>
    <div id="content-wrapper">
        <!--   Content Page     -->
            <div class="container-fluid">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-lg-8">
                                    <h3 class="panel-title"><i class="fa fa-users"></i> User Profiles Panel</h3>
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" class="btn btn-primary buttenHeader"
                                            data-toggle="modal" data-target="#newUserModal">New User</button>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped" id="usertable">
                                    <thead>
                                    <tr>
                                        <td>Username</td>
                                        <td>Name</td>
                                        <td>Family</td>
                                        <td>Mobile</td>
                                        <td>E-Mail</td>
                                        <td>Role</td>
                                        <td>Balance</td>
                                        <td>Edit</td>
                                        <td>Invoices</td>
                                        <td>Delete</td>
                                    </tr>
                                    <c:forEach items="${usr}" var="usr">
                                    <tr>
                                        <td>${usr.username}</td>
                                        <td>${usr.name}</td>
                                        <td>${usr.family}</td>
                                        <td>${usr.mobile}</td>
                                        <td>${usr.email}</td>
                                        <td>${usr.role}</td>
                                        <td>${usr.balance}</td>
                                        <td><p data-placement='top' data-toggle='tooltip' title='Edit'><button class='btn btn-primary btn-xs editButton' data-title='Edit'><span class='glyphicon glyphicon-pencil'></span></button></p></td>
                                        <td><p data-placement='top' data-toggle='tooltip' title='Invoices'><button class='btn btn-primary btn-xs invoicesButton' data-title='Invoices'><span class='glyphicon glyphicon-list-alt'></span></button></p></td>
                                        <td><p data-placement='top' data-toggle='tooltip' title='Delete'><button class='btn btn-danger btn-xs deleteButton' data-title='Delete'><span class='glyphicon glyphicon-trash'></span></button></p></td>
                                    </tr>
                                    </c:forEach>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                      </div>
                        <div class="panel-footer" id="user-panel-footer">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label id="panelErrorLable" style="color: red; text-align: left"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
   </div>


    <%--<!-- New User Modal -->--%>
    <div id="newUserModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="glyphicon glyphicon-globe"></i>  Register New User</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <form action="#" method="post" class="userRegisterationForm" name="usrForm" role="form">

                                    <div class="row">
                                        <div class="col-lg-6 col-lg-6">
                                            <input class="form-control" name="name" placeholder="First Name" id="name" type="text" value="jamileh" autofocus />
                                        </div>
                                        <div class="col-lg-6 col-lg-6">
                                            <input class="form-control" name="family" placeholder="Last Name" id="family" type="text" value="bahri" />
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="username" placeholder="Username" type="text" id="username" value="jb1370" autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="usernamelable"></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="email" placeholder="Your Email" type="email" id="email" value="j@yahoo.com" autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="maillable"></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="password" placeholder="New Password" type="password" id="password" value="123456789" autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="passwordlable"></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="reenterpassword" placeholder="Re-enter Password" type="password" id="confirmpassword" value="123456789" autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="reenterpasswordlable"></label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-4">
                                            <input class="form-control" name="phone" placeholder="Phone" id="phone" type="text" value="33445566"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <input class="form-control" name="mobile" placeholder="Mobile" type="text" id="mobile" value="9371653365" autofocus/>
                                        </div>
                                        <div class="col-lg-4">
                                            <input class="form-control" name="nationalid" placeholder="National ID" id="nationalid" value="0480484848" type="text"/>
                                        </div>
                                    </div>
                                    <div class="row" id="pmnvalidation">
                                        <div class="col-lg-4">
                                            <label id="phonevalidatemsg"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <label id="mobilevalidatemsg"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <label id="nationalidvalidatemsg"/>
                                        </div>
                                    </div>
                                    <input class="form-control" name="address" placeholder="Address" id="address" type="text" value="tehran" />
                                    <label for="">Birth Date</label>

                                    <div class="row" id="datepicker">
                                       <input class="form-control" name="birthday" placeholder="birthday" id="birthday" type="text" value="1370-11-18" />
                                    </div>

                                    <label>Gender</label>
                                    <div class="form-group">
                                        <div class="col-lg-4">
                                            <label class="radio-inline">
                                                <input type="radio" name="sex" id="inlineRadio1" value="MALE" />
                                                Male
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="sex" id="inlineRadio2" value="FEMALE" />
                                                Female
                                            </label>
                                        </div>
                                        <label class="col-lg-8" for="" id="sexErrorLable" style="color: red;"></label>
                                    </div>
                                    <%--<div class="form-group ">--%>

                                        <%--<input type="radio" name="sex" id="sex1" value='male' checked> male--%>
                                        <%--<input type="radio" name="sex" id="sex2" value='female'> female--%>

                                     <%--</div>--%>

                                    <br />
                                    <label>Role</label>
                                    <div class="form-group">
                                        <div class="col-lg-4">
                                            <label class="radio-inline">
                                                <input type="radio" name="role" id="inlineRadioRole1" value="ROLE_ADMIN" />
                                                ADMIN
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="role" id="inlineRadioRole2" value="ROLE_USER" />
                                                USER
                                            </label>
                                        </div>
                                        <label class="col-lg-8" for="" id="roleErrorLable" style="color: red;"></label>
                                    </div>
                                    <br />
                                    <br />
                                    <br />
                                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

                                    <button class="btn btn-lg btn-primary" type="submit" id="registerUser">Register</button>
                                    <button class="btn btn-lg btn-default" type="button" data-dismiss="modal">Cancel</button>
                                    <br />
                                    <br />

                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Modal body -->
                </div>

                <div class="modal-footer" id="newuser-modal-footer">
                    <div class="row">
                        <div class="col-lg-6">
                            <label id="errorlable" style="color: red; text-align: left"></label>
                        </div>
                    </div>
                </div>
                <!-- Modal Content -->
            </div>
        </div>
    </div>

    <%--<!-- Edit User Modal -->--%>
    <div id="editUserModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">

            <!-- Modal content-->
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="glyphicon glyphicon-globe"></i>  Edit User Profile</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <form action="#" method="put" class="e-userEditForm" role="form">
                                    <div class="row">
                                        <div class="col-lg-6 col-lg-6">
                                            <input class="form-control" name="e-firstname" id="e-firstname" placeholder="First Name" type="text" required autofocus />
                                        </div>
                                        <div class="col-lg-6 col-lg-6">
                                            <input class="form-control" name="e-lastname" id="e-lastname" placeholder="Last Name" type="text" required />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="e-username" id="e-username" placeholder="Username" type="text" required autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="e-usernamelable"></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="e-email" id="e-email" placeholder="Your Email" type="email" required autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="e-maillable"></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="e-password" id="e-password" placeholder="New Password" type="password" required autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="e-passwordlable"></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-9">
                                            <input class="form-control" name="e-reenterpassword" id="e-reenterpassword" placeholder="Re-enter Password" type="password" required autofocus/>
                                        </div>
                                        <div class="col-lg-3">
                                            <label id="e-reenterpasswordlable"></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <input class="form-control" name="e-phone" id="e-phone" placeholder="Phone" type="text"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <input class="form-control" name="e-mobile" id="e-mobile" placeholder="Mobile" type="text" required autofocus/>
                                        </div>
                                        <div class="col-lg-4">
                                            <input class="form-control" name="e-nationalid" id="e-nationalid" placeholder="National ID" type="text"/>
                                        </div>
                                    </div>
                                    <div class="row" id="e-pmnvalidation">
                                        <div class="col-lg-4">
                                            <label id="e-phonevalidatemsg"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <label id="e-mobilevalidatemsg"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <label id="e-nationalidvalidatemsg"/>
                                        </div>
                                    </div>
                                    <input class="form-control" name="e-address" id="e-address" placeholder="Address" type="text" />
                                    <div class="row" id="e-datepicker">
                                        <input class="form-control" name="e-birthday" placeholder="birthday" id="e-birthday" type="text" />
                                    </div>
                                    <label>Gender</label>
                                    <div class="form-group">
                                        <div class="col-lg-4">
                                            <label class="radio-inline">
                                                <input type="radio" name="e-sex" id="e-inlineRadio1" value="MALE" />
                                                Male
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="e-sex" id="e-inlineRadio2" value="FEMALE" />
                                                Female
                                            </label>
                                        </div>
                                        <label class="col-lg-8" for="" id="e-sexErrorLable" style="color: red;"></label>
                                    </div>
                                    <br />
                                    <label>Role</label>
                                    <div class="form-group">
                                        <div class="col-lg-4">
                                            <label class="radio-inline">
                                                <input type="radio" name="e-role" id="e-inlineRadioRole1" value="ROLE_ADMIN" />
                                                ADMIN
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="e-role" id="e-inlineRadioRole2" value="ROLE_USER" />
                                                USER
                                            </label>
                                        </div>
                                        <label class="col-lg-8" for="" id="e-roleErrorLable" style="color: red;"></label>
                                    </div>
                                    <label class="col-lg-8" id="e-id" hidden ></label>

                                    <br />
                                    <br />
                                    <button class="btn btn-lg btn-primary" type="submit">Update</button>
                                    <button class="btn btn-lg btn-default" type="button" data-dismiss="modal">Cancel</button>
                                    <br />
                                    <br />
                                </form>
                                <!-- class="col-lg-12" -->
                            </div>
                        </div>
                    </div>
                    <!-- Edit User Modal body -->
                </div>
                <div class="modal-footer" id="edituser-modal-footer">
                    <div class="row">
                        <div class="col-lg-6">
                            <label id="e-errorlable" style="color: red; text-align: left"></label>
                        </div>
                    </div>
                </div>

                <!--  Modal Content -->
            </div>
            <!-- modal-dialog modal-lg    -->
        </div>
        <!--  Edit User Modal -->
    </div>

    <%--<!-- Invoices Modal -->--%>
    <div class="modal fade" id="invoicesUserModal" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="glyphicon glyphicon-list-alt">  </i><label id="title-invoices">User Invoices</label></h4>
                </div>

                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped" id="invoicesTable">
                            <thead>
                            <tr>
                                <th>Transaction Id</th>
                                <th>Merchant Id</th>
                                <th>M - Name</th>
                                <th>M - Mobile</th>
                                <th>M - Card</th>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Timestamp</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="modal-footer" id="invoice-modal-footer">
                    <div class="row">
                        <div class="col-lg-8">
                        </div>
                        <div class="col-lg-4">
                            <button class="close" type="button" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>
<!-- /#wrapper -->

<!-- Scroll to Top Button-->
<%--<a class="scroll-to-top rounded" href="#page-top">--%>
    <%--<i class="fas fa-angle-up"></i>--%>
<%--</a>--%>

<!-- Logout Modal-->
<%--<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog" role="document">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>--%>
                <%--<button class="close" type="button" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">×</span>--%>
                <%--</button>--%>
            <%--</div>--%>
            <%--<div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>--%>
                <%--<a class="btn btn-primary" href="login.html">Logout</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>




<!-- AJAX add user (POST) -->
<%--<script type="text/javascript">--%>
    <%--$(document).ready(function() {--%>

        <%--//press new user--%>
        <%--$('.userRegisterationForm').on('submit', function(event) {--%>
              <%--event.preventDefault();--%>
            <%--// var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");--%>
            <%--// var csrfHeader = $("meta[name='_csrf_header']").attr("content");--%>
            <%--var csrfToken = $("meta[name='_csrf']").attr("content");--%>
            <%--var header = "X-CSRF-TOKEN";--%>
            <%--if(!userRegistrationFormValidation($(this))) {--%>
                <%--return;--%>
            <%--}--%>
            <%--console.log($('input[type=radio][name=sex]:checked').val());--%>
            <%--console.log( $('form[name=usrForm]').serialize());--%>
            <%--var form_data = {--%>
                <%--balance: 0,--%>
                <%--name:$("#name").val(),--%>
                <%--family: $("#family").val(),--%>
                <%--gender: $('input[type=radio][name=sex]:checked').val(),--%>
                <%--role: $('input[type=radio][name=role]:checked').val(),--%>
                <%--address: $("#address").val(),--%>
                <%--phone: $("#phone").val(),--%>
                <%--mobile:  $("#mobile").val(),--%>
                <%--email:  $("#email").val(),--%>
                <%--nationalId:  $("#nationalid").val(),--%>
                <%--username:  $("#username").val(),--%>
                <%--password:  $("#password").val(),--%>
                <%--birthday:  $("#birthday").val()--%>
            <%--};--%>
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--url: "/users",--%>
                <%--dataType: "json",--%>
                <%--data : form_data,--%>
                <%--// setCookies:session_id,--%>
                <%--&lt;%&ndash;headers: {'X-CSRF-TOKEN': ${_csrf.token}},&ndash;%&gt;--%>
                <%--xhrFields: {--%>
                    <%--withCredentials: false--%>
                <%--},--%>
                <%--beforeSend: function(xhr) {--%>
                    <%--xhr.setRequestHeader(header, csrfToken);--%>
                <%--},--%>
                <%--success: function (response) {--%>
                    <%--if(response.validated){--%>
                        <%--location.reload(true);--%>
                    <%--}--%>
                    <%--else {--%>
                        <%--$.each(response.errorMessages,function(key,value){--%>
                            <%--$('input[name='+key+']').after('<span class="error">'+value+'</span>');--%>
                        <%--});--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function (ex) {--%>
                    <%--console.log(ex);--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>

        <%--////press edit buttun--%>
        <%--$(".editButton").click(function(){--%>
            <%--console.log(this);--%>
            <%--var currentSelectedRow = $(this).closest("tr");--%>
            <%--var currentSelectedUsername = currentSelectedRow.find("td:eq(0)").html();--%>
            <%--console.log(currentSelectedUsername);--%>
            <%--var csrfToken = $("meta[name='_csrf']").attr("content");--%>
            <%--var header = "X-CSRF-TOKEN";--%>

            <%--$.ajax({--%>
                <%--type: 'GET',--%>
                <%--url: "/users/" + currentSelectedUsername,--%>
                <%--xhrFields: {--%>
                    <%--withCredentials: false--%>
                <%--},--%>
                <%--beforeSend: function(xhr) {--%>
                    <%--xhr.setRequestHeader(header, csrfToken);--%>
                <%--},--%>
                <%--success: function (response) {--%>
                    <%--console.log(response);--%>

                    <%--$("#e-id").val(response.id);--%>
                    <%--$("#e-firstname").val(response.name);--%>
                    <%--$("#e-lastname").val(response.family);--%>
                    <%--$("#e-username").val(response.username);--%>
                    <%--$("#e-email").val(response.email);--%>
                    <%--$("#e-password").val(response.password);--%>
                    <%--$("#e-reenterpassword").val(response.password);--%>
                    <%--$("#e-phone").val(response.phone);--%>
                    <%--$("#e-mobile").val(response.mobile);--%>
                    <%--$("#e-nationalid").val(response.nationalId);--%>
                    <%--$("#e-address").val(response.address);--%>
                    <%--$("#e-birthday").val(response.birthday);--%>
                    <%--$("#e-username").prop('disabled', true);--%>
                    <%--if(response.gender == "MALE")--%>
                        <%--$("#e-inlineRadio1").prop('checked', true);--%>
                    <%--else--%>
                        <%--$("#e-inlineRadio2").prop('checked', true);--%>

                    <%--if(response.role == "ROLE_USER")--%>
                        <%--$("#e-inlineRadioRole2").prop('checked', true);--%>
                    <%--else--%>
                        <%--$("#e-inlineRadioRole1").prop('checked', true);--%>


                    <%--$("#editUserModal").modal("show");--%>

                    <%--// if(response.validated){--%>
                    <%--//     location.reload(true);--%>
                    <%--// }--%>
                    <%--// else {--%>
                    <%--//     $.each(response.errorMessages,function(key,value){--%>
                    <%--//         $('input[name='+key+']').after('<span class="error">'+value+'</span>');--%>
                    <%--//     });--%>
                    <%--// }--%>
                <%--},--%>
                <%--error: function (ex) {--%>
                    <%--console.log(ex);--%>
                <%--}--%>
            <%--});--%>

        <%--});--%>

        <%--////Update Users--%>
        <%--$('.e-userEditForm').on('submit', function(event) {--%>
            <%--event.preventDefault();--%>
            <%--var csrfToken = $("meta[name='_csrf']").attr("content");--%>
            <%--var header = "X-CSRF-TOKEN";--%>
            <%--if(!userUpdateFormValidation($(this))) {--%>
                <%--return;--%>
            <%--}--%>
            <%--var form_data = {--%>
                <%--// balance: 0,--%>
                <%--id:$("#e-id").val(),--%>
                <%--name:$("#e-firstname").val(),--%>
                <%--family: $("#e-lastname").val(),--%>
                <%--gender: $('input[type=radio][name=e-sex]:checked').val(),--%>
                <%--role: $('input[type=radio][name=e-role]:checked').val(),--%>
                <%--address: $("#e-address").val(),--%>
                <%--phone: $("#e-phone").val(),--%>
                <%--mobile:  $("#e-mobile").val(),--%>
                <%--email:  $("#e-email").val(),--%>
                <%--nationalId:  $("#e-nationalid").val(),--%>
                <%--password:  $("#e-password").val(),--%>
                <%--birthday:  $("#e-birthday").val(),--%>
                <%--username: $("#e-username").val()--%>
            <%--};--%>
            <%--$.ajax({--%>
                <%--type: "PUT",--%>
                <%--url: "/users",--%>
                <%--dataType: "json",--%>
                <%--data : form_data,--%>
                <%--xhrFields: {--%>
                    <%--withCredentials: false--%>
                <%--},--%>
                <%--beforeSend: function(xhr) {--%>
                    <%--xhr.setRequestHeader(header, csrfToken);--%>
                <%--},--%>
                <%--success: function (response) {--%>
                    <%--if(response.validated){--%>
                        <%--location.reload(true);--%>
                    <%--}--%>
                    <%--else {--%>
                        <%--$.each(response.errorMessages,function(key,value){--%>
                            <%--$('input[name=e-'+key+']').after('<span class="error">'+value+'</span>');--%>
                        <%--});--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function (ex) {--%>
                    <%--console.log(ex);--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>

        <%--////press delete button--%>
        <%--$(".deleteButton").click(function(){--%>
            <%--var currentSelectedRow = $(this).closest("tr");--%>
            <%--var currentSelectedUsername = currentSelectedRow.find("td:eq(0)").html();--%>
            <%--var csrfToken = $("meta[name='_csrf']").attr("content");--%>
            <%--var header = "X-CSRF-TOKEN";--%>

            <%--$.ajax({--%>
                <%--type: 'DELETE',--%>
                <%--url: "/users/" + currentSelectedUsername,--%>
                <%--xhrFields: {--%>
                    <%--withCredentials: false--%>
                <%--},--%>
                <%--beforeSend: function(xhr) {--%>
                    <%--xhr.setRequestHeader(header, csrfToken);--%>
                <%--},--%>
                <%--success: function (response) {--%>
                  <%--location.reload(true);--%>
                <%--},--%>
                <%--error: function (ex) {--%>
                    <%--console.log(ex);--%>
                <%--}--%>
            <%--});--%>

        <%--});--%>

        <%--////press invoices Button--%>
        <%--$(".invoicesButton").click(function () {--%>
            <%--var currentSelectedRow = $(this).closest("tr");--%>
            <%--var currentSelectedUsername = currentSelectedRow.find("td:eq(0)").html();--%>
            <%--var csrfToken = $("meta[name='_csrf']").attr("content");--%>
            <%--var header = "X-CSRF-TOKEN";--%>
            <%--$('#invoicesTable  tbody > tr').empty();--%>


            <%--$.ajax({--%>
                <%--type: 'GET',--%>
                <%--url: "/Invoices/" + currentSelectedUsername,--%>
                <%--xhrFields: {--%>
                    <%--withCredentials: false--%>
                <%--},--%>
                <%--beforeSend: function(xhr) {--%>
                    <%--xhr.setRequestHeader(header, csrfToken);--%>
                <%--},--%>
                <%--success: function (response) {--%>
                    <%--console.log(response);--%>

                        <%--$.each(response, function(i, item) {--%>
                           <%--var $tr = $('<tr>').append(--%>
                                <%--$('<td>').text(item.id),--%>
                                <%--$('<td>').text(item.merchantId),--%>
                                <%--$('<td>').text(""),--%>
                                <%--$('<td>').text(""),--%>
                                <%--$('<td>').text(""),--%>
                                <%--$('<td>').text(item.type),--%>
                                <%--$('<td>').text(item.amount),--%>
                                <%--$('<td>').text(item.timestamp)--%>
                            <%--);--%>
                            <%--$('#invoicesTable').append($tr);--%>

                        <%--});--%>
                    <%--$("#invoicesUserModal").modal("show");--%>
                <%--},--%>
                <%--error: function (ex) {--%>
                    <%--console.log(ex);--%>
                <%--}--%>

            <%--})--%>

    <%--});--%>

    <%--// New User form Functions =============================================================--%>
    <%--function userRegistrationFormValidation(userForm) {--%>

        <%--var passwordInput = $("#password").val();--%>
        <%--if(passwordInput.length < 8 ) {--%>
            <%--userForm.find("#passwordlable").html("Password must be least 8 characters");--%>
            <%--userForm.find("#passwordlable").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--var reenterPasswordInput = $("#confirmpassword").val();--%>
        <%--if(reenterPasswordInput.length !== passwordInput.length) {--%>
            <%--userForm.find("#reenterpasswordlable").html("Re-EnterPassword must be equal password");--%>
            <%--userForm.find("#reenterpasswordlable").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--if(passwordInput.localeCompare(reenterPasswordInput) !== 0) {--%>
            <%--userForm.find("#reenterpasswordlable").html("Re-EnterPassword is not equal password");--%>
            <%--userForm.find("#reenterpasswordlable").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--var userMobile = $("#mobile").val();--%>
        <%--if(userMobile.length < 10 || userMobile.length > 13) {--%>
            <%--userForm.find("#pmnvalidation").show();--%>
            <%--userForm.find("#mobilevalidatemsg").html("Mobile Number is invalid");--%>
            <%--userForm.find("#mobilevalidatemsg").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--if(typeof userForm.find('input[type=radio][name=sex]:checked').val() === "undefined") {--%>
            <%--userForm.find("#sexErrorLable").html("Gender must be select")--%>
            <%--return false;--%>
        <%--}--%>

        <%--if(typeof userForm.find('input[type=radio][name=role]:checked').val() === "undefined") {--%>
            <%--userForm.find("#roleErrorLable").html("Role must be select")--%>
            <%--return false;--%>
        <%--}--%>

        <%--return true;--%>
    <%--}--%>

    <%--// Edit User form Functions =============================================================--%>
    <%--function userUpdateFormValidation(userForm) {--%>

        <%--var passwordInput = $("#e-password").val();--%>
        <%--if(passwordInput.length < 8 ) {--%>
            <%--userForm.find("#e-passwordlable").html("Password must be least 8 characters");--%>
            <%--userForm.find("#e-passwordlable").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--var reenterPasswordInput = $("#e-reenterpassword").val();--%>
        <%--if(reenterPasswordInput.length !== passwordInput.length) {--%>
            <%--userForm.find("#e-reenterpasswordlable").html("Re-EnterPassword must be equal password");--%>
            <%--userForm.find("#e-reenterpasswordlable").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--if(passwordInput.localeCompare(reenterPasswordInput) !== 0) {--%>
            <%--userForm.find("#e-reenterpasswordlable").html("Re-EnterPassword is not equal password");--%>
            <%--userForm.find("#e-reenterpasswordlable").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

        <%--var userMobile = $("#e-mobile").val();--%>
        <%--if(userMobile.length < 10 || userMobile.length > 13) {--%>
            <%--userForm.find("#e-pmnvalidation").show();--%>
            <%--userForm.find("#e-mobilevalidatemsg").html("Mobile Number is invalid");--%>
            <%--userForm.find("#e-mobilevalidatemsg").css("color", "red");--%>
            <%--return false;--%>
        <%--}--%>

         <%--return true;--%>
    <%--}--%>

    <%--// function getCookieVal(cookieName) {--%>
    <%--//     var allCookies = document.cookies;--%>
    <%--//     console.log( " allCookies :‌" + allCookies);--%>
    <%--//--%>
    <%--//     var pos = allCookies.indexOf(cookieName+'=');--%>
    <%--//     if (pos == -1) return null;--%>
    <%--//     var valueStart = pos + (cookieName.length+1);--%>
    <%--//     var valueEnd = allCookies.indexOf(";",valueStart);--%>
    <%--//     if (valueEnd == -1) valueEnd = allCookies.length;--%>
    <%--//     var value = allCookies.substring(valueStart,valueEnd );--%>
    <%--//     value = unescape(value);--%>
    <%--//     if(value == "")--%>
    <%--//         return null;--%>
    <%--//     else--%>
    <%--//         return value;--%>
    <%--//     // value == "" ? return null : return value;--%>
    <%--// }--%>

    <%--});--%>

<%--</script>--%>

</body>

</html>
