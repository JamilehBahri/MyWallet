<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Merchant Panel</title>

    <%--<!-- Custom fonts for this template-->--%>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <%--<!-- Page level plugin CSS-->--%>
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <%--<!-- Custom styles for this template-->--%>
    <link href="css/sb-admin.css" rel="stylesheet">

    <style type="text/css">
        span.error{
            color: red;
            margin-left: 5px;
        }
    </style>
</head>

<body id="page-top">
<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="../old/index.jsp">Start Bootstrap</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                    <i class="fas fa-search"></i>
                </button>
            </div>
        </div>
    </form>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <span class="badge badge-danger">9+</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>
                <span class="badge badge-danger">7</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">Settings</a>
                <a class="dropdown-item" href="#">Activity Log</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
            </div>
        </li>
    </ul>

</nav>

<div id="wrapper">
            <!-- Sidebar -->
                <ul class="sidebar navbar-nav m-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/">
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
                    <li class="nav-item">
                        <a class="nav-link" href="/incrementcredit">
                            <i class="fas fa-credit-card"></i>
                            <span>Increment Credit</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/payment">
                            <i class="fas fa-fw fa-table"></i>
                            <span>Payment</span></a>
                    </li>
                </ul>

            <div id="content-wrapper">
                <!--   Content Page     -->
                <div class="container-fluid">
                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <div class="row">
                                <div class="col-lg-8">
                                    <h3 class="panel-title"><i class="fa fa-users"></i> Merchant Profiles Panel</h3>
                                </div>
                                <div class="col-lg-4">
                                    <button type="button" class="btn btn-primary buttenHeader"
                                            data-toggle="modal" data-target="#newMerchantModal">New Merchant</button>
                                </div>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="merchantTable" width="100%" cellspacing="0" >
                                    <thead>
                                    <tr style="font-weight:bold">
                                        <td>ID</td>
                                        <td>Name</td>
                                        <td>Family</td>
                                        <td>Mobile</td>
                                        <td>E-Mail</td>
                                        <td>Debit Card PAN</td>
                                        <td>Edit</td>
                                        <td>Invoices</td>
                                        <td>Delete</td>
                                    </tr>
                                    <c:forEach items="${merchant}" var="merchant">
                                        <tr>
                                            <td>${merchant.merchantId}</td>
                                            <td>${merchant.name}</td>
                                            <td>${merchant.family}</td>
                                            <td>${merchant.mobile}</td>
                                            <td>${merchant.email}</td>
                                            <td>${merchant.debitCardPan}</td>
                                            <td><p data-placement='top' data-toggle='tooltip' title='Edit'><button class='btn btn-primary btn-xs editButton' data-title='Edit'><span class="fas fa-user-edit"></span></button></p></td>
                                            <td><p data-placement='top' data-toggle='tooltip' title='Invoices'><button class='btn btn-primary btn-xs invoicesButton' data-title='Invoices'><span class="fas fa-file-invoice"></span></button></p></td>
                                            <td><p data-placement='top' data-toggle='tooltip' title='Delete'><button class='btn btn-danger btn-xs deleteButton' data-title='Delete'><span class="fas fa-trash"></span></button></p></td>
                                        </tr>
                                    </c:forEach>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card-footer small text-muted">Total Merchant is : </div>
                    </div>
                </div>

                <!-- Sticky Footer -->
                <footer class="sticky-footer">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright © Your Website 2019</span>
                        </div>
                    </div>
                </footer>
            </div>
           <!-- /.content-wrapper -->
    </div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="/login">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- New Merchant Modal -->
<div id="newMerchantModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close fas fa-window-close" data-dismiss="modal"></button>
                <h4 class="modal-title"> Register New Merchant</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <form action="#" method="post" class="merchantRegistrationForm" role="form">
                                <div class="row">
                                    <div class="col-lg-6 col-lg-6">
                                        <input class="form-control" name="firstname" id="firstname" placeholder="First Name" value="sina" type="text" required autofocus />
                                    </div>
                                    <div class="col-lg-6 col-lg-6">
                                        <input class="form-control" name="lastname" id="lastname" placeholder="Last Name" value="tadayyon" type="text" required />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-9">
                                        <input class="form-control" name="debitcard" id="debitcard" placeholder="Debit Card PAN" value="1234567891234567" type="text" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="debitcardlable"></label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-9">
                                        <input class="form-control" name="youremail" id="youremail" placeholder="Your Email" value="ss@yahoo.com" type="email" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="maillable"></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <input class="form-control" name="phone" id="phone" value="12345678" placeholder="Phone" type="text"/>
                                    </div>
                                    <div class="col-lg-4">
                                        <input class="form-control" name="mobile" id="mobile" value="1234567890" placeholder="Mobile" type="text" required autofocus/>
                                    </div>
                                    <div class="col-lg-4">
                                        <input class="form-control" name="nationalid" id="nationalid" value="1234567891" placeholder="National ID" type="text"/>
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
                                <input class="form-control" name="address" id="address" placeholder="Address" value="narmak" type="text" />
                                <br />
                                <br />
                                <button class="btn btn-lg btn-primary" type="submit">Register</button>
                                <button class="btn btn-lg btn-default" type="button" data-dismiss="modal">Cancel</button>
                                <br />
                                <br />
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Modal body -->
            </div>

            <div class="modal-footer" id="newMerchant-modal-footer">
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

<!-- Edit User Modal -->
<div id="editMerchantModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">

        <!-- Modal content-->
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close fas fa-window-close" data-dismiss="modal"></button>
                <h4 class="modal-title">  Edit Merchant Profile</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <form action="#" method="put" class="e-merchantEditForm" role="form">
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
                                        <input class="form-control" name="e-merchantid" id="e-merchantid" placeholder="Merchant Id" type="text" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="e-merchantidlable"></label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-9">
                                        <input class="form-control" name="e-debitcard" id="e-debitcard" placeholder="Debit Card PAN" type="text" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="e-debitcardlable"></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-9">
                                        <input class="form-control" name="e-youremail" id="e-youremail" placeholder="Your Email" type="email" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="e-maillable"></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <input class="form-control" name="e-phone" id="e-phone" placeholder="Phone" type="text"/>
                                    </div>
                                    <div class="col-lg-4">
                                        <input class="form-control" name="e-mobile" id="e-mobile"  placeholder="Mobile" type="text" required autofocus/>
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
                                <br />
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
            <div class="modal-footer" id="editMerchant-modal-footer">
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

<!-- Delete Modal -->
<div class="modal fade" id="deleteMerchantModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close fas fa-window-close" data-dismiss="modal"></button>
                <h4 class="modal-title"> Delete Merchant Profile</h4>
            </div>
            <div class="modal-body">
                <labe id="deleteMerchantModalTitle" style="text-align: center"></labe>

                <br />
                <br />
                <button class="btn btn-primary" type="button" id="merchantDelete" >Delete</button>
                <button class="btn btn-default" type="button" data-dismiss="modal">Cancel</button>
                <br />
            </div>
            <div class="modal-footer" id="delete-modal-footer">
                <div class="row">
                    <div class="col-md-8">
                        <label id="d-errorlable" style="color: red; text-align: left"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Invoices Modal -->
<div class="modal fade" id="invoicesMerchantModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <h4 class="modal-title"><i class="close fas fa-window-close">  </i><label id="title-invoices">Merchant Invoices</label></h4>
            </div>

            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-hover table-striped" id="invoicesTable">
                        <thead>
                        <tr>
                            <th>Transaction Id</th>
                            <th>Username</th>
                            <th>User Mobile</th>
                            <th>User Fullname</th>
                            <th>Amount</th>
                            <th>Timestamp</th>
                        </tr>
                        <%--<c:forEach items="${merchant}" var="merchant">--%>
                            <%--<tr>--%>
                                <%--<td>${merchant.id}</td>--%>
                                <%--<td>${merchant.username}</td>--%>
                                <%--<td>${merchant.mobile}</td>--%>
                                <%--<td>${merchant.name} ${merchant.family}</td>--%>
                                <%--<td>${merchant.amount}</td>--%>
                                <%--<td>${merchant.registrationTimestamp}</td>--%>
                                <%--<td><p data-placement='top' data-toggle='tooltip' title='Edit'><button class='btn btn-primary btn-xs editButton' data-title='Edit'><span class='glyphicon glyphicon-pencil'></span></button></p></td>--%>
                                <%--<td><p data-placement='top' data-toggle='tooltip' title='Invoices'><button class='btn btn-primary btn-xs invoicesButton' data-title='Invoices'><span class='glyphicon glyphicon-list-alt'></span></button></p></td>--%>
                                <%--<td><p data-placement='top' data-toggle='tooltip' title='Delete'><button class='btn btn-danger btn-xs deleteButton' data-title='Delete'><span class='glyphicon glyphicon-trash'></span></button></p></td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
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
                        <button class="btn btn-lg btn-default" type="button" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<%--<!-- Bootstrap core JavaScript-->--%>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.js"></script>
<!-- Custom scripts for all pages-->
<script src="js/sb-admin.min.js"></script>

<script type="text/javascript">
        $(document).ready(function() {

            //press new merchant
            $('.merchantRegistrationForm').on('submit', function(event) {
                event.preventDefault();
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var header = "X-CSRF-TOKEN";
                if(!merchantRegistrationFormValidation($(this))) {
                    return;
                }
               console.log( $('form[name=usrForm]').serialize());
                var form_data = {

                    name:$("#firstname").val(),
                    family: $("#lastname").val(),
                    email:  $("#youremail").val(),
                    address: $("#address").val(),
                    phone: $("#phone").val(),
                    mobile:  $("#mobile").val(),
                    nationalId:  $("#nationalid").val(),
                    debitCardPan:  $("#debitcard").val()

                };
                $.ajax({
                    type: "POST",
                    url: "/merchants",
                    dataType: "json",
                    data : form_data,
                    xhrFields: {
                        withCredentials: false
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, csrfToken);
                    },
                    success: function (response) {
                        if(response.validated){
                            location.reload(true);
                        }
                        else {
                            $.each(response.errorMessages,function(key,value){
                                $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                            });
                        }
                    },
                    error: function (ex) {
                        console.log(ex);
                    }
                });
            });

            //press edit buttun
            $(".editButton").click(function(){
                var currentSelectedRow = $(this).closest("tr");
                var currentSelectedId = currentSelectedRow.find("td:eq(0)").html();
                console.log(currentSelectedId);
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var header = "X-CSRF-TOKEN";

                $.ajax({
                    type: 'GET',
                    url: "/merchants/" + currentSelectedId,
                    xhrFields: {
                        withCredentials: false
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, csrfToken);
                    },
                    success: function (response) {
                        console.log(response);

                        $("#e-merchantid").val(response.merchantId);
                        $("#e-firstname").val(response.name);
                        $("#e-lastname").val(response.family);
                        $("#e-youremail").val(response.email);
                        $("#e-address").val(response.address);
                        $("#e-phone").val(response.phone);
                        $("#e-mobile").val(response.mobile);
                        $("#e-nationalid").val(response.nationalId);
                        $("#e-debitcard").val(response.debitCardPan);
                        $("#e-merchantid").prop('disabled', true);
                        $("#editMerchantModal").modal("show");


                    },
                    error: function (ex) {
                        console.log(ex);
                    }
                });

            });

            //Update Merchant
            $('.e-merchantEditForm').on('submit', function(event) {
                event.preventDefault();
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var header = "X-CSRF-TOKEN";
                if(!merchantUpdateFormValidation($(this))) {
                    return;
                }
                var form_data = {
                    merchantId:$("#e-merchantid").val(),
                    name:$("#e-firstname").val(),
                    family: $("#e-lastname").val(),
                    email:  $("#e-youremail").val(),
                    address: $("#e-address").val(),
                    phone: $("#e-phone").val(),
                    mobile:  $("#e-mobile").val(),
                    nationalId:  $("#e-nationalid").val(),
                    debitCardPan:  $("#e-debitcard").val()

                };
                $.ajax({
                    type: "PUT",
                    url: "/merchants",
                    dataType: "json",
                    data : form_data,
                    xhrFields: {
                        withCredentials: false
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, csrfToken);
                    },
                    success: function (response) {
                        if(response.validated){
                            location.reload(true);
                        }
                        else {
                            $.each(response.errorMessages,function(key,value){
                                $('input[name=e-'+key+']').after('<span class="error">'+value+'</span>');
                            });
                        }
                    },
                    error: function (ex) {
                        console.log(ex);
                    }
                });
            });

            var SelectedMerchant ;
            ////press delete button
            $(".deleteButton").click(function(){
                var currentSelectedRow = $(this).closest("tr");
                SelectedMerchant = currentSelectedRow.find("td:eq(0)").html();
                var currentSelectedname = currentSelectedRow.find("td:eq(1)").html();
                var currentSelectedfamily = currentSelectedRow.find("td:eq(2)").html();
                $("#deleteMerchantModal").find("#deleteMerchantModalTitle").html("Are you sure delete " + currentSelectedname + " " +currentSelectedfamily);

                $("#deleteMerchantModal").modal("show");

            });

            $("#merchantDelete").click(function(){

                var csrfToken = $("meta[name='_csrf']").attr("content");
                var header = "X-CSRF-TOKEN";

                $.ajax({
                    type: 'DELETE',
                    url: "/merchants/" + SelectedMerchant,
                    xhrFields: {
                        withCredentials: false
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, csrfToken);
                    },
                    success: function () {
                        location.reload(true);
                    },
                    error: function (ex) {
                        console.log(ex);
                    }
                });
            });

            ////press invoices Button
            $(".invoicesButton").click(function () {
                var currentSelectedRow = $(this).closest("tr");
                var currentSelectedId = currentSelectedRow.find("td:eq(0)").html();
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var header = "X-CSRF-TOKEN";
                $('#invoicesTable  tbody > tr').empty();
                $.ajax({
                    type: 'GET',
                    url: "/Invoices/merchants/" + currentSelectedId,
                    xhrFields: {
                        withCredentials: false
                    },
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, csrfToken);
                    },
                    success: function (response) {
                        console.log(response);

                        $.each(response, function(i, item) {
                            var $tr = $('<tr>').append(
                                $('<td>').text(item.id),
                                $('<td>').text(item.username),
                                $('<td>').text(""),
                                $('<td>').text(""),
                                $('<td>').text(item.amount),
                                $('<td>').text(item.timestamp)
                            );
                            $('#invoicesTable').append($tr);

                        });
                        $("#invoicesMerchantModal").modal("show");
                    },
                    error: function (ex) {
                        console.log(ex);
                    }

                })

            });

            // New Merchant form Functions =============================================================
            function merchantRegistrationFormValidation(userForm) {
                var errorFlag = true;
                var debitcardInput = $("#debitcard").val();
                if(debitcardInput.length < 16 ) {
                    userForm.find("#debitcardlable").html("debitcard must be least 16 characters");
                    userForm.find("#debitcardlable").css("color", "red");
                    errorFlag = false;
                }
                var merchantMobile = $("#mobile").val();
                if(merchantMobile.length < 10 || merchantMobile.length > 13) {
                    userForm.find("#pmnvalidation").show();
                    userForm.find("#mobilevalidatemsg").html("Mobile Number is invalid");
                    userForm.find("#mobilevalidatemsg").css("color", "red");
                    errorFlag = false;
                }
                var merchantNationalid = $("#nationalid").val();
                if(merchantNationalid.length != 10 ) {
                    userForm.find("#pmnvalidation").show();
                    userForm.find("#nationalidvalidatemsg").html("national id length is invalid");
                    userForm.find("#nationalidvalidatemsg").css("color", "red");
                    errorFlag = false;
                }
                var merchantPhone = $("#phone").val();
                if(merchantPhone.length != 8 ) {
                    userForm.find("#pmnvalidation").show();
                    userForm.find("#phonevalidatemsg").html("phone length is invalid");
                    userForm.find("#phonevalidatemsg").css("color", "red");
                    errorFlag = false;
                }
                return   errorFlag;
            }

            // update Merchant form Functions =============================================================
            function merchantUpdateFormValidation(userForm) {
                var EerrorFlag = true;
                var Emerchantedebitcard = $("#e-debitcard").val();
                if(Emerchantedebitcard.length < 16 ) {
                    userForm.find("#e-debitcardlable").html("debitcard must be least 16 characters");
                    userForm.find("#e-debitcardlable").css("color", "red");
                    EerrorFlag = false;
                }
                var EmerchantMobile = $("#e-mobile").val();
                if(EmerchantMobile.length < 10 || EmerchantMobile.length > 13) {
                    userForm.find("#pmnvalidation").show();
                    userForm.find("#e-mobilevalidatemsg").html("Mobile Number is invalid");
                    userForm.find("#e-mobilevalidatemsg").css("color", "red");
                    EerrorFlag = false;
                }
                var EmerchantNationalid = $("#e-nationalid").val();
                if(EmerchantNationalid.length != 10 ) {
                    userForm.find("#pmnvalidation").show();
                    userForm.find("#e-nationalidvalidatemsg").html("national id length is invalid");
                    userForm.find("#e-nationalidvalidatemsg").css("color", "red");
                    EerrorFlag = false;
                }
                var EmerchantPhone = $("#e-phone").val();
                if(EmerchantPhone.length != 8 ) {
                    userForm.find("#pmnvalidation").show();
                    userForm.find("#e-phonevalidatemsg").html("phone length is invalid");
                    userForm.find("#e-phonevalidatemsg").css("color", "red");
                    EerrorFlag = false;
                }
                return   EerrorFlag;
            }


        });
    </script>

</body>
</html>
