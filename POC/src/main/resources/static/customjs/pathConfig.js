$(function() {

	onLoadPathValues();
	var formPathConfig = $("form[name='pathConfigValues']");

	formPathConfig.validate({
		errorClass : "ErrorClass",
		rules : {
			excelFilePath : {
				required : true,
			    remote : {
				url : "checkPath",
				type : "POST",
				data : {
					excelFilePath : function() {
						return $('#excelFilePath').val();
					},
				},
			},
			},
			audioCreatedFilePath : {
				required : true,
			    remote : {
				url : "CheckAudioPath",
				type : "POST",
				data : {
					audioCreatedFilePath : function() {
						return $('#audioCreatedFilePath').val();
					},
				},
			},
			},
		},

		messages : {
			excelFilePath : "Folder doesn't exist",
			audioCreatedFilePath : "Folder doesn't exist",
		},
		submitHandler : function(form) {
			
		}
	});
	
	$("#ChooseFolder").validate({
		errorClass : "ErrorClass",
		rules : {
			audioType : {
				required : true,
			},
			audioFilePath : {
				required : true,
			},
			
		},
		messages : {
			
			audioType : {
				required : "Please select Audio Type",
			},
			
			audioFilePath : {
				required : "Please enter Audio FilePath",
			},
		},
		submitHandler : function(form) {
			var formData = $(form).serialize();
			insertFilePath(formData);
			console.log(formData);
		}
	});

	

$("#savePathConfig").click(function() {                         
		if (formPathConfig.valid()) {
			submitPath(formPathConfig);
		}
	});

	
});


//submit Path
function submitPath(formPathConfig) {
var data = $(formPathConfig).serialize();
	$.ajax({
		url : "changeConfigPath",
		type : "POST",
		data :data,
		success : function(data) {
			switch (data) {
			case true:
				$.confirm({
					title : '',
					content : "Path Configuration saved Successfully",
					type : 'green',
					buttons : {
						OK : {
							action : function() {
								$("#saveConfig").attr("disabled","disabled");
							}
						},

					}
				});
				break;

			case false:
				$.confirm({
					title : '',
					content : "Please check the Folder Path",
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


// to set Path values to form
function onLoadPathValues() {
	$.ajax({
		url : "LoadPathPropertiesValue",
		type : "POST",
		dataType : "json",
		success : function(data) {
			$('input[name="excelFilePath"]').val(data.ExcelFilePath);
			$('input[name="audioCreatedFilePath"]').val(data.CreatedAudioFilePath);
		}
	});
}

function insertFilePath(formData){
	$.ajax({
		url : "saveFilePathDB",
		type : "POST",
		data :formData,
		success : function(data) {
			switch (data) {
			case true:
				$.confirm({
					title : '',
					content : "Data is inserted into DB successfully",
					type : 'green',
					buttons : {
						OK : {
							action : function() {
							}
						},

					}
				});
				break;

			case false:
				$.confirm({
					title : '',
					content : "Data isn't inserted into DB",
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
