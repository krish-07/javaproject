<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<div class="row">
	<div class='col-md-12 col-sm-12 col-12'>
		<div class=row style="margin-top: 90px;">
			<div class='offset-md-2 col-md-4 offset-sm-4 col-sm-4 offset-4 col-4'>
				<label style="color: teal; font-size: 17px" class="marker"><i
					class="fa fa-search"></i> TRACE </label>
			</div>
			<div class='offset-md-4 col-md-2  col-sm-2 col-4 text-center'>
				<i class="fa fa-filter fa-lg" data-toggle="tooltip"
					style="color: teal; font-size: 30px" title="show/hide" id='filter'></i>
			</div>
		</div>
		<div class='filter' style="border: 0px solid teal;">
			<form name='filterForm' id='filterForm'>
				<div class=row>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="fileName">File Name</label> <input type="text"
								class="form-control form-control-sm" id="fileName"
								placeholder="file Name" name='fileName'>
						</div>
					</div>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="fileName">Voice Name</label> <select
								class="form-control form-control-sm" id="voiceName"
								name='voiceName'>
								<option value=''>select voice</option>
							</select>
						</div>
					</div>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="gender">Gender</label> <input type="text"
								class="form-control form-control-sm" id="gender" name='gender'
								readonly>
						</div>
					</div>
				</div>
				<div class=row>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="fileName">Audio Type</label> <select
								class="form-control form-control-sm" id="audioType"
								name='audioType'>
								<option value=''>select audiotype</option>
								<option value='wav'>wav</option>
								<option value='vox'>vox</option>
							</select>
						</div>
					</div>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="fileName">Language</label> <select
								class="form-control form-control-sm" id="language"
								name='language'>
								<option value=''>select language</option>
							</select>
						</div>
					</div>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="project">Project</label> <select
								class="form-control form-control-sm" id="projectName"
								name='projectName'>
								<option value=''>select project</option>
							</select>
						</div>
					</div>

				</div>
				<div class="row">
					<div class='offset-md-8 col-md-2 col-sm-12 col-12'>
						<button type="submit"
							class="btn btn-submit btn-sm btn-block tooldesign" id='submit'
							title='Fill atleast one of the field'>Submit</button>
					</div>
					<div class='col-md-2 col-sm-12 col-12'>
						<button type="reset" class="btn btn-reset btn-sm btn-block"
							id='reset'>Reset</button>
					</div>


				</div>
			</form>
		</div>
		<div class="report-box">
			<div class=row>
				<div class='col-md-12 col-sm-12 col-12'>
					<table
						class="table w-100  table-responsive-sm table-responsive-md	 table-responsive-lg table-bordered table-hover"
						id='reportTable'>
						<thead>
							<tr>
								<th>S.No</th>
								<th>File Name</th>
								<th>Audio Type</th>
								<th>Language</th>
								<th>Voice Name</th>
								<th>Gender</th>
								<th>Audio Info</th>
								<th>Status</th>
								<th>Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal" id='viewAudioInfo' tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body" style="background-color: #e0e0e030">
				<div class="model-header">
					<div class='row' style="padding: 5px">
						<div class='col-md-10 col-sm-10 col-10 text-left'>
							<label><i class="fa fa-info-circle fa-lg"
								style="color: white;"></i> Audio Info</label>
						</div>
						<div class=' col-md-1 col-sm-1 col-1 text-right'>
							<button data-dismiss="modal" class="btn btn-model btn-sm">
								<i class="fa fa-window-close" style="color: red;"></i>
							</button>
						</div>
					</div>
				</div>
				<div class="model-box">
					<div class='row' style="margin: 20px;">
						<div class='col-md-12 col-sm-12 col-12'>
							<table
								class="table-striped table-hover table w-100  table-responsive-sm table-responsive-md	 table-responsive-lg ">
								<tr>
									<td><label>Project Name </label></td>
									<td id="projectName"></td>
								</tr>

								<tr>
									<td><label>Audio Data </label></td>
									<td style="font-size: 14px !important;">
										<article id="audioData"></article>
									</td>
								</tr>


								<tr>
									<td><label>Host Name </label></td>
									<td id="hostName"></td>
								</tr>

								<tr>
									<td><label>Audio File Path </label></td>
									<td id="audioFilePath"></td>
								</tr>
								<tr>
									<td><label>Created Date&amp;Time </label></td>
									<td id="createdDateTime"></td>
								</tr>
								<tr>
									<td><label>Modified Date&amp;Time </label></td>
									<td id="modifiedDateTime"></td>
								</tr>

								<tr>
									<td><label>ModifiedBy </label></td>
									<td id="modifiedBy"></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal" id='audioPlayer' tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body" style="background-color: #e0e0e030">
				<div class="model-header">
					<div class='row' style="padding: 5px;">
						<div class='col-md-10 col-sm-10 col-10'>
							<label>AUDIO PLAYER</label>
						</div>
						<div class='col-md-1 col-sm-1 col-1 text-right'>
							<button data-dismiss="modal" class="btn btn-model btn-sm">
								<i class="fa fa-window-close" style="color: red;"></i>
							</button>
						</div>
					</div>
				</div>
				<div class='model-box'>
					<div class='row'>
						<div class='col-md-12 col-sm-12 col-12 text-center'>
							<div class='card'>
								<div class='card-body' id="isExist"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal" id='updateTableContent' tabindex="-1" role="dialog">
	<div class="modal-dialog"
		style="max-width: 60%; background-color: #e0e0e030" role="document">
		<div class="modal-content">
			<form name='updateForm' id='updateForm'>
				<div class="modal-body" style="background-color: #e0e0e030">
					<div class="model-header">
						<div class="row" style="padding: 10px;">
							<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
								<label class="modal-title"><i class='fa fa-edit'></i> Update Prompt</label>
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
						<div class=row style="margin-top: 20px;">
							<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
								<div class="form-group">
									<input type="hidden" class="form-control form-control-sm"
										id="audioId" name='audioId'>
										<input type="hidden" class="form-control form-control-sm"
										id="projectAllocateId" name='projectAllocateId'>
										<input type="hidden" class="form-control form-control-sm"
										id="aFPathConfigId" name='aFPathConfigId'>
										
										<label for="Project Name">Project Name</label> 
									<input type="text" class="form-control form-control-sm"
										id="projectName" name='projectName' ReadOnly> 
										
										
										
								</div>
							</div>

							<div class='col-md-5 col-sm-12 col-12'>
								<div class="form-group">
								
								 <label for="fileName">File
										Name</label> <input type="text" class="form-control form-control-sm"
										id="fileName" placeholder="file Name" name='fileName' readonly>
										

								</div>
							</div>
						</div>

						<div class=row>
							<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
								<div class="form-group">
									<label for="language">Language</label> <select
										class="form-control form-control-sm" id="language"
										name='language'>
										<option value="">select language</option>
									</select>
								</div>
							</div>
							<div class='col-md-5 col-sm-12 col-12'>
								<div class="form-group">
								<label for="audioType">Audio Type</label> <select
										class="form-control form-control-sm" id="audioType"
										name='audioType'>
										<option value="">select language</option>	
										<option value='wav'>wav</option>
								<option value='vox'>vox</option>
										</select>
										
								</div>
							</div>
						</div>
						<!-- <div class="row" style="margin-bottom: 25px;"></div> -->
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
									</select>		<input type="hidden" id="extensionPathID" name="extensionPathID">				
									
								</div>
							</div>

						</div>
						<div class="row" style="margin-bottom: 25px;">
							<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
								<div class="form-group">
									<label for="gender">Gender</label><input type="text"
										class="form-control form-control-sm" id="gender"
										placeholder="gender" name='gender' ReadOnly>
								</div>
							</div>

							<div class='col-md-5 col-sm-12 col-12'>
								<div class="form-group">
									<label for="audioInput">Audio Input</label>
									<textarea rows="1" maxlength="500" style="resize:both;min-height: 70px;"
										class="form-control form-control-sm" id="audioInput"
										name='audioInput'></textarea>

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
<script src="customjs/trace.js"></script>