<body>
	<header>
		<nav class='header-box sticky'>
			<div class='row'>
				<div class='col-md-2 col-sm-3 col-6 text-center'>
					<a class="" href="#"> <img alt="" height="55px" width="150px"
						style="background-color: #f9f3f3; color: #f9f3f3;"
						src="logo/PointelLogo.png">
					</a>
				</div>

				<div class='offset-md-7 col-md-1 col-sm-2 col-2 line-adjust text-right'>
					<div class="app">
						<label> AMS</label>
					</div>
				</div>
				<div class=' col-md-1 col-sm-2 col-2 text-right line-adjust'>
					<div class="dropdown">
						<label class=" dropdown-toggle" id="dropdownMenuButton"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class='fa fa-cog fa-lg'></i>
						</label>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" data-toggle="modal"
								data-target="#changePassword">Change Password</a> <a
								class="dropdown-item" data-toggle="modal" data-target="#about">About</a>
						</div>
					</div>
				</div>
				<div class='col-md-1 col-sm-2 col-2 line-adjust'>
					<a href="Logout"><i class="fa fa-sign-out fa-lg"
						aria-hidden="true"></i></a>
				</div>


			</div>
		</nav>
	</header>
	<aside class='menu-box'>
		<ul class="list-group">

			<%
				if (session.getAttribute("POCUSERROLE").toString().equalsIgnoreCase("Admin")) {
			%>
			<li class=""><a href="dbConfig">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-database"></i>DatabaseConfig
			</a></li>
			<li class=""><a href="pocMenu">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-cogs"> </i> PromptConfig
			</a></li>
			<li class=""><a href="Add">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-plus-circle"> </i> Create Prompt
			</a></li>
			<li class=""><a href="Trace">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-search"></i> Trace
			</a></li>

			<li class=""><a href="UserManagement">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-users"></i> UserManagement
			</a></li>
			<li class=""><a href="ProjectConfig">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-tasks" aria-hidden="true"></i> ProjectConfig
			</a></li>
			<%
				} else {
			%>
			<li class=""><a href="Add">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-plus-circle"> </i> Add
			</a></li>
			<li class=""><a href="Trace">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-eye"></i> Trace
			</a></li>
			<%
				}
			%>
			<!-- <li class=""><a href="dbConfig">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-database"></i>DBConfig
			</a></li>
		<li class=""><a href="pocMenu">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-plus-circle"> </i> Menu
			</a></li>
		
			<li class=""><a href="#" data-toggle="collapse"
				data-target="#hideMenu">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-chevron-circle-right"></i>Config
			</a></li>

			 <ul class="collapse in list-group" id="hideMenu">
				<li><a href="dbConfig">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
						class="fa fa-database"></i>DBConfig
				</a></li>
				<li><a href="pathConfig">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
						class="fa fa-database"></i>PathConfig
				</a></li>
			</ul>

			<li class=""><a href="Add">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-plus-circle"> </i> Add
			</a></li>
			<li class=""><a href="Trace">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-eye"></i> Trace
			</a></li>

			<li class=""><a href="UserManagement">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-users"></i> UserManagement
			</a></li>
			<li class=""><a href="ProjectConfig">&nbsp;&nbsp;&nbsp;&nbsp;<i
					class="fa fa-tasks" aria-hidden="true"></i> ProjectConfig
			</a></li> -->
		</ul>
	</aside>

	<div class="modal" id='changePassword' tabindex="-1" role="dialog">
		<div class="modal-dialog "
			style="max-width: 40%; background-color: #e0e0e030" role="document">
			<div class="modal-content">
				<form name='updateFilterForm' id='updateFilterForm'>
					<div class="modal-body" style="background-color: #e0e0e030">
						<div class="model-header">
							<div class="row" style="padding: 10px;">
								<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
									<label class="modal-title"><i class='fa fa-key'
										style="color: cyan"></i> Change Password</label>
								</div>
								<div class='col-md-5 col-sm-12 col-12 text-right'>
									<button class='btn btn-model btn-sm' type="submit"
										data-toggle="tooltip" title="save">
										<i class='fa fa-save' style="color: cyan"> </i>
									</button>
									<button class='btn btn-model btn-sm' data-dismiss="modal"
										data-toggle="tooltip" title="close">
										<i class='fa fa-window-close' style="color: red"> </i>
									</button>
								</div>
							</div>
						</div>
						<div class='model-box'>
							<div class="row" style="margin-top: 20px;">
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="currentPassword">Current Password</label> <input
											type="text" class="form-control form-control-sm"
											id="currentPassword" placeholder="currentPassword"
											name="currentPassword">
									</div>
								</div>
							</div>

							<div class="row">
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="newPassword">New Password</label> <input
											type="text" class="form-control form-control-sm"
											id="newPassword" placeholder="New Password"
											name="newPassword">
									</div>
								</div>
							</div>
							<div class="row">
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="currentPassword">Confirm Password</label> <input
											type="text" class="form-control form-control-sm"
											id="confirmPassword" placeholder="Confirm Password"
											name="confirmPassword">
									</div>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal" id='about' tabindex="-1" role="dialog">
		<div class="modal-dialog "
			style="max-width: 40%; background-color: #e0e0e030" role="document">
			<div class="modal-content">
				<div class="modal-body" style="background-color: #e0e0e030">
					<div class="model-header">
						<div class="row" style="padding: 10px;">
							<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
								<label class="modal-title"><i class='fa fa-info-circle'></i>About</label>
							</div>
							<div class='col-md-5 col-sm-12 col-12 text-right'>
								<!-- <button class='btn btn-model btn-sm' type="submit"
										data-toggle="tooltip" title="save">
										<i class='fa fa-save' style="color: cyan"> </i>
									</button> -->
								<button class='btn btn-model btn-sm' data-dismiss="modal"
									data-toggle="tooltip" title="close">
									<i class='fa fa-window-close' style="color: red"> </i>
								</button>
							</div>
						</div>
					</div>
					<div class='model-box'>
						<div class="row" style="margin: 20px;">

							<div class="col-md-6 col-sm-12 col-12 text-center">
								<label>AMS</label><br>Build: b20181228<br> <a
									target="_blank" style="text-decoration: none"
									href="https://www.pointel.com">www.pointel.com</a>
							</div>
							<div class="col-md-6 col-sm-12 col-12 text-center">
								<div>
									<img width="150" height="100" src="logo/PointelLogo.png">
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>