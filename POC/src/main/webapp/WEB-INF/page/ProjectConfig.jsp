<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<section>
	<div class="row">
		<div class='col-md-12 col-sm-12 col-12'>
			<div class=row style="margin-top: 90px;">
				<div
					class='offset-md-2 col-md-4 offset-sm-4 col-sm-3 offset-4 col-6'>
					<ul class="nav nav-tabs tabcolor" id="tab" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							id="projecttab" data-toggle="pill" href="#projectManagement"
							role="tab" aria-controls="pills-home" aria-selected="true"> <i
								class="fa  fa-tasks" aria-hidden="true"></i> Project Management

						</a></li>
						<li class="nav-item"><a class="nav-link " id="foldermaptab"
							data-toggle="pill" href="#folderMapping" role="tab"
							aria-controls="pills-home" aria-selected="true"><i
								class="fa fa-map"></i> Project Folder Mapping </a></li>
					</ul>
				</div>
				<div class='offset-md-5 col-md-1 col-sm-4 col-2 '>
					<i class="fa fa-plus fa-2x" data-toggle="modal"
						data-target="#AddProject" style="color: teal; font-size: 25px"
						title="Import Files" id='uploadFiles'></i>
				</div>
			</div>
			<div class="row" id="projectManagement">
				<div class='col-md-12 col-sm-12 col-12'>
					<div class="report-box">
						<div class=row>
							<div class='col-md-12 col-sm-12 col-12'>
								<table
									class="table w-100  table-responsive-sm table-responsive-md	 table-responsive-lg table-bordered table-hover"
									id='projectMgmt'>
									<thead>
										<tr>
											<th>S.No</th>
											<th>Project Name</th>
											<th>Host Name</th>
											<th>Project Location</th>
											<th>Action</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row hide" id="folderMapping">
				<div class='col-md-12 col-sm-12 col-12'>
					<div class="report-box ">
						<div class=row>
							<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
								<div class=row>
									<div class='col-md-6 col-sm-6 col-6'>
										<label for="active">Select Project<span
											class="asteriskField">&nbsp;*</span></label>
									</div>
									<div class='col-md-6 col-sm-6 col-6'>
										<div class="text-right">
											<button class="btn btn-view btn-sm" id="view">
												<i class="fa fa-eye "></i>
											</button>
											<button class="btn btn-add btn-sm" id="add">
												<i class="fa fa-plus "> </i>
											</button>

											<button class="btn btn-delete btn-sm" id="delete">
												<i class="fa fa-trash "> </i>
											</button>
										</div>
									</div>
								</div>
								<div class="form-group" style="margin-top: 10px;">
									<select class="form-control" id="project" name='project'></select>
								</div>
							</div>
						</div>
						<div class="row hide" id='addfolder'>
							<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
								<label for="active">Add Folder<span
									class="asteriskField">&nbsp;*</span>
								</label>
								<div class="form-group">
									<input type="text" class="form-control" id="folderName"
										placeholder="folder Name" name='folderName'>
								</div>
							</div>
						</div>
						<div class="row" id="viewfolder">
							<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
								<div class="form-group">
									<label for="active">View Folder<span
										class="asteriskField">&nbsp;*</span></label> <select disabled
										id="folderList" name='folderList'>
									</select>
								</div>
							</div>
						</div>
						<div class="row hide" id="exentension">
							<div class='offset-md-2 col-md-8 col-sm-12 col-12'>
								<div class="form-group">
									<label for="active">Extension Type<span
										class="asteriskField">&nbsp;*</span></label> <select
										class="form-control" id="extensionType" name='extensionType'>
										<option value="vox">VOX</option>
										<option value="wav">WAV</option>
									</select>
								</div>
							</div>
						</div>

						<div class="row">
						<div class='offset-md-2 col-md-4 col-sm-12 col-12'>
						<br>
								<label style="text-decoration: underline;">Note:</label> <small>Example
									Path format: <span
									style="font-weight: 600; background-color: #dedede">
										\projectname\foldername\</span>
								</small>
							</div>
							<div class="col-md-2 col-sm-12 col-12">
								<button type="button" class="btn btn-submit btn-sm btn-block"
									id="submit" disabled>Submit</button>
							</div>
							<div class="col-md-2  col-sm-12 col-12">
								<button type="reset" class="btn btn-submit btn-sm btn-block"
									id="reset">Reset</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id='AddProject' role="dialog">
		<div class="modal-dialog"
			style="max-width: 50%; background-color: #e0e0e030">
			<div class="modal-content">
				<form name='AddProjectForm' id='AddProjectForm' autocomplete="off">
					<div class="modal-body">
						<div class="model-header">
							<div class='row' style="margin: 5px;">
								<div class='col-md-9 col-sm-10 col-10'>
									<label>Add Project</label>
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
						<div class='model-box'>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="projectName">Project Name <span
											class='asteriskField'> *</span></label> <input type="text"
											class="form-control form-control-sm" id="projectName"
											placeholder="Project Name" name='projectName'>
									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="hostName">Host Name <span
											class='asteriskField'> *</span></label> <input type="text"
											class="form-control form-control-sm" id="hostName"
											name='hostName' placeholder="Host Name">
									</div>
								</div>
							</div>
							<div class=row style="margin-bottom: 10px;">
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="audioFilePath">Audio File Path <span
											class='asteriskField'> *</span></label> <input type="text"
											class="form-control form-control-sm" id="projectPath"
											name="projectPath" placeholder="Audio File path"
											name='audioFilePath'>
									</div>
								</div>
							</div>

							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<label style="text-decoration: underline;">Note:</label> <small>Example
										Path format: <span
										style="font-weight: 600; background-color: #dedede">
											\projectname\foldername\</span>
									</small>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal" id='EditProject' role="dialog">
		<div class="modal-dialog"
			style="max-width: 50%; background-color: #e0e0e030">
			<div class="modal-content">
				<form name='EditProjectForm' id='EditProjectForm' autocomplete="off">
					<div class="modal-body">
						<div class="model-header">
							<div class='row' style="margin: 5px;">
								<div class='col-md-9 col-sm-10 col-10'>
									<label><i class='fa fa-edit'></i> Edit Project Info</label>
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
						<div class='model-box'>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="projectName">Project Name</label> <input
											type="text" class="form-control form-control-sm"
											id="projectName" placeholder="Project Name"
											name='projectName'> <input type="hidden"
											name="projectId" id="projectId">

									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="hostName">Host Name</label> <input type="text"
											class="form-control form-control-sm" id="hostName"
											name='hostName' placeholder="Host Name">
									</div>
								</div>
							</div>
							<div class=row style="margin-bottom: 10px;">
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="audioFilePath">Audio File Path</label> <input
											type="text" class="form-control form-control-sm"
											id="projectPath" name="projectPath"
											placeholder="Audio File path" name='audioFilePath'>
									</div>
								</div>
							</div>

							<div class=row style="margin-bottom: 10px;">
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<label style="text-decoration: underline;">Note:</label> <small>Example
										Path format: <span
										style="font-weight: 600; background-color: #dedede">
											\projectname\foldername\</span></span>
									</small>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="../layout/footer.jsp"%>
<%@ include file="../layout/foot.jsp"%>
<script src="customjs/projectconfig.js"></script>
<script type="text/javascript">
	$("#folderList").select2({
		multiple : true,
		width : '100%',
	});
</script>