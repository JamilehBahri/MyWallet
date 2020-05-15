<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <title>Admin Panel</title>

  <%--<link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">--%>

  <%--<!-- Custom fonts for this template-->--%>
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <%--<!-- Page level plugin CSS-->--%>
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <%--<!-- Custom styles for this template-->--%>
  <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

  <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="/index">Start Bootstrap</a>

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
          <i class="fas fa-wallet"></i>
          <span>Payment</span></a>
      </li>
    </ul>

    <div id="content-wrapper">

      <div class="container-fluid">
        <!-- DataTables Example Latest Transaction Panel -->
        <div class="card mb-3">
          <div class="card-header">
            <div class="row">
              <div class="col-lg-8">
                <h3><i class="fas fa-wallet"></i> Pay From Wallet</h3>
              </div>

            </div>
          </div>
          <div class="card-body">
            <form action="#" method="post" class="paymentForm" name="payForm" role="form">

              <div class="row">
                <div class="col-lg-9">
                  <input class="form-control" name="merchantcode" placeholder="merchantCode" id="merchantcode" type="text" autofocus />
                </div>
                <div class="col-lg-3">
                  <label id="merchantcodelable"></label>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-9">
                  <input class="form-control" name="username" placeholder="username" id="username" type="text" autofocus />
                </div>
                <div class="col-lg-3">
                  <label id="usernamelable"></label>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-9">
                  <input class="form-control" name="amount" placeholder="Amount" id="amount" type="text" autofocus />
                </div>
                <div class="col-lg-3">
                  <label id="amountlable"></label>
                </div>
              </div>
              <%--data-toggle="modal" data-target="#ConfirmPayModal"--%>
              <button class="btn btn-lg btn-primary buttenHeader" type="button" id="pay"  > Pay </button>
              <input class="btn btn-lg btn-primary btn-block" type="reset" value="Reset">

            </form>
            <%--<c:url var="payActionUrl" value="/payment" />--%>
               <%--<form:form method="post" class="paymentForm" modelAttribute="paymentInfo" action="${payActionUrl}">--%>
                 <%--<label> merchantCode : </label>--%>
                 <%--<form:input path="merchantcode" type="text" class="form-control" placeholder="merchantCode" />--%>
                 <%--<form:errors path="merchantcode" />--%>
                 <%--<br>--%>
                 <%--<label> Amount : </label>--%>
                 <%--<form:input path="amount" type="text"  class="form-control" placeholder=" Amount (Rial)"/>--%>
                 <%--<form:errors path="amount" />--%>
                 <%--<br>--%>
                 <%--<label> username : </label>--%>
                 <%--<form:input path="username" type="text" class="form-control" placeholder="username" />--%>
                 <%--<form:errors path="username" />--%>
                 <%--<br>--%>
                 <%--<button class="btn btn-lg btn-primary buttenHeader" type="button" data-toggle="modal" data-target="#ConfirmPayModal" > Pay </button>--%>
                 <%--<input class="btn btn-lg btn-primary btn-block" type="reset" value="Reset">--%>

               <%--</form:form>--%>
             </div>
          <%--<div class="card-footer small text-muted">Total transaction number is : </div>--%>
       </div>

      </div>
      <!-- /.container-fluid -->

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
  <!-- /#wrapper -->

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

  <%--<!-- ConfirmPay  Modal -->--%>
  <div id="ConfirmPayModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close fas fa-window-close" data-dismiss="modal"></button>
          <h4 class="modal-title"> Confirm Pay</h4>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col-lg-12">
                  <form action="#" method="put" modelAttribute="paymentInfo" name="payModal" class="m-confirmpay" role="form">
                    <label> Are you sure pay </label>
                    <label id="m_amount" name="m_amount"> </label>
                    <label> Rials,to the </label>
                    <label id="m_merchantname"></label>
                    <label id="m_merchantfamily"></label>(code :
                    <label id="m_merchantcode"></label>
                    <label> )account , from userame : </label>
                    <label id="m_username"></label>
                  <button class="btn btn-lg btn-primary btn-block" type="submit" name="action" value="continue"> Continue</button>
                  <br>
                  <a href="/payment"><input class="btn btn-lg btn-primary btn-block" type="button" value="Cancel" name="cancel" onclick="this.parentNode.href=/payment;" /></a>
                  </form>
                </div>
            </div>
          </div>
          <!-- Modal body -->
        </div>
        <div class="modal-footer" id="confirmpay-modal-footer">
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

  <%--<!-- successPay  Modal -->--%>
  <div id="SuccessPayModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close fas fa-window-close" data-dismiss="modal"></button>
          <h4 class="modal-title"> Success Pay</h4>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col-lg-12">
                <form action="#" method="GET" class="m-successpay" role="form">
                  <label> pay is success </label>
                    <a href="/"><input class="btn btn-lg btn-primary btn-block" type="button" value="close" name="close" onclick="this.parentNode.href=/;" /></a>
                </form>
              </div>
            </div>
          </div>
          <!-- Modal body -->
        </div>
        <div class="modal-footer" id="confirmpay-modal-footer">
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


  <%--<!-- Bootstrap core JavaScript-->--%>
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin.min.js"></script>

  <script type="text/javascript">
      $(document).ready(function() {
          //press pay
          $("#pay").click(function(){
              event.preventDefault();
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var header = "X-CSRF-TOKEN";
              var form_data = {
                  merchantcode:$("#merchantcode").val(),
                  username: $("#username").val(),
                  amount :  $("#amount").val()
              };
              console.log(form_data);

              $.ajax({
                  type: "POST",
                  url: "/payment",
                  dataType: "json",
                  data : form_data,
                  xhrFields: {
                      withCredentials: false
                  },
                  beforeSend: function(xhr) {
                      xhr.setRequestHeader(header, csrfToken);
                  },
                  success: function (response)
                  {
                      if(response.validated){
                          $("#m_merchantname").text(response.paymentDto.merchantname + response.paymentDto.merchantfamily);
                          $("#m_merchantcode").text(response.paymentDto.merchantcode);
                          $("#m_username").text(response.paymentDto.username);
                          $("#m_amount").text(response.paymentDto.amount);

                          $('#ConfirmPayModal').modal('show');

                      }
                      // else {
                      //     $.each(response.errorMessages,function(key,value){
                      //         $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                      //     });
                      // }
                  },
                  error: function (ex) {
                      console.log(ex);
                  }
              });
          });

          //press confirm
          $('.m-confirmpay').on('submit', function(event) {
              event.preventDefault();
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var header = "X-CSRF-TOKEN";
              var form_data = {
                  merchantcode:$("#merchantcode").val(),
                  username: $("#username").val(),
                  amount :  $("#amount").val()

              };
              $.ajax({
                  type: "PUT",
                  url: "/payment",
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
                          $('#SuccessPayModal').modal('show');
                      }
                      // else {
                      //     $.each(response.errorMessages,function(key,value){
                      //         $('input[name=e-'+key+']').after('<span class="error">'+value+'</span>');
                      //     });
                      // }
                  },
                  error: function (ex) {
                      console.log(ex);
                  }
              });
          });


      });
  </script>



</body>

</html>
