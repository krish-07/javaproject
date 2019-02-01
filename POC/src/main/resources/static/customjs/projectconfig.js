var projectMgmt;
var counter = 0;


var action = "<div class='text-center btn-type'>"
		+ " <button type='button' id='edit' class='btn btn-edit btn-sm' data-toggle='modal' data-target='#EditProject' data-toggle='tooltip' title='add path' ><i class='fa fa-edit fa-lg'></i> </button> " 
		+ "<button type='button' id='delete' class='btn btn-trash btn-sm' data-toggle='tooltip' title='delete'><i class='fa fa-trash fa-lg'></i> </button> </div> ";

$(document).ready(function() {
	
	$("#reset").click(function(){
		$("#allocateProject").val(null).change();
		$("#serviceName").val(null).change();
	});
	
	
	$("#reset").click(function(){
		$("#project,#folderName").val('');
		$("#folderList").val(null).change();
	});
	
	$("#view").addClass("crud");
	
	$("#projecttab").click(function(){
		$("#folderMapping").addClass("hide");
		$("#projectManagement").removeClass("hide");
	});
	
	$("#foldermaptab").click(function(){
		$("#folderMapping").removeClass("hide");
		$("#projectManagement").addClass("hide");
	});
	
	
	$("#folderMapping").on("change","#project",function(){
		var projectId= $(this).val();
		$("#folderList").val(null).change();
		if(projectId != "" ) {
			getFolderByProject(projectId);	
		} 
	});
	
	
			projectMgmt = $("#projectMgmt").DataTable({
				'lengthChange' : false,
				 "ordering": false,
				 'rowId' : 'projectId',

				columns : [ {
					data : "sNo"
				}, {
					data : "projectName"
				}, {
					data : "hostName"
				}, {
					data : "projectPath"
				}, {
					data : "action",
				}, ],
			});
			
			getAllProject();
			$("view").addClass("crud")
			
			$("#add").click(function() {
				$(this).addClass("crud")
				$("#delete,#view").removeClass("crud")
				counter = 1;
				$("#project").val('');
				$("#viewfolder").addClass("hide");
				$("#addfolder,#exentension").removeClass("hide");
				$("#submit").prop("disabled",false);
				$("#folderList").empty();
				
			})
			
			$("#delete").click(function() {
				$(this).addClass("crud");
				$("#view,#add").removeClass("crud")
				counter = 2;
				$("#folderList").empty();
				$("#project,#folderName").val('');
				$("#viewfolder").removeClass("hide");
				$("#addfolder,#exentension").addClass("hide");
				$("#folderList").val(null).change();
				$("#folderList").prop("disabled",false);
				$("#submit").prop("disabled",false);
			})
			
			$("#view").click(function() {
				counter = 0;
				$(this).addClass("crud")
				$("#delete,#add").removeClass("crud")
				$("#folderList").empty();
				$("#submit").prop("disabled",true);
				$("#project").val('');
				$("#viewfolder").removeClass("hide");
				$("#addfolder,#exentension").addClass("hide");
				$("#folderList").prop("disabled",true);
				$("#folderList").val(null).change();
				
			})
			
			
			$("#submit").click(function() {
				console.log(counter)
				var projectId = $("#project").val();
				if(counter == 1) {
					var extensionType = $("#folderName").val();
					var extension = $("#extensionType").val();
					if(projectId!="" && folderName != "" && extensionType != "") {
						var pathPattern = /^\\[\\\S|*\S]?.*$/;
						if( pathPattern.test(extensionType)) {
							var formData ="extensionType="+extensionType+"&projectId="+projectId+"&extension="+extension;
							addFolderByProject(formData);
						} else {
							pathValidate();
						}
					} else {
						confirmAlert();
					}
				}
					else if(counter == 2) {
						var folderList= $("#folderList").val();
						if(projectId!="" && folderList != "" ) {
							var formValue ="folderList="+folderList+"&projectId="+projectId;
							deleteFolder(formValue)
						}else {
							confirmAlert();
						}
					}
			})
			
			$("#AddProjectForm").validate({
				errorClass : "ErrorClass",
				rules : {
					projectName : {
						required : true,
					},
					hostName : {
						required : true,
					},
					projectPath : {
						required : true,
						validatePath : true,
					},

				},
				messages : {
					projectName : {
						required : "Please enter Project Name!",
					},
					hostName : {
						required : "Please enter Host Name!",
					},
					projectPath : {
						required : "Please enter Project Path!",
						validatePath:"Invalid Path format!"
					},
				},
				submitHandler : function(form) {
					var formData = $(form).serialize();
					addProject(formData);
				}
			});
			
			$.validator.addMethod("validatePath",function(value, element) {
				var pathPattern = /^\\[\\\S|*\S]?.*$/;
					return pathPattern.test(value);
			});

			$('#EditProject').on('hidden.bs.modal',	function() {
				$(this).find("input,textarea,select").val("");
				$("#AddProjectForm").validate().resetForm();
				$(this).find("select,textarea,input")
				.removeClass("ErrorClass");
			});	
			
			
			$('#AddProject').on('hidden.bs.modal',	function() {
				$(this).find("input,textarea,select").val("");
				$("#EditProjectForm").validate().resetForm();
				$(this).find("select,textarea,input")
				.removeClass("ErrorClass");
			});			

			
			
			
			
			projectMgmt.on('click',"#folder",function() {
				var currentRow = projectMgmt.row($(this).parents("tr"));
				var currentRowData = projectMgmt.row(currentRow).data();
				$('#AddFolder').on('show.bs.modal',function(event) {
					$("#AddFolder #projectId").val(currentRowData.projectId)
					});
				
				getFolderByProject(currentRowData.projectId);
				
			});
			
			
			
			projectMgmt.on('click',"#edit",function() {
				var currentRow = projectMgmt.row($(this).parents("tr"));
				var currentRowData = projectMgmt.row(currentRow).data();
				$('#EditProject').on('show.bs.modal',function(event) {
					$("#EditProjectForm #projectId").val(currentRowData.projectId);
					$("#EditProjectForm #projectName").val(currentRowData.projectName);
					$("#EditProjectForm #hostName").val(currentRowData.hostName);				
					$("#EditProjectForm #projectPath").val(currentRowData.projectPath);
					});
				});
			
			
					// Delete table content
					projectMgmt.on('click',"#delete",function() {
							var currentRow = projectMgmt.row($(this).parents("tr"));
							var currentRowData = projectMgmt.row(currentRow).data();
								$.confirm({
									title : '',
									content : "Do you want to delete the records in the filename  <b>"
												+ currentRowData.projectName+ "</b>?",
									type : 'blue',
									buttons : {
									OK : {
										action : function() {
											removeProject(currentRowData.projectId);
										}
									},
									CANCEL : {},
									}
								});
					});

					$("#EditProjectForm").validate({
						errorClass : "ErrorClass",
						rules : {
							projectName : {
								required : true,
							},
							hostName : {
								required : true,
							},
							projectPath : {
								required : true,
								validatePath:true,
							},

						},
						messages : {
							projectName : {
								required : "Please enter Project Name!",
							},
							hostName : {
								required : "Please enter Host Name!",
							},
							projectPath : {
								required : "Please enter Project Path!",
								validatePath:"Invalid Path format!"
							},
						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							editProjectInfo(formData);
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

function getAllProject() {
	$.ajax({
		url : 'GetAllProject',
		method : 'Post',
		success : function(response) {
			$("#project").empty().append("<option value=''>select project</option>")
			for (var i = 0; i < response.project.length; i++) {
				$("#project").append("<option value='"+response.project[i].projectId +"'>" +response.project[i].projectName +"</option>" )
			}
			drawTable(response.project)
		}
	});
}

function drawTable(data) {
	projectMgmt.clear().draw();
	if (data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			data[i].sNo = i + 1;
			data[i].action = action;
			projectMgmt.row.add(data[i]).draw();
			}
		}
}

function addProject(formData) {
	
	$.ajax({
		url : 'AddNewProject',
		method : 'Post',
		data : formData,
		success : function(response) {
			if (response == "200") {
				$("#AddProject").modal('hide');
				success('Project has been created successfully');
				getAllProject();
			} else if(response == "404"){
				failed('Project already exist!')
			} else if(response == "405") {
				failed('Make sure that the given host name and folder to be shared!')
			}

		}
	});
}

function editProjectInfo(formData) {
	
	$.ajax({
		url : 'UpdateProjectInfo',
		method : 'Post',
		data : formData,
		success : function(response) {
			if (response == "200") {
				$("#EditProject").modal('hide');
				success('Project Info has been updated successfully');
				getAllProject();
			} else if(response == "404"){
				failed('Project already exist!')
			} else if(response == "405") {
				failed('Make sure that the given host name and folder to be shared!')
			}
		}
	});
}

function removeProject(projectId) {
	$.ajax({
		url : 'RemoveProject',
		method : 'Post',
		data : "projectId=" + projectId,
		success : function(response) {
			if(response) {
				success('Project has been removed successfully');
				projectMgmt.row("#" + projectId).remove().draw();
			}else {
				failed('Sever internal Error!')
			}			
			
		}
	});
}



function success(message) {
	$.confirm({
		title : '',
		content : message,
		type : 'green',
		buttons : {
			OK : {}
			},
	});
}

function failed(message) {
	$.confirm({
		title : '',
		content : message,
		type : 'red',
		buttons : {
			OK : {},
		}
	});
}

function pathValidate() {
	$.alert({
		 title: '<b><i class="fa fa-exclamation-triangle"></i> Warning!</b>',
	    content: 'Invalid path format!',
	    autoClose: 'close|800',
	    buttons: {
	    	close: function () {}
	    }
	});
}


function confirmAlert(){
	$.alert({
		 title: '<b><i class="fa fa-exclamation-triangle"></i> Warning!</b>',
	    content: 'Enter the values !',
	    autoClose: 'close|800',
	    buttons: {
	    	close: function () {
	        }
	    }
	});
}



function deleteAlert(){
	$.alert({
		 title: '<b><i class="fa fa-exclamation-triangle"></i> Warning!</b>',
	    content: 'Select the folder you want to delete!',
	    autoClose: 'close|800',
	    buttons: {
	    	close: function () {
	        }
	    }
	});
}

function getFolderByProject(projectId) {
	$.ajax({
		url : 'GetFolderByProject',
		method : 'Post',
		data : "projectId=" + projectId,
		success : function(response) {
			if(counter == 2) {
				$("#folderList").empty();
				for (var i = 0; i < response.length; i++) {
					$("#folderList").append("<option value='"+response[i].id +"'>" +response[i].extensionType+"</option>" )
				}
			} else {
			
			var  viewFolderArray = [];
			$("#folderList").empty();
			for (var i = 0; i < response.length; i++) {
				viewFolderArray[i] = response[i].id;
				$("#folderList").append("<option value='"+response[i].id +"'>" +response[i].extensionType+"</option>" )
			}
			$("#folderList").val(viewFolderArray);
		}
		}
	});
}


function addFolderByProject(formData) {
	$.ajax({
		url : 'AddFolderByProject',
		method : 'Post',
		data : formData,
		success : function(response) {
			if(response) {
				$("#project,#folderName").val('');
				$("#folderList").val(null).change();
				success('Folder has been added successfully!');
			}else {
				failed('Sever internal Error!')
			}
		}
	});
}



function deleteFolder(formData) {
	$.ajax({
		url : 'DeleteFolderFromProject',
		method : 'Post',
		data : formData,
		success : function(response) {
			if(response) {
				$("#project,#folderName").val('');$("#folderList").val(null).change();
				success('Folder has been removed successfully!');
			}else {
				failed('Sever internal Error!')
			}		
		}
	});
}

