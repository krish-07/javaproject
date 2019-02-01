var reportTable;

var onLoadJson = {};
var extensionaudioPathId;

var action = "<div class='text-center btn-type'>"
		+ "<button type='button' id='edit'   class='btn btn-edit btn-sm' data-toggle='modal' data-target='#updateTableContent' data-toggle='tooltip' title='modify' ><i class='fa fa-edit fa-lg'> </i></button> "
		+ "<button type='button' id='delete' class='btn btn-trash btn-sm' data-toggle='tooltip' title='delete'><i class='fa fa-trash fa-lg'></i></button> "
		+ "<button type='button' id='play'   class='btn btn-play btn-sm' data-toggle='modal' data-target='#audioPlayer' data-toggle='tooltip' title='play audio'><i class='fa fa-play fa-lg'> </i> </button> </div>";

$(document).ready(function() {

OnloadProp();

$(document).on('change', '#voiceName,#updateTableContent #voiceName', function() {
	
	
	var isMale = 0;
	var isFemale=0;
	for (var i = 0; i < onLoadJson.Male.length; i++) {
			if($(this).val() == onLoadJson.Male[i] ){
				$("#gender,#updateTableContent #gender").val("Male");
					isMale = 1;
		}
			
	}
	
	for (var i = 0; i < onLoadJson.Female.length; i++) {
		if($(this).val() == onLoadJson.Female[i] ){
			 $("#gender,#updateTableContent #gender").val("Female") ;
				isFemale = 1;
	}
		
}
	
	if((isMale != 1)&&(isFemale!=1) ){
		$("#gender").val("");
	}

});



	
reportTable = $("#reportTable").DataTable({
						"searching" : false,
						'lengthChange' : false,
						'ordering' : false,
						'rowId' : 'audioId',

						columns : [ {
							data : "sNo",
							"width": "3%"
						}, {
							data : "fileName",
							"width": "12%"
						}, {
							data : "audioType",
							"width": "12%"
						}, {
							data : "language",
							"width": "5%"
						}, {
							data : "voiceName"
						}, {
							data : "gender",
							"width": "5%"
						},{
							data : "detail"
						},{
							data : "audioStatus"
						},{
							data : "action",
							"width": "12%"
						} ],
					});

					$("#reset").click(function() {
						reportTable.clear().draw();
					});
					
					$('#updateTableContent').on('hidden.bs.modal', function () {
						$(this).find("input,textarea,select").val("");
						$("#updateFilterForm").validate().resetForm();
						$(this).find("select,textarea,input").removeClass("ErrorClass");
					});
					
					
					$("#filterForm").validate({
						submitHandler : function(form) {
							var formData = $(form).serialize();
							var formCounter = 0;
							var splitData = formData.split('&');
							for (var i = 0; i < splitData.length; i++) {
								var splitString = splitData[i].split('=');
								if (splitString[1] !== '') {
									formCounter++;
								}
							}
							if (formCounter > 0 && $("#projectName").val()!="" ) {
								filter(formData);
							} else {
								$.alert({
									 title: '<b><i class="fa fa-exclamation-triangle"></i> Warning!</b>',
								    content: 'Atleast Project to be selected!',
								    autoClose: 'close|800',
								    buttons: {
								    	close: function () {
								        }
								    }
								});
							}
							
						}
					});
				
					// View audio content
					reportTable.on('click', "#viewInfo",
							function() {
								var currentRow = reportTable.row($(this)
										.parents("tr"));
								var currentRowData = reportTable
										.row(currentRow).data();
								$("#viewAudioInfo #projectName").html(
										currentRowData.projectName)
										
									$("#viewAudioInfo #audioData").html(
										currentRowData.audioInput)
										
											
									$("#viewAudioInfo #hostName").html(
										currentRowData.hostName)
										
											
									$("#viewAudioInfo #audioFilePath").html(
										currentRowData.audioFilePath)
										
											
									$("#viewAudioInfo #createdDateTime").html(
										currentRowData.createdDateTime)
										
									$("#viewAudioInfo #modifiedDateTime").html(
										currentRowData.lastModifiedDateTime)
										
									$("#viewAudioInfo #modifiedBy").html(
										currentRowData.userId)

							});

					// Update table content
					reportTable.on('click', "#edit",
							function() {
						
								var currentRow = reportTable.row($(this)
										.parents("tr"));
								var currentRowData = reportTable
										.row(currentRow).data();
								getAudioFilePath(currentRowData.projectName,currentRowData.audioType)
								
								  $('#updateTableContent').on('show.bs.modal', function (event) {
								
								$("#updateTableContent  #audioId").val(
										currentRowData.audioId);
								$("#updateTableContent  #audioInput").val(
										currentRowData.audioInput);
								$("#updateTableContent #gender").val(
										currentRowData.gender).change();
								$("#updateTableContent #language").val(
										currentRowData.language).change();
								$("#updateTableContent #voiceName").val(
										currentRowData.voiceName).change();
								$("#updateTableContent #audioType").val(
										currentRowData.audioType).change(function(){
											
						getAudioFilePath(currentRowData.projectName,$("#updateTableContent #audioType").val());
												});
								$("#updateTableContent #fileName").val(
										currentRowData.fileName);
								$("#updateTableContent #projectName").val(
										currentRowData.projectName);
								/*$("#updateTableContent #audioFilePath").empty();
								$("#updateTableContent #audioFilePath").append("<option value="+currentRowData.audioFilePath+">"+currentRowData.audioFilePath+"</option>");*/
								$("#updateTableContent #projectAllocateId").val(
										currentRowData.projectAllocateId).change();
								$("#updateTableContent #aFPathConfigId").val(
										currentRowData.aFPathConfigId).change();
								$("#updateTableContent #audioFilePath").val(
										currentRowData.audioFilePath).change(function(){
											var getAudioPath=$("#updateTableContent #audioFilePath").val();
											
											 $.each(extensionaudioPathId.audioFilePath, function( key, value ) {
												if(getAudioPath==value.audioFilePath){
													$("#extensionPathID").val(value.ExtensionID);
												}
													});
										});
								  });
								

							});

					// Delete table content
					reportTable.on('click',"#delete",function() {
										var currentRow = reportTable
												.row($(this).parents("tr"));
										var currentRowData = reportTable.row(
												currentRow).data();
										$.confirm({
											title : '',
											content : "Do you want to delete the records in the filename  <b>"
												+ currentRowData.fileName
												+ "</b>?",
											type : 'blue',
											buttons : {
												OK:{
												action : function() {
													deleteAudioRecord(currentRowData.audioId);
												}
											},
											CANCEL:{
												
											},
											}
										});

									});

					reportTable.on('click',"#play",function() {
										var currentRow = reportTable
												.row($(this).parents("tr"));
										var rowData = reportTable.row(
												currentRow).data();

										$.ajax({
													url : "FileExist",
													method : "POST",
													data : "FileName="
														+rowData.audioFilePath
														+'\\'
															+rowData.fileName
															+ '.'
															+ rowData.audioType,
													success : function(response) {
														if (response) {
															 var fileNameReplace=rowData.audioFilePath+"\\"+
																rowData.fileName
																+ "."
																+ rowData.audioType;
															var html = "<audio controls  controlsList='nodownload' src='PlayAudio?fileName="+fileNameReplace.replace(/\\/g,'/')+"'></audio>";
										            
										
															$(
																	"#audioPlayer #isExist")
																	.html(html);
														} else {
															$(
																	"#audioPlayer #isExist")
																	.html(
																			"<div class='not-found'> <i class='fa fa-exclamation-circle'></i> Audio doesn't exist </div>")
														}
													}

												});
									});


					$("#updateForm").validate({
						errorClass : "ErrorClass",
						rules : {
							audioInput : {
								required : true,
							},
							gender : {
								required : true,
							},
							language : {
								required : true,
							},
							voiceName : {
								required : true,
							},
							audioType : {
								required : true,
							},
							fileName : {
								required : true,
							},
							audioFilePath : {
								required : true,
							},
						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							UpdateFilter(formData);
						}
					});

				});

function filter(formData) {

	$.ajax({
		url : 'Filter',
		method : 'Post',
		data : formData,
		success : function(response) {
			drawTable(response);
		}
	});
}

function drawTable(data) {

	reportTable.clear().draw();
	if (data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			data[i].sNo = i + 1;
			data[i].action = action;
			data[i].detail  = "<div class='text-center'> <i class='fa fa-info-circle fa-lg' id='viewInfo' data-toggle='modal' data-target='#viewAudioInfo'></i> </div>" ;
			reportTable.row.add(data[i]).draw();
		}
	}
}

function UpdateFilter(formData) {
	
	formData=formData+"&extensionPathId="+$("#extensionPathID").val();
	$.ajax({
		url : 'updateFilter',
		method : 'Post',
		data : formData,
		success : function(response) {
			console.log(formData);
		if(response== true){
			$("#updateTableContent").modal('hide');
			$.confirm({
			    title: '',
			    content: 'Data is updated into DB successfully',
			    type: 'green',
			    buttons: {
			        OK: {
			        	
			        	action : function() {
							updateAudio(formData);
						}
			        },
			        
			    }
			
			});
			
		}
		else{
			// $("#updateTableContent").modal('hide');
			$.confirm({
			    title: '',
			    content: 'Data is not updated into DB',
			    type: 'red',
			    buttons: {
			        OK: {
			       
			        },
			        
			    }
			
			});
			
		}
		
		}
	});
}

function updateAudio(formData) {
	var jc=$.dialog({
	    icon: 'fa fa-spinner fa-spin',
	    title: '<b>Working!</b>',
	    content: 'Sit back, we are processing your request!',
	   closeIcon:false,
	});
	
	$.ajax({
		url : 'UpdateAudio',
		method : 'Post',
		data : formData,
		success : function(response) {
			if(response==true){
				jc.close();
				$.confirm({
				    title: '',
				    content: 'Audio is Created successfully',
				    type: 'green',
				    buttons: {
				    	OK:{
				    	}
				    }
				
				});
			}
		  else{
			  jc.close();
			  $.confirm({
				    title: '',
				    content: 'Audio Failed to Create',
				    type: 'red',
				    buttons: {
				    	 OK: {
						       
					        },
				    }
				
				});
		  
		  }
		}
});
}



function OnloadProp() {

	$.ajax({
		url : 'OnloadProp',
		method : 'Post',
		success : function(response) {
			var projectName=[];
			const projectNameSet = new Set();
			for (var i = 0; i < response.project.length; i++) {
				projectNameSet.add(response.project[i].ProjectName);
			}
			
			
			var iterator = projectNameSet.values();
			for (var i = 0; i <projectNameSet.size; i++) {
				var showProject = iterator.next().value;
				projectName.push(showProject)
		      $("#projectName,#updateTableContent #projectName").append("<option value='"+showProject +"'>"+ showProject+"</option>");
		    }
			
			onLoadJson.Project = projectName;
			
			$.each(response.voiceConfig, function( key, value ) {
				  var voiceName = [];
				  $.each(value.VoiceType.split(","), function( voiceKey, voiceValue ) {
					  voiceName.push(voiceValue);
					  $("#voiceName,#updateTableContent #voiceName").append("<option value='"+voiceValue +"'>"+voiceValue+"</option>")
				  });
				  if(value.Gender.toUpperCase().trim() == "MALE" ) {
					  onLoadJson.Male= voiceName;
				  } else {
					  onLoadJson.Female= voiceName;
				  }
				  onLoadJson.language = value.Language;
			});
			$("#language,#updateTableContent #language").append("<option value='"+ onLoadJson.language +"'>"+ onLoadJson.language+"</option>");
			
			
			
		}
	});
}

function deleteAudioRecord(audioId) {
	$.ajax({
		url : 'DeleteAudioRecord',
		method : 'Post',
		data : "AudioId=" + audioId,
		success : function(response) {
			
			reportTable.row("#" + audioId).remove().draw();
		}
	});
}
function getAudioFilePath(projectName,audioType) {
	$.ajax({
		url : 'AudioFilePath',
		method : 'Post',
		data : "projectName=" + projectName+"&audioType="+audioType,
		success : function(response) {
			extensionaudioPathId=response;
			$("#updateTableContent #audioFilePath").empty();
			$.each(response.audioFilePath, function( key, value ) {
			$("#updateTableContent #audioFilePath").append("<option value='"+ value.audioFilePath +"'>"+  value.audioFilePath+"</option>");
			$("#extensionPathID").val(value.ExtensionID);
			})
		}
	});
}
$("#filter").click(function() {
	$(".filter").toggle();
});

