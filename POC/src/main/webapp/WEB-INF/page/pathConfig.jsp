<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<div class="row">
	<div class='col-md-12 col-sm-12 col-12'>

		<div class=row style="margin-top: 90px;">
			<div class='offset-md-2 col-md-4 offset-sm-4 col-sm-3 offset-4 col-6'>
				<label style="color: teal; font-size: 20px" class="marker">&nbsp;&nbsp;
					<i class="fa fa-cog"></i> FilePath Config
				</label>
			</div>

			<div class='offset-md-3 col-md-1 col-sm-4 col-2 text-right'>
				<i class="fa fa-folder" data-toggle="modal"
					data-target="#typeFolder" style="color: teal; font-size: 25px"
					id='uploadFiles'></i>
			</div>
		</div>
		<div class='filter'>
			<form name='pathConfigValues' id='pathConfigValues'>

				<div class=row>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="excelFilePath">Excel FilePath<span
								class="asteriskField">&nbsp;*</span></label> <input class="form-control"
								id="excelFilePath" name="excelFilePath"
								placeholder="ExcelFilePath" type="text" value="" />

						</div>
					</div>
					<div class='col-md-4 col-sm-12 col-12'>
						<div class="form-group">
							<label for="audioCreatedFilePath">CreatedAudio FilePath<span
								class="asteriskField">&nbsp;*</span></label> <input class="form-control"
								id="audioCreatedFilePath" name="audioCreatedFilePath"
								placeholder="AudioCreatedFilePath" type="text" value="" />

						</div>
					</div>

					<div class='col-md-2 col-sm-12 col-12'>

						<label for="savePathConfig"></label>
						<button type="button" class="btn btn-submit btn-sm btn-block"
							style="background-color: #c7c7c7; margin-top: 10px;"
							id="savePathConfig">Submit</button>

					</div>

				</div>
			</form>
		</div>

		<div class="modal" id='typeFolder' role="dialog">
			<div class="modal-dialog"
				style="max-width: 40%; background-color: #e0e0e030">
				<div class="modal-content">
					<form name='ChooseFolder' id='ChooseFolder'>
						<div class="modal-body">
							<div class="model-header">
								<div class='row' style="margin: 5px;">
									<div class='col-md-9 col-sm-10 col-10'>
										<label>Folder Config</label>
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
								<div class='row' style="margin: 20px;">
									<div class='offset-md-2 col-md-4 col-sm-12 col-12'>

										<label for="audioType">Audio Type:</label>
									</div>
									<div class='col-md-5 col-sm-12 col-12'>


										<select class="form-control form-control-sm" id="audioType"
											name='audioType'>
											<option value="">select audiotype</option>
											<option value='vox'>vox</option>
											<option value='wav'>wav</option>
										</select>
									</div>
								</div>
								<div class='row' style="margin: 20px;">
									<div class='offset-md-2 col-md-4 col-sm-12 col-12'>

										<label for="audioFilePath">Audio FilePath:</label>
									</div>
									<div class='col-md-5 col-sm-12 col-12'>
										<input class="form-control form-control-sm" type="text"
											id="audioFilePath" placeholder="audioFilePath"
											name='audioFilePath'>
									</div>


								</div>


							</div>
						</div>

					</form>
				</div>

			</div>
		</div>


	</div>

</div>




<%@ include file="../layout/footer.jsp"%>

<%@ include file="../layout/foot.jsp"%>

<script src="customjs/pathConfig.js"></script>