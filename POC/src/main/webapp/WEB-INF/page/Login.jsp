<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--BOOTSTRAP CSS  -->
<link rel="stylesheet" type="text/css"
	href="plugin/bootstrap/bootstrap.min.css">

<!-- JQUERY_UI CSS -->
<link rel="stylesheet" type="text/css"
	href="plugin/jquery/jquery-ui.min.css">

<!-- FONT-AWESOME CSS -->
<link rel="stylesheet" type="text/css"
	href="plugin/fontawesome/css/font-awesome.min.css">

<!-- JQUERY_CONFIRM CSS -->
<link rel="stylesheet" type="text/css"
	href="plugin/jquery/jquery-confirm.min.css">


<!-- ALERTIFY CSS  -->

<link rel="stylesheet" type="text/css" href="customcss/common.css">

<link rel="stylesheet" type="text/css" href="customcss/login.css">
</head>
<header>
	<div class='header-box sticky'>
		<div class='row'>
			<div class='col-md-2 col-sm-3 col-6 text-center'>
				<img alt="" height="55px" width="150px"
					style="background-color: #dcdcdc; "
					src="logo/PointelLogo.png">
			</div>
			<div class='offset-md-6 col-md-4 col-sm-3 col-6 text-center'>
				<div class="app">
					<label for="fileName" style="font-weight: bold"> Audio Management Solution </label>
				</div>
			</div>
		</div>
	</div>
</header>
<body>
	<div class="row">
		<div class='offset-md-3 col-md-6 col-sm-12 col-12'>
			<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
				<div class='login-box'>
					<form name='login' id='login' autocomplete="off">
						<div class=row>
							<div class='col-md-12 col-sm-12 col-12'>
								<div class="form-group">
									<label for="userName"> <i class='fa fa-user'></i> User
										Name
									</label> <input type="text" class="form-control form-control-sm"
										id="identityName" placeholder="User Name " name='identityName'>
								</div>
							</div>
						</div>
						<div class=row>
							<div class='col-md-12 col-sm-12 col-12'>
								<div class="form-group">
									<label for="password"><i class="fa fa-key"
										aria-hidden="true"></i> Password</label> <input type="password"
										class="form-control form-control-sm" id="watchWord"
										placeholder="Password" name='watchWord'>
								</div>
							</div>
						</div>
						<div class=row>
							<div class=' col-md-6 col-sm-12 col-12'>
								<button class='btn btn-submit btn-sm btn-block' type="submit">Submit</button>
							</div>
							<div class='col-md-6 col-sm-12 col-12'>
								<button class='btn btn-reset btn-sm btn-block' type="reset">Reset</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer class="text-center">
		<label>Copyright &copy; 2019 <a href="http://pointel.com"
			target="_blank">Pointel Inc</a>.
		</label> All rights reserved.
	</footer>
	<%@ include file="../layout/foot.jsp"%>
	<script src='/customjs/login.js'></script>
</body>
</html>
