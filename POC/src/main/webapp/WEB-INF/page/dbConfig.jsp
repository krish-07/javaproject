<%@ include file="../layout/head.jsp"%>
<%@ include file="../layout/header.jsp"%>
<div class="row">
	<div class='col-md-12 col-sm-12 col-12'>

		<div class=row style="margin-top: 90px;">
			<div class='offset-md-2 col-md-4 offset-sm-4 col-sm-3 offset-4 col-6'>
				<label style="color: teal; font-size: 17px" class="marker">&nbsp;&nbsp;
					<i class="fa fa-database" aria-hidden="true"></i> Database Config
				</label>
			</div>
		</div>
		<div class='filter'>
			<form name='dbConfigValues' id='dbConfigValues'>

				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="databaseType">Database Type<span
								class="asteriskField">&nbsp;*</span></label> <select
								class="select form-control" id="databaseType"
								name="databaseType">
								<option value="sql server">Sql Server</option>
							</select>
						</div>
					</div>
					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="hostName">Host Address<span
								class="asteriskField">&nbsp;*</span></label> <input class="form-control"
								id="hostName" name="hostName" placeholder="Host Address"
								type="text" value="" autocomplete="off" />

						</div>
					</div>

				</div>

				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="portNumber">Port<span class="asteriskField">&nbsp;*</span></label>
							<input class="form-control" id="portNumber" name="portNumber"
								placeholder="Port" type="text" value="" autocomplete="off" />
						</div>
					</div>
					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="databaseName">Database / Service Name<span
								class="asteriskField">&nbsp;*</span></label> <input class="form-control"
								id="databaseName" name="databaseName"
								placeholder="Database / Service Name" type="text" value=""
								autocomplete="off" />

						</div>
					</div>
				</div>

				<div class=row>
					<div class='offset-md-1 col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="userName">Database Username<span
								class="asteriskField">&nbsp;*</span></label> <input class="form-control"
								id="userName" name="userName" placeholder="Username" type="text"
								value="" autocomplete="off" />
						</div>
					</div>
					<div class='col-md-5 col-sm-12 col-12'>
						<div class="form-group">
							<label for="userPassword">Database Password<span
								class="asteriskField">&nbsp;*</span></label> <input class="form-control"
								id="userPassword" name="userPassword" placeholder="Password"
								type="password" value="" /> <span class="eye-icon"><i
								id="visiblePass" class="fa fa-eye"></i></span>
						</div>
					</div>
				</div>

				<div class='offset-md-6 col-md-5 col-sm-12 col-12'>
					<div class=row>
						<div class='offset-md-6 col-md-6 col-sm-6 col-6'>
							<button class="btn btn-submit btn-sm btn-block" id="test">Test</button>
						</div>
						<!-- <div class='col-md-6 col-sm-6 col-6'>
						<button type="submit" class="btn btn-submit btn-sm btn-block" id="saveConfig">Submit</button>
					</div> -->
					</div>
				</div>


			</form>
		</div>

	</div>

</div>




<%@ include file="../layout/footer.jsp"%>

<%@ include file="../layout/foot.jsp"%>
<script src="customjs/dbConfig.js"></script>