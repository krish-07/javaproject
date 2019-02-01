<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<section>
	<div class="row">
		<div class='col-md-12 col-sm-12 col-12'>
			<div class=row style="margin-top: 90px;">
				<div
					class='offset-md-2 col-md-4 offset-sm-4 col-sm-3 offset-4 col-6'>
					<label style="color: teal; font-size: 17px" class="marker"><i
						class="fa fa-cog" aria-hidden="true"></i> PromptConfig</label>
				</div>
				<div class='offset-md-5 col-md-1 col-sm-4 col-2 '>
					<i class="fa fa-plus fa-lg" data-toggle="modal" style="color: teal"
						data-target="#addService"></i>
				</div>
			</div>
			<div class="report-box">
				<div class=row>
					<div class='col-md-12 col-sm-12 col-12'>
						<table
							class="table w-100  table-responsive-sm table-responsive-md	 table-responsive-lg table-bordered table-hover"
							id='pocMenu'>
							<thead>
								<tr>
									<th>S.No</th>
									<th>ApiServiceName</th>
									<th>LanguageMenus</th>
									<th>VoiceNameMenus</th>
									<th>GenderMenus</th>
									<th>AudioTypeMenus</th>
									<th>Action</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id='addService' role="dialog">
		<div class="modal-dialog"
			style="max-width: 50%; background-color: #e0e0e030">
			<div class="modal-content">
				<form name='AddServiceForm' id='AddServiceForm'>
					<div class="modal-body">
						<div class="model-header">
							<div class='row' style="margin: 5px;">
								<div class='col-md-9 col-sm-10 col-10'>
									<span style="color: white;"><i class="fa fa-edit-plus"></i>
										Add New Service </span>
								</div>
								<div class='col-md-1 col-sm-1 col-1 text-right'>
									<button class="btn btn-model btn-sm" id="modalSubmit">
										<i class="fa fa-save" style="color: cyan"></i>
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
										<label for="apiServiceName">ApiServiceName<span
											class="asteriskField">&nbsp;*</span></label> <input type="text"
											class="form-control form-control-sm" id="apiServiceName"
											name='apiServiceName' placeholder="ApiServiceName">
									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="languageMenus">LanguageMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
									id="languageMenuAdd" name='languageMenu'>
									<option value='en-us'>en-us</option>
									<option value='es-us'>es-us</option>
									</select>
									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="audioTypeMenus">AudioTypeMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
											id="audiotypeMenuAdd" name='audiotypeMenu'>
											<option value='wav'>wav</option>
											<option value='vox'>vox</option>
										</select>
									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="voiceNameMenus">VoiceNameMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
											id="voiceNameMenuAdd" name='voiceNameMenu'>
											<option value='en-US-Wavenet-A'>en-US-Wavenet-A</option>
											<option value='en-US-Wavenet-B'>en-US-Wavenet-B</option>
											<option value='en-US-Wavenet-C'>en-US-Wavenet-C</option>
											<option value='en-US-Wavenet-D'>en-US-Wavenet-D</option>
											<option value='en-US-Wavenet-E'>en-US-Wavenet-E</option>
											<option value='en-US-Wavenet-F'>en-US-Wavenet-F</option>
											<option value='en-US-Standard-B'>en-US-Standard-B</option>
											<option value='en-US-Standard-C'>en-US-Standard-C</option>
											<option value='en-US-Standard-D'>en-US-Standard-D</option>

										</select>
									</div>
								</div>
							</div>


							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="genderMenus">GenderMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
											id="genderMenuAdd" name='genderMenu'>
											<option value='Male'>Male</option>
											<option value='Female'>Female</option>
										</select>
									</div>
								</div>
							</div>

						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	<div class="modal" id='editMenu' role="dialog">
		<div class="modal-dialog"
			style="max-width: 40%; background-color: #e0e0e030">
			<div class="modal-content">
				<form name='EditMenuForm' id='EditMenuForm'>
					<div class="modal-body">
						<div class="model-header">
							<div class='row' style="margin: 5px;">
								<div class='col-md-9 col-sm-10 col-10'>
									<span style="color: white;"><i class="fa fa-edit"></i>
										Edit Menu Info </span>
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
										<label for="apiServiceName">ApiServiceName<span
											class="asteriskField">&nbsp;*</span></label> <input type="text"
											class="form-control form-control-sm" id="apiServiceName"
											name='apiServiceName' placeholder="ApiServiceName"><input
											type="hidden" name="serviceId" id="serviceId">

									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="languageMenus">LanguageMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
									id="languageMenuSelect" name='languageMenu'>
									<option value='en-us'>en-us</option>
									<option value='es-us'>es-us</option>
									</select>
									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="audioTypeMenus">AudioTypeMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
											id="audiotypeMenuSelect" name='audiotypeMenu'>
											<option value='wav'>wav</option>
											<option value='vox'>vox</option>
										</select>

										<!--  <input type="text"
											class="form-control form-control-sm" id="audioTypeMenu" name="audioTypeMenu"
											placeholder="AudioTypeMenus"/> -->
									</div>
								</div>
							</div>
							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="voiceNameMenus">VoiceNameMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
											id="voiceNameMenuSelect" name='voiceNameMenu'>
											<option value='en-US-Wavenet-A'>en-US-Wavenet-A</option>
											<option value='en-US-Wavenet-B'>en-US-Wavenet-B</option>
											<option value='en-US-Wavenet-C'>en-US-Wavenet-C</option>
											<option value='en-US-Wavenet-D'>en-US-Wavenet-D</option>
											<option value='en-US-Wavenet-E'>en-US-Wavenet-E</option>
											<option value='en-US-Wavenet-F'>en-US-Wavenet-F</option>
											<option value='en-US-Standard-B'>en-US-Standard-B</option>
											<option value='en-US-Standard-C'>en-US-Standard-C</option>
											<option value='en-US-Standard-D'>en-US-Standard-D</option>

										</select>
										<!-- <input type="text"
											class="form-control form-control-sm" id="voiceNameMenu" name="voiceNameMenu"
											placeholder="VoiceNameMenus"/> -->
									</div>
								</div>
							</div>


							<div class=row>
								<div class='offset-md-1 col-md-10 col-sm-12 col-12'>
									<div class="form-group">
										<label for="genderMenus">GenderMenus<span
											class="asteriskField">&nbsp;*</span></label> <select
											id="genderMenuSelect" name='genderMenu'>
											<option value='Male'>Male</option>
											<option value='Female'>Female</option>
										</select>
										<!-- <input type="text"
											class="form-control form-control-sm" id="genderMenu" name="genderMenu"
											placeholder="GenderMenus"/> -->

									</div>
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
<script src="customjs/pocMenu.js"></script>
<script type="text/javascript">
	$("#audiotypeMenuSelect,#audiotypeMenuAdd").select2({
		multiple : true,
		width : '100%',
		placeholder : "Select AudioTypeMenus"
	});

	$("#voiceNameMenuSelect,#voiceNameMenuAdd").select2({
		multiple : true,
		width : '100%',
		placeholder : "Select VoiceNameMenus"
	});

	$("#genderMenuSelect,#genderMenuAdd").select2({
		multiple : true,
		width : '100%',
		placeholder : "Select GenderMenus"
	});
	
	$("#languageMenuSelect,#languageMenuAdd").select2({
		multiple : true,
		width : '100%',
		placeholder : "Select LanguageMenus"
	});
</script>

