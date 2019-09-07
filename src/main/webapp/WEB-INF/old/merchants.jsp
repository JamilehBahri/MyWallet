<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Merchant Panel</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/sb-admin.css" rel="stylesheet">
    <link href="/resources/css/plugins/morris.css" rel="stylesheet">
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        span.error{
            color: red;
            margin-left: 5px;
        }
    </style>
</head>

<body>
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

            <div id="content-wrapper">
                <!--   Content Page     -->
                <div class="container-fluid">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
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
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-hover table-striped" id="merchantTable">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Family</th>
                                            <th>Mobile</th>
                                            <th>E-Mail</th>
                                            <th>Debit Card PAN</th>
                                            <th>Edit</th>
                                            <th>Invoices</th>
                                            <th>Delete</th>
                                        </tr>
                                        <c:forEach items="${merchant}" var="merchant">
                                            <tr>
                                                <td>${merchant.merchantId}</td>
                                                <td>${merchant.name}</td>
                                                <td>${merchant.family}</td>
                                                <td>${merchant.mobile}</td>
                                                <td>${merchant.email}</td>
                                                <td>${merchant.debitCardPan}</td>
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
           <!-- /.content-wrapper -->



        <!-- New Merchant Modal -->
        <div id="newMerchantModal" class="modal fade" role="dialog">
          <div class="modal-dialog modal-lg">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><i class="glyphicon glyphicon-globe"></i>  Register New Merchant</h4>
              </div>
              <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <form action="#" method="post" class="merchantRegistrationForm" role="form">
                                <div class="row">
                                    <div class="col-lg-6 col-lg-6">
                                        <input class="form-control" name="firstname" placeholder="First Name" value="sian" type="text" required autofocus />
                                    </div>
                                    <div class="col-lg-6 col-lg-6">
                                        <input class="form-control" name="lastname" placeholder="Last Name" value="tadayyon" type="text" required />
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-9">
                                        <input class="form-control" name="debitcard" placeholder="Debit Card PAN" value="1234567891234567" type="text" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="debitcardlable"></label>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-9">
                                        <input class="form-control" name="youremail" placeholder="Your Email" type="email" value="ss@yahoo.com" required autofocus/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label id="maillable"></label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <input class="form-control" name="phone" placeholder="Phone" value="12345678" type="text"/>
                                    </div>
                                    <div class="col-lg-4">
                                        <input class="form-control" name="mobile" placeholder="Mobile" value="12345678903" type="text"  required autofocus/>
                                    </div>
                                    <div class="col-lg-4">
                                        <input class="form-control" name="nationalid" placeholder="National ID" value="1234567891" type="text"/>
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
                                <input class="form-control" name="address" placeholder="Address" value="narmak" type="text" />
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
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"><i class="glyphicon glyphicon-globe"></i>  Edit Merchant Profile</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form action="#" method="put" class="e-userEditForm" role="form">
                                        <div class="row">
                                            <div class="col-lg-6 col-lg-6">
                                                <input class="form-control" name="e-firstname" placeholder="First Name" type="text" required autofocus />
                                            </div>
                                            <div class="col-lg-6 col-lg-6">
                                                <input class="form-control" name="e-lastname" placeholder="Last Name" type="text" required />
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-9">
                                                <input class="form-control" name="e-merchantid" placeholder="Merchant Id" type="text" required autofocus/>
                                            </div>
                                            <div class="col-lg-3">
                                                <label id="e-merchantidlable"></label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-9">
                                                <input class="form-control" name="e-debitcard" placeholder="Debit Card PAN" type="text" required autofocus/>
                                            </div>
                                            <div class="col-lg-3">
                                                <label id="e-debitcardlable"></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-9">
                                                <input class="form-control" name="e-youremail" placeholder="Your Email" type="email" required autofocus/>
                                            </div>
                                            <div class="col-lg-3">
                                                <label id="e-maillable"></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <input class="form-control" name="e-phone" placeholder="Phone" type="text"/>
                                            </div>
                                            <div class="col-lg-4">
                                                <input class="form-control" name="e-mobile" placeholder="Mobile" type="text" required autofocus/>
                                            </div>
                                            <div class="col-lg-4">
                                                <input class="form-control" name="e-nationalid" placeholder="National ID" type="text"/>
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
                                        <input class="form-control" name="e-address" placeholder="Address" type="text" />
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
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="glyphicon glyphicon-globe"></i>  Delete Merchant Profile</h4>
                </div>
                <div class="modal-body">
                    <labe id="deleteMerchantModalTitle" style="text-align: center"></labe>
                    <br />
                    <br />
                    <button class="btn btn-primary" type="button" id="deleteMerchantModelButton">Delete</button>
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
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"><i class="glyphicon glyphicon-list-alt">  </i><label id="title-invoices">Merchant Invoices</label></h4>
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
                                <c:forEach items="${merchant}" var="merchant">
                                    <tr>
                                        <td>${merchant.id}</td>
                                        <td>${merchant.username}</td>
                                        <td>${merchant.mobile}</td>
                                        <td>${merchant.name} ${merchant.family}</td>
                                        <td>${merchant.amount}</td>
                                        <td>${merchant.registrationTimestamp}</td>
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

    <!-- wrapper -->
    </div>

     <div id="footer">
        <div class="container">
            <p class="footer-block">Powered By Jamileh Bahri - Copyright 2019</p>
        </div>
    </div>


    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- AJAX add user (POST) -->
    <script type="text/javascript">
        $(document).ready(function() {

        });
    </script>
</body>

</html>
