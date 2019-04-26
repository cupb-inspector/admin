<%@page import="hxy.inspec.admin.po.Account"%>
<%@page import="java.util.List"%>
<%@page import="hxy.inspec.admin.services.AccountService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>充值管理</title>
    <meta name="description" content="Ela Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    

    <link rel="stylesheet" href="assets/css/normalize.css">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/themify-icons.css">
<link rel="stylesheet" href="assets/css/pe-icon-7-filled.css">
    <link rel="stylesheet" href="assets/css/flag-icon.min.css"><link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="assets/css/lib/datatable/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<style>
    html,body{
            margin: 0px;
            width: 100%;
            height: 100%;
        }
</style>
</head>
<body>
    
        <div class="content" style="background:#f1f2f7;height: 100%">
            <div class="animated fadeIn">
                    <div class="row">
                            <div class="col-lg-6 col-md-6">
                                
                                    <div class="card">
            
                                        <div class="card-body">
                                            <div class="stat-widget-five">
                                                <div class="stat-icon dib flat-color-1">
                                                    <i class="pe-7s-cash"></i>
                                                </div>
                                                <div class="stat-content">
                                                    <div class="text-left dib">
                                                        <div class="stat-text"><span class="count">569</span></div>
                                                        <div class="stat-heading">日充值金额</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
            
                                    </div>
                               
                            </div>
            
                            <div class="col-lg-6 col-md-6">
                                
                                    <div class="card">
            
                                        <div class="card-body">
                                            <div class="stat-widget-five">
                                                <div class="stat-icon dib flat-color-2">
                                                    <i class="pe-7s-cash"></i>
                                                </div>
                                                <div class="stat-content">
                                                    <div class="text-left dib">
                                                        <div class="stat-text"><span class="count">35</span></div>
                                                        <div class="stat-heading">总充值金额</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
            
                                    </div>
            
                            </div>
            
                            
                        </div>
                        <!-- /Widgets -->
                <div class="row">

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <strong class="card-title">充值统计</strong>
                            </div>
                            <div class="card-body">
                                <table id="bootstrap-data-table" class="table table-striped table-bordered">
                                        <thead>
                                                <tr>
                                                    <th>充值时间</th>
                                                    <th>用户ID</th>
                                                    <th>请求类型</th>
                                                    <th>金额</th>
                                                     <th>余额</th>
                                                    <th>操作</th>
            
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                            AccountService accountService = new AccountService();
                                            //查询所有未处理的订单
                                           List<Account> ls= accountService.selectAllByStatus("0");
                                            	if(ls!=null&&ls.size()!=0){
                                            		for(int i=0;i<ls.size();i++){
                                            			Account a = ls.get(i);
                                            			%>
                                                <tr>
                                                    <td><%=a.getTime() %></td>
                                                    <td><%=a.getUserTel() %></td>
                                                    <td><%=a.getTypeString() %></td>
                                                    <td><%=a.getValue() %></td>
                                                    <td><%=a.getSurplus() %></td>
                                                    
                                                    <td>
                                                        <a href="payment-details?id=<%=a.getId() %>" target="myiframe" style="color:blue">详情</a>                                                    </td>
                                                </tr>
                                            			
                                            			<% 
                                            		}
                                            	}
                                            
                                            %>
                                            
                                            </tbody>
                                </table>
                            </div>
                        </div>
                    </div>


                </div>
            </div><!-- .animated -->
        </div><!-- .content -->


        <div class="clearfix"></div>


    <!-- Right Panel -->

    <!-- Scripts -->
    <script src="assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.matchHeight.min.js"></script>
    <script src="assets/js/main.js"></script>


    <script src="assets/js/lib/data-table/datatables.min.js"></script>
    <script src="assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>
    <script src="assets/js/lib/data-table/dataTables.buttons.min.js"></script>
    <script src="assets/js/lib/data-table/buttons.bootstrap.min.js"></script>
    <script src="assets/js/lib/data-table/jszip.min.js"></script>
    <script src="assets/js/lib/data-table/vfs_fonts.js"></script>
    <script src="assets/js/lib/data-table/buttons.html5.min.js"></script>
    <script src="assets/js/lib/data-table/buttons.print.min.js"></script>
    <script src="assets/js/lib/data-table/buttons.colVis.min.js"></script>
    <script src="assets/js/init/datatables-init.js"></script>


    <script type="text/javascript">
        $(document).ready(function() {
          $('#bootstrap-data-table').DataTable();
      } );
  </script>


</body>
</html>
