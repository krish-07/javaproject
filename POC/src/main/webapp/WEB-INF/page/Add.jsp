<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<div class="row">
	<div class='col-md-12 col-sm-12 col-12'>
		<div class=row style="margin-top: 90px;">
			<div class='offset-md-2 col-md-4 offset-sm-4 col-sm-3 offset-4 col-6'>
				<label style="color: teal; font-size: 17px" class="marker"><i
					class="fa fa-volume-up" aria-hidden="true"></i> Create Prompt</label>
			</div>
						
			<div class='offset-md-2 col-md-4 col-sm-2 col-2'>
				<ul class="nav nav-tabs">
                <li class="nav-item"><a class="nav-link " data-toggle="tab" href="#" id="singleUpload" style="background-color:#c7c7c7;">SingleFile Import</a></li>
                <li class="nav-item"><a class="nav-link" href="#" data-toggle="tab"
					data-target="#viewUploadFiles" id='uploadFiles'>ExcelFile Import</a></li>
            </ul>
			</div> 

		</div>

		<div class='filter' id="addFilter">
			<div class="row" id="filesCountDesign">
				<div class='offset-md-8 col-md-5 col-sm-12 col-12'
					id="audioFileDesign">
					<label id="fileCount">AudioFileCount: 0/0</label>
					<div id="myProgress"
						class="progress-bar progress-bar-striped active">
						<div id="myBar">
							<span id="progressText">0%</span>
						</div>
					</div>
				</div>
			</div>
			<form name='addFilterForm' id='addFilterForm'>
				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
						<label for="projectName">Project Name</label> <select
								class="form-control form-control-sm" id="projectName"
								name='projectName'>
							</select>
													</div>
					</div>
					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
						<label for="fileName">File Name</label> <input type="text"
								class="form-control form-control-sm" id="fileName"
								placeholder="file Name" name='fileName' autocomplete="off">
						</div>
					</div>
				</div>

				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="language">Language</label> <select
								class="form-control form-control-sm" id="language"
								name='language'>
							</select>
						</div>
					</div>

					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="audioType">Audio Type</label> <select
								class="form-control form-control-sm" id="audioType"
								name='audioType'>
							</select>
						</div>
					</div>
				</div>

				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="voiceName">Voice Name</label> <select
								class="form-control form-control-sm" id="voiceName"
								name='voiceName'>
							</select>
						</div>
					</div>
					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="audioFilePath">Audio FilePath</label> <select
								class="form-control form-control-sm" id="audioFilePath"
								name='audioFilePath'>
								</select>
						</div>
					</div>
				</div>

				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
						<label for="gender">Gender</label> <input type="text"
								class="form-control form-control-sm" id="gender"
								placeholder="Gender" name='gender' ReadOnly>
						</div>
					</div>
					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="audioInput">Audio Input</label>
							<textarea maxlength="500" class="form-control form-control-sm"
								id="audioInput" name='audioInput'></textarea>

						</div>

					</div>
				</div>
				<div class="row">
					<div class='offset-md-6 col-md-5 col-sm-12 col-12'>
						<div class="row">
							<div class='col-md-6 col-sm-6 col-6'>
								<button type="submit" class="btn btn-submit btn-sm btn-block"
									id="submit">Submit</button>
							</div>
							<div class='col-md-6 col-sm-6 col-6'>
								<button type="reset" class="btn btn-reset btn-sm btn-block"
									id="reset">Reset</button>
							</div>
						</div>
					</div>
				</div>

			</form>


					</div>
					<div class='filter' id="chooseFileDiv">
	<form name='ChooseFilePath' id='ChooseFilePath'>
				<div class="row">
					<div class="offset-md-1 col-md-5 col-sm-12 col-12">
						<label for="excelFilePath" style="color: teal; font-size: 20px"
							class="marker">Choose Excel File</label>
					</div>
				</div>
				<div class="row">
					<div class='offset-md-1 col-md-4 col-sm-12 col-12'>
						<input type="file" class="form-control form-control-sm"
							style="background: #f0f0f0"
							accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
							name="excelFilePath" id="excelFilePath">
					</div>

					<div class='col-md-2'>
						<button type="submit" class="btn btn-submit btn-sm btn-block"
							id="modalSubmit" style="background-color: #c7c7c7">Submit</button>
					</div>
				</div>

			</form>
	</div>
					
					

	</div>
	
	<!--Excel Modal -->
	<div class="modal fade" id="excelStatus">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<div class="model-header"
						style="padding-left: 10px; padding-top: 3px;">
						<div class="row">
							<div class="col-md-6" style="color: white;padding-top: 2px;">
								<h4>Excel Audio Status</h4>
							</div>
							<div class="col-md-6">
								<button id="excelStatusModal" class="btn"
									style="background-color: black; color: green; float: right; margin-right: 5px; margin-bottom: 1px;">
									<i class="fa fa-close"></i>
								</button>
							</div>
						</div>
					</div>
					
					
					<div class="model-box" style="height: 400px;overflow-y: auto;">
						<table class="table table-striped" id="excelStatusTable">
						
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

</div>


		


<%@ include file="../layout/footer.jsp"%>
<%@ include file="../layout/foot.jsp"%>
<script src="customjs/Add.js"></script>