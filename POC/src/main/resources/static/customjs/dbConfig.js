$(document).ready(function() {

	onLoadDBValues();
	var formDBConfig = $("form[name='dbConfigValues']");

	formDBConfig.validate({
		errorClass : "ErrorClass",
		rules : {
			databaseType : {
			},
			hostName : "required",
			databaseName : "required",
			portNumber : {
				required : true,
				digits : true
			},
			userName : "required",
			userPassword : "required"
		},

		messages : {
			hostName : "Please enter Host Address",
			databaseName : "Please enter Database / Service Name",
			portNumber : {
				required : "Please enter Port Number",
				digits : "Please enter Digits"
			},
			userName : "Please enter Database Username",
			userPassword : "Please enter password"
		},
		submitHandler : function(form) {
			
		}
	});

	$("#test").click(function() {
		if (formDBConfig.valid()) {
			testConnection(formDBConfig);
			
		}
	});

$("#saveConfig").click(function() {                         
		if (formDBConfig.valid()) {
			submit(formDBConfig);
		}
	});

	$("#visiblePass").click(function() {
		$(this).toggleClass('fa-eye').toggleClass('fa-eye-slash');
		if ($('#userPassword').attr('type') == 'password')
			$('#userPassword').attr('type', 'text')
		else
			$('#userPassword').attr('type', 'password')
	});
});

// Test connection
function testConnection(formDBConfig) {
	var formData =$(formDBConfig).serialize();
	$.ajax({
		url : "DBConnection",
		type : 'post',
		data : formData,
		success : function(result) {
			switch (result) {
			case true:
				$.confirm({
					title : '',
					content : "Please check the database credentials",
					type : 'red',
					buttons : {
						OK : {
							
						},

					}
				});
				break;
			case false:
				$.confirm({
					title : '',
					content : "Connected Successfully",
					type : 'green',
					buttons : {
						OK : {
							/*action : function() {
								$("#saveConfig").removeAttr("disabled");
							}*/
						},

					}
				});
				break;
			default:
				break;
			}
		}
	});
}
//submit connection
function submit(formDBConfig) {
var data = $(formDBConfig).serialize();
	$.ajax({
		url : "changeConfigDB",
		type : "POST",
		data :data,
		success : function(data) {
			switch (data) {
			case true:
				$.confirm({
					title : '',
					content : "Configuration saved Successfully",
					type : 'green',
					buttons : {
						OK : {
							/*action : function() {
								$("#saveConfig").attr("disabled","disabled");
							}*/
						},

					}
				});
				break;

			case false:
				$.confirm({
					title : '',
					content : "Please check the database credentials",
					type : 'red',
					buttons : {
						OK : {
							
						},

					}
				});
				break;

			default:
				break;
			}
		}
	});
}


// to set database values to form
function onLoadDBValues() {
	$.ajax({
		url : "LoadPropertiesValue",
		type : "POST",
		dataType : "json",
		success : function(data) {
			$('input[name="userName"]').val(data.UserName);
			$('input[name="databaseName"]').val(data.Database);
			$('input[name="userPassword"]').val(data.Password);
			$('input[name="portNumber"]').val(data.Port);
			$('input[name="hostName"]').val(data.HostName);
		}
	});
}
