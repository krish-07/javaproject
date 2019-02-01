<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<section>
	<div class="row">
		<div class='col-md-12 col-sm-12 col-12'>
			<div class=row style="margin-top: 90px;">
				<div
					class='offset-md-2 col-md-4 offset-sm-4 col-sm-3 offset-4 col-6'>
					<ul class="nav nav-tabs tabcolor" id="tab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="usertab"
							data-toggle="pill" href="#userMgmt" role="tab"
							aria-controls="pills-home" aria-selected="true"> <i
								class="fa fa-users" aria-hidden="true"></i> User Management

						</a></li>
						<li class="nav-item"><a class="nav-link " id="projecttab"
							data-toggle="pill" href="#projectMapping" role="tab"
							aria-controls="pills-home" aria-selected="true"><i
								class="fa fa-map"></i> Project Mapping </a></li>
					</ul>
				</div>
				<div class='offset-md-5 col-md-1 col-sm-4 col-2 '>
					<i class="fa fa-user-plus fa-lg" style="color: teal" data-toggle="modal"
						data-target="#addUser"></i>
				</div>
			</div>
		</div>
	</div>

	<div class="row" id="userMgmt">
		<div class='col-md-12 col-sm-12 col-12'>
			<div class="report-box">
				<div class=row>
					<div class='col-md-12 col-sm-12 col-12'>
						<table
							class="table w-100  table-responsive-sm table-responsive-md	 table-responsive-lg table-bordered table-hover"
							id='userManagement'>
							<thead>
								<tr>
									<th>S.No</th>
									<th>UserName</th>
									<th>Active</th>
									<th>Role</th>
									<th>Comment</th>
									<th>Action</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row hide" id="projectMapping">
		<div class='col-md-12 col-sm-12 col-12'>
			<div class="report-box ">
				<div class=row>
					<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
						<div class="form-group">
							<div class=row>
								<div class='col-md-6 col-sm-6 col-6'>
									<label for="active">Select User<span
										class="asteriskField">&nbsp;*</span></label>
								</div>
								<div class='col-md-6 col-sm-6 col-6'>
									<div class="text-right">
										<button class="btn btn-view btn-sm" id="view">
											<i class="fa fa-eye "></i>
										</button>

										<button class="btn btn-add btn-sm " id="add">
											<i class="fa fa-plus "> </i>
										</button>

										<button class="btn btn-delete btn-sm" id="delete">
											<i class="fa fa-trash "> </i>
										</button>
									</div>
								</div>
							</div>
							<div class="form-group" style="margin-top: 10px;">
								<select class="form-control" id="user" name='user'></select>
							</div>
						</div>
					</div>
				</div>

				<div class=row>
					<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
						<div class="form-group">
							<label for="active">Allocate Project<span
								class="asteriskField">&nbsp;*</span></label> <select disabled
								id="allocateProject" name='allocateProject'>
							</select>
						</div>
					</div>
				</div>
				<div class=row>
					<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
						<div class="form-group">
							<label for="active">Service Name<span
								class="asteriskField">&nbsp;*</span></label> <select disabled
								style="max-width: 100%; border: 1px solid #ced4da"
								id="serviceName" name='serviceName'>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="offset-md-4 col-md-3 col-sm-12 col-12">
						<button type="button" class="btn btn-submit btn-sm btn-block"
							id="submit" disabled>Submit</button>
					</div>
					<div class="col-md-3 col-md-3 col-sm-12 col-12">
						<button type="reset" class="btn btn-submit btn-sm btn-block"
							id="reset">Reset</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<div class="modal" id='addUser' role="dialog">
	<div class="modal-dialog"
		style="max-width: 50%; background-color: #e0e0e030">
		<div class="modal-content">
			<form name='AddUserForm' id='AddUserForm'>
				<div class="modal-body">
					<div class="model-header">
						<div class='row' style="margin: 5px;">
							<div class='col-md-9 col-sm-10 col-10'>
								<span style="color: white;"><i class="fa fa-user-plus"></i>
									Add New User </span>
							</div>
							<div class='col-md-1 col-sm-1 col-1 text-right'>
								<button class="btn btn-model btn-sm" id="modalSubmit">
									<i class="fa fa-save" style="color: cyan;"></i>
								</button>
							</div>
							<div class='col-md-1 col-sm-1 col-1 text-right'>
								<button data-dismiss="modal" class="btn btn-model btn-sm">
									<i class="fa fa-window-close" style="color: red;"></i>
								</button>
							</div>
						</div>
					</div>
					<div class="model-box">
						<div class=row style="margin-top: 20px">
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioInput">User Name <span
										class="asteriskField">&nbsp;*</span></label> <input type="text"
										class="form-control form-control-sm" id="userName"
										name='userName'>
								</div>
							</div>
						</div>
						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="userPassword">Password<span
										class="asteriskField">&nbsp;*</span></label> <input
										class="form-control" id="password" name="password"
										placeholder="Password" type="password" value="" /> <span
										class="eye-icon"><i id="visiblePass" class="fa fa-eye"></i></span>
								</div>
							</div>
						</div>
						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="userPassword">Confirm Password<span
										class="asteriskField">&nbsp;*</span></label> <input
										class="form-control" id="confPassword" name="confPassword"
										placeholder="Password" type="password" value="" /> <span
										class="eye-icon"><i id="confVisiblePass"
										class="fa fa-eye"></i></span>
								</div>
							</div>
						</div>
						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioType">Role<span class="asteriskField">&nbsp;*</span></label>
									<select class="form-control form-control-sm" id="role"
										name='role'>
										<option value='Admin'>Admin</option>
										<option value='User'>User</option>
									</select>
								</div>
							</div>
						</div>
						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="active">Active<span class="asteriskField">&nbsp;*</span></label>
									<select class="form-control form-control-sm" id="active"
										name='active'>
										<option value='1'>Active</option>
										<option value='0'>In-Active</option>
									</select>

								</div>
							</div>
						</div>
						<div class=row style="margin-bottom: 20px">
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioInput">Comment</label>
									<textarea rows="1" maxlength="250" style="resize: none;"
										class="form-control form-control-sm" id='comment'
										name='comment'></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>
<div class="modal" id='editUser' role="dialog">
	<div class="modal-dialog"
		style="max-width: 40%; background-color: #e0e0e030">
		<div class="modal-content">
			<form name='EditUserForm' id='EditUserForm'>
				<div class="modal-body">
					<div class="model-header">
						<div class='row' style="margin: 5px;">
							<div class='col-md-9 col-sm-10 col-10'>
								<span style="color: white;"><i class="fa fa-user-plus"></i>
									Edit User Info </span>
							</div>
							<div class='col-md-1 col-sm-1 col-1 text-right'>
								<button class="btn btn-model btn-sm">
									<i class="fa fa-save" style="color: cyan;"></i>
								</button>
							</div>
							<div class='col-md-1 col-sm-1 col-1 text-right'>
								<button data-dismiss="modal" class="btn btn-model btn-sm">
									<i class="fa fa-window-close" style="color: red;"></i>
								</button>
							</div>
						</div>
					</div>
					<div class="model-box">
						<div class=row style="margin-top: 20px">
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioInput">User Name <span
										class="asteriskField">&nbsp;*</span></label> <input type="text"
										class="form-control form-control-sm" id="editUserName"
										name='userName'> <input type="hidden" name="userId"
										id="userId">

								</div>
							</div>
						</div>
						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="userPassword">Password<span
										class="asteriskField">&nbsp;*</span></label> <input
										class="form-control" id="editPassword" name="password"
										placeholder="Password" type="password" value="" /> <span
										class="eye-icon"><i id="visiblePass" class="fa fa-eye"></i></span>
								</div>
							</div>
						</div>
						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioType">Role<span class="asteriskField">&nbsp;*</span></label>
									<select class="form-control form-control-sm" id="editRole"
										name='role'>
										<option value='Admin'>Admin</option>
										<option value='User'>User</option>
									</select>
								</div>
							</div>
						</div>

						<div class=row>
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="active">Active<span class="asteriskField">&nbsp;*</span></label>
									<select class="form-control form-control-sm" id="editActive"
										name='active'>
										<option value='1'>Active</option>
										<option value='0'>In-Active</option>
									</select>

								</div>

							</div>
						</div>
						<div class=row style="margin-bottom: 20px">
							<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioInput">Comment</label>
									<textarea rows="1" maxlength="250" style="resize: none;"
										class="form-control form-control-sm" id='editComment'
										name='comment'></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>
<%@ include file="../layout/footer.jsp"%>
<%@ include file="../layout/foot.jsp"%>
<script src="customjs/userManagement.js"></script>

<script type="text/javascript">
	$("#allocateProject").select2({
		multiple : true,
		width : '100%',
		placeholder : "select project"
	});

	$("#serviceName").select2({
		multiple : true,
		width : '100%',
		allowClear : true,
		placeholder : "select service"
	});
</script>