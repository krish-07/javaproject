var filePath;
var fileName;
var maleVoiceName;
var femaleVoiceName;
var extensionId;
var projectAllocationId;
var extensionaudioPathId;

//var onloadResponse={};

$( window ).on( "load", function() {
	$("#addFilter").show();
	$("	#chooseFileDiv").hide();
});

$(document)
		.ready(
				function() {
					onLoadProjectValues();
					$("#singleUpload").click(function(){
						$("#addFilter").show();
						$("#chooseFileDiv").hide();
						$(this).css("background-color","#c7c7c7");
						$("#uploadFiles").css("background-color","white");
					});
					
					$("#uploadFiles").click(function(){
						$("#addFilter").hide();
						$("	#chooseFileDiv").show();
						$(this).css("background-color","#c7c7c7");
						$("#singleUpload").css("background-color","white");
					});
					
                 $("#projectName").change(function(){
                	 var projectNameGetter="projectNameGet="+$("#projectName").val();
                	 $.ajax({
						 url : "GetAudioType",
						 type : "Post",
						 data : projectNameGetter,
						 dataType : "json",
						 success : function(response){
                	 
                	      $("#audioType").empty();
         			      var audioTypeNames=[];
                	      
                	     
                	      if(response.AudioType==null){
                	    	 errorMessage("AudioType isnot configured in DB");
                	      }
                	      else{
                	    	  $("#audioType").append("<option value=''>Select AudioType</option>");
                	    	  audioTypeNames=response.AudioType.split(",");
                    	      
                	      if(audioTypeNames.length==1){
       						 $("#audioType").append("<option value="+response.AudioType+">"+response.AudioType+"</option>");
       						 }
       					 
       						 else
       						 {
       							 for(var j=0;j<audioTypeNames.length;j++){
       								 $("#audioType").append("<option value="+audioTypeNames[j]+">"+audioTypeNames[j]+"</option>");
       							 }
       						 }	
                	      }
             		
						 }
                	 });
						
					});
					
					$(document).on("change","#language",function(){
					 var lang=$("#language").val();
						 var langGetter = "langGet="+lang;
						 $.ajax({
							 url : "GetVoiceName",
							 type : "Post",
							 data : langGetter,
							 dataType : "json",
							 success : function(response){
								 console.log(response);
								 femaleVoiceName=response.FemaleVoiceName;
								 maleVoiceName=response.MaleVoiceName;
								 var voiceNames=[];
								 $("#voiceName").empty();
								 if(response.VoiceMenu==null){
		                	    	 errorMessage("VoiceMenu isnot configured in DB");
		                	      }
								 else{
									 
								 $("#voiceName").append("<option value=''>Select VoiceNames</option>");
								if(response.VoiceMenu.length!=0){
									voiceNames=response.VoiceMenu.split(",");
									 for(var i=0;i<voiceNames.length;i++) {
										 $("#voiceName").append("<option value="+voiceNames[i]+">"+voiceNames[i]+"</option>");
									 }
								 }
								 }
								 
							 }
						 });
					 });
					
					$("#voiceName").change(function(){
						 var voice=$("#voiceName").val();
						 $("#gender").empty();
						 var maleVoiceNames=[];
						 maleVoiceNames=maleVoiceName.split(",");
						 
						 if(jQuery.inArray(voice,maleVoiceNames)!=-1){
							 $("#gender").val("Male"); 
						 }
						 else{
							 var femaleVoiceNames=[];
							 femaleVoiceNames=femaleVoiceName.split(",");
							 if(jQuery.inArray(voice,femaleVoiceNames)!=-1){
								 $("#gender").val("Female"); 
							 }
							 else{
								 $("#gender").val("");
							 }
							 
						 }
						 
						 });
					
					$("#audioType").change(function(){
						
						var audioExtensionForm="audioExtension="+$("#audioType").val()+"&projectName="+$("#projectName").val();
						var audioPaths=[];
						 $.ajax({
							 url : "GetAudioPath",
							 type : "Post",
							 data : audioExtensionForm,
							 dataType : "json",
							 success : function(response){
								 console.log(response);
								 
								 //extensionId=response.ExtensionPathID;
									 projectAllocationId=response.ProjectAllocationID;
								 $("#audioFilePath").empty();
								if(response.audioFilePath==null||response.ProjectAllocationID==null){
									 errorMessage("AudioFile isnot configured in DB");
								 }
								 else{
									 extensionaudioPathId=response;
								 $("#audioFilePath").append("<option value=''>Select audioFilePath</option>");
								 $.each(response.audioFilePath, function( key, value ) {
										$("#audioFilePath").append("<option value='"+ value.audioPaths +"'>"+  value.audioPaths+"</option>");
										})
								/* audioPaths=response.AudioFilePath.split(",");
								 if(audioPaths.length==1){
									 $("#audioFilePath").append("<option value="+response.AudioFilePath+">"+response.AudioFilePath+"</option>");
									 }
									 else
									 {
										 for(var i=0;i<audioPaths.length;i++){
											 $("#audioFilePath").append("<option value="+audioPaths[i]+">"+audioPaths[i]+"</option>");
										 }
									 }*/
								 }
							 }
						 });
						
					});
					
					$("#audioFilePath").change(function(){
						var getAudioPath=$("#audioFilePath").val();
						
						 $.each(extensionaudioPathId.audioFilePath, function( key, value ) {
							if(getAudioPath==value.audioPaths){
								extensionId=value.ExtensionID;
							}
								});
					});
					
					
					$("#addFilterForm").validate({
						errorClass : "ErrorClass",
						rules : {
							audioInput : {
								required : true,
							},
							gender : {
								required : true,
							},
							projectName : {
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
								remote : {
									url : "CheckFileName",
									type : "POST",
									data : {
										fileName : function() {
											return $('#fileName').val();
										},
									},
								},
							},
							audioFilePath : {
								required : true,
							},

						},
						messages : {
							audioInput : {
								required : "Please enter Audio Input",
							},
							gender : {
								required : "Please select Gender",
							},
							projectName : {
								required : "Please select Project Name",
							},
							language : {
								required : "Please select Language",
							},
							voiceName : {
								required : "Please select Voice Name",
							},
							audioType : {
								required : "Please select Audio Type",
							},
							fileName : {
								required : "Please enter File Name",
								remote : "FileName already exist!",
							},
							audioFilePath : {
								required : "Please select Audio FilePath",
							},
						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							addFilter(formData);
							console.log(formData);
						}
					});

				
					$("#uploadFiles").click(function() {
						$('#excelFilePath').val('');
					});
					$("#ChooseFilePath")
							.validate(
									{
										errorClass : "ErrorClass",
										rules : {
											excelFilePath : {
												required : true,
											},

										},
										errorPlacement : function(error,
												element) {
											return true;
										},

										submitHandler : function(form) {
											var formDataXls = new FormData();
											formDataXls
													.append(
															'file',
															$('input[type="file"]')[0].files[0]);
											checkExcelfile(formDataXls);
										}
									});

				});

function addFilter(formData) {
	var formData1=formData+"&extensionPathId="+extensionId+"&projectAllocationId="+projectAllocationId;
	$.ajax({
		url : 'addFilter',
		method : 'Post',
		data : formData1,
		success : function(response) {
			if(response==true){
			$.confirm({
				title : '',
				content : 'Data is inserted into DB Successfully',
				type : 'green',
				buttons : {
					OK : {
						action : function() {
						checkSingleAudio(formData);
					}
					},

				}
			});
			}
			else{
				$.confirm({
					title : '',
					content : 'Data is not inserted into DB',
					type : 'red',
					buttons : {
						OK : {
							
						},
					}
				});
			}

			$('#addFilterForm').find('input:text, select, textarea').val('');
		}
	});
}


function checkSingleAudio(formData) {
	var jc=$.dialog({
	    icon: 'fa fa-spinner fa-spin',
	    title: '<b>Working!</b>',
	    content: 'Sit back, we are processing your request!',
	   closeIcon:false,
	});
	$.ajax({
		url : 'checkSingleAudio',
		method : 'Post',
		data : formData,
		success : function(response) {
			console.log(response);
			if(response=="1"){
				jc.close();
			$.confirm({
				title : '',
				content : 'Audio is created successfully',
				type : 'green',
				buttons : {
					OK : {
					
					}
					},

				
			});
			}
			else if(response=="2"){
				jc.close();
				$.confirm({
					title : '',
					content : 'service temporarily unavailable',
					type : 'red',
					buttons : {
						OK : {
							
						},
					}
				});
			}
			else{
				jc.close();
				$.confirm({
					title : '',
					content : 'Audio is not created successfully',
					type : 'red',
					buttons : {
						OK : {
							
						},
					}
				});
			}

		}
	});
}





function checkExcelfile(formDataXls) {
	$.ajax({
				url : 'checkExcelFile',
				method : 'Post',
				processData : false,
				enctype : 'multipart/form-data',
				contentType : false,
				cache : false,
				data : formDataXls,
				success : function(response) {
					console.log(response);
					if (response.toString().trim()== "ExcelFile data is inserted successfully") {
						$
						.confirm({
							title : '',
							content : response,
							type : 'green',
							buttons : {
								OK:{
								action : function() {
									$('#viewUploadFiles').modal('hide');
									//$("#audioFileDesign").show();
									readUploadExcelFile(formDataXls);
								}
							},
							}
						});
					}
					else if(response.toString().trim()=="Mismatch Configuration in Excel File for Particular user"){
						$.confirm({
							title : '',
							content : response,
							type : 'red',
							buttons : {
								OK : {

								}
							},

						});
					}
					else{
					$
							.confirm({
								title : '',
								content : response,
								type : 'blue',
								buttons : {
									OK:{
									action : function() {
										$('#viewUploadFiles').modal('hide');
									}
								},
								}
							});
					}
				}
			});
}


var wsresponse;
function readUploadExcelFile(formDataXls) {
	var jc = $.dialog({
		icon : 'fa fa-spinner fa-spin',
		title : '<b>Working!</b>',
		content : 'Sit back, we are processing your request!',
		closeIcon : false,
	});
	$.ajax({
		url : 'readUploadExcelFile',
		method : 'Post',
		processData : false,
		enctype : 'multipart/form-data',
		contentType : false,
		cache : false,
		data : formDataXls,
		success : function(response) {
			wsresponse=response;
			$("#excelStatusTable").empty();
			$('#excelStatusTable').append('<tr style="font-weight: 700"><td>SNO</td><td>FileName</td><td>Status</td></tr>');
			for(var i=0;i<response.length;i++){		
				if(response[i].fileStatus==1){
					$('#excelStatusTable').append('<tr><td class="SNo">'+(i+1)+'</td><td>'+response[i].fileName+'</td><td><i class="fa fa-check fa-2x" style="color: green;padding-left: 15px;" aria-hidden="true"></i><td></tr>');	
				}else{
					$('#excelStatusTable').append('<tr><td class="SNo">'+(i+1)+'</td><td>'+response[i].fileName+'</td><td class="reUploadBtn"><button id="reUpload" class="btn" style="background-color:white;"><i  title="ReUpload" class="fa fa-refresh fa-lg" style="color: #ce3434;padding-left: 5px;" aria-hidden="true"></i></button><td></tr>');
				}
			}
			jc.close();
			$('#excelStatus').modal({
                backdrop: 'static',
                keyboard: true, 
                show: true
			});			
		}
	});
}

$("#excelStatusTable").on("click","#reUpload",function(){
	var jc = $.dialog({
		icon : 'fa fa-spinner fa-spin',
		title : '<b>Working!</b>',
		content : 'Sit back, we are processing your request!',
		closeIcon : false,
	});
	 var $row = $(this).closest("tr");    // Find the row
	 var $text = $row.find(".SNo").text(); // Find the text  
	 var reqObject=wsresponse[$text-1];
	 var json={"audioInput":reqObject.audioInput,"language":reqObject.language,"gender":reqObject.gender,"voiceName":reqObject.voiceName,"audioType":reqObject.audioType,"fileName": reqObject.fileName,"filePath":reqObject.audioFilePath,"projectAllocationId":reqObject.audioFilePath};
	 $.ajax({
		 url:"checkSingleAudio",
		 method:"Post",
		 data:json,	
		 dataType: "json",
		 success:function(response){
			 jc.close();
			 if(response==1){
				 $.confirm({
						title : '',
						content : 'Audio is  created successfully',
						type : 'green',
						buttons : {
							OK : {

							},
						}
				});
				 $row.find('td').eq(2).html('<i class="fa fa-check fa-2x" style="color: green;padding-left: 15px;" aria-hidden="true"></i>')
			 }
			 else{
				 $.confirm({
						title : '',
						content : 'Audio is not created successfully',
						type : 'red',
						buttons : {
							OK : {

							}
						},

					});
				 
			 }
		 },
		 error:function(response){
			 jc.close();
		 }
		 
	 }); 
});

$("#excelStatusModal").click(function(){
	$('#excelStatus').modal('hide');
});

$("#myBar").click(function() {
	// jc.close();
$.confirm({
		title : '',
		content : "<b>Audio isn't created for this filename:</b> "+displayFileName,
		type : 'red',
		buttons : {
			OK : {

			},
		}
	});
});

function onLoadProjectValues(){
	$.ajax({
		 url : "GetProjectName",
		 type : "Post",
		 dataType : "json",
		 success : function(response){
			 
			 if(response.ProjectName==null||response.Language==null){
				errorMessage("Mismatch Configured in DB");
			 }
			 else{
			 $("#projectName").empty();
			 
			 var projectNames=[];
			 var languageName=[];
			 $("#language").empty();
			 $("#language").append("<option value=''>Select Language</option>");
			 languageName=response.Language.split(",");
			 if(languageName.length==1){
			 $("#language").append("<option value="+response.Language+">"+response.Language+"</option>");
			 }
			 else{
				 for(var i=0;i<languageName.length;i++) {
					 $("#language").append("<option value="+languageName[i]+">"+languageName[i]+"</option>");
				 } 
			 }
			 $("#projectName").append("<option value=''>Select Project Name</option>");
			 projectNames=response.ProjectName.split(",");
			 if(projectNames.length==1){
			 $("#projectName").append("<option value="+response.ProjectName+">"+response.ProjectName+"</option>");
			 }
			 else
			 {
				 for(var i=0;i<projectNames.length;i++)
					 {
			 $("#projectName").append("<option value="+projectNames[i]+">"+projectNames[i]+"</option>");
			 }
			 }
			 
		 }
		 }
	 });
	
}

function errorMessage(message){
$.confirm({
	title : '',
	content : message,
	type : 'red',
	buttons : {
		OK : {

		},
	}
});
}
