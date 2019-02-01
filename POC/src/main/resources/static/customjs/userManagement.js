var userManagement;
var counter = 0;
var action = "<div class='text-center btn-type'>"
		+ "<button type='button' id='edit'   class='btn btn-edit btn-sm' data-toggle='modal' data-target='#editUser' data-toggle='tooltip' title='modify' ><i class='fa fa-edit fa-lg'> </i></button> "
		+ "<button type='button' id='delete' class='btn btn-trash btn-sm' data-toggle='tooltip' title='delete'><i class='fa fa-trash fa-lg'></i></button>  </div> ";

$(document).ready(function() {
			
	$("#view").addClass("crud");
	
	$("#usertab").click(function(){
		$("#projectMapping").addClass("hide");
		$("#userMgmt").removeClass("hide");
	});
	
	$("#projecttab").click(function(){
		$("#projectMapping").removeClass("hide");
		$("#userMgmt").addClass("hide");
	});
	
	
			getAllUser();
			userManagement = $("#userManagement").DataTable({
				'lengthChange' : false,
				 "ordering": false,
				 'rowId' : 'userId',

				columns : [ {
					data : "sNo",
					"width" : "5%"
				}, {
					data : "userName",
					"width" : "15%"
				}, {
					data : "activeStatus",
					"width" : "15%"
				}, {
					data : "role",
					"width" : "15%"
				}, {
					data : "comment",
					"width" : "20%"
				}, {
					data : "action",
					"width" : "15%"
				}, ],
			});

			
		$("#projectMapping").on("change","#user",function(){
			var userId= $(this).val();
			$("#allocateProject").val(null).change();
			$("#serviceName").val(null).change();
			if(userId != "" ) {
				getUserByUserId(userId);	
			} 
		});
		
		$("#reset").click(function(){
			$("#allocateProject").val(null).change();
			$("#serviceName").val(null).change();
		});
		
		$("#submit").click(function(){
			var projectId=$('#allocateProject').val();
			var serviceName=$("#serviceName").val();
			var userId=$("#user").val();
			if(userId != "" && serviceName.length != 0 && projectId != 0) {
			 mapProject("UserId="+userId+ "&ServiceId="+serviceName+"&ProjectId="+projectId);
			}
			else {
				confirmAlert();
			}
		});
		
		
		$("#add").click(function() {
			$(this).addClass("crud")
			$("#delete,#view").removeClass("crud")
			counter=1;
			$("#allocateProject").val(null).change();
			$("#serviceName").val(null).change();
			$("#user").val('')
			$('#allocateProject').prop("disabled",false);
			$("#serviceName").prop("disabled",false);
			$("#submit").prop("disabled",false);
			
		})
			$("#view").click(function() {
			$(this).addClass("crud");
			$("#delete,#add").removeClass("crud")
			counter=3;
			$("#allocateProject").val(null).change();
			$("#serviceName").val(null).change();
			$("#user").val('')
			$('#allocateProject').prop("disabled",true);
			$("#serviceName").prop("disabled",true);
			$("#submit").prop("disabled",true);
			
		})
		
		
		$("#delete").click(function() {
			$(this).addClass("crud")
			$("#view,#add").removeClass("crud")
			counter = 2;
			$("#allocateProject").val(null).change();
			$("#serviceName").val(null).change();
			$("#user").val('')
			$('#allocateProject').prop("disabled",false);
			$("#serviceName").prop("disabled",false);
			$("#submit").prop("disabled",false);
			
		})
		

	$('#addUser').on('hidden.bs.modal',	function() {
		$(this).find("input,textarea,select").val("");
		$("#AddUserForm").validate().resetForm();
		$(this).find("select,textarea,input")
				.removeClass("ErrorClass");
	 });
	
	
	$("#confVisiblePass").click(function() {
		$(this).toggleClass('fa-eye').toggleClass('fa-eye-slash');
		if ($('#confPassword').attr('type') == 'password')
			$('#confPassword').attr('type', 'text')
		else
			$('#confPassword').attr('type', 'password')
	});
	
	
	
	$("#visiblePass").click(function() {
		$(this).toggleClass('fa-eye').toggleClass('fa-eye-slash');
		if ($('#password').attr('type') == 'password')
			$('#password').attr('type', 'text')
		else
			$('#password').attr('type', 'password')
	});
	
	
	$("#EditUserForm #visiblePass").click(function() {
		$(this).toggleClass('fa-eye').toggleClass('fa-eye-slash');
		if ($('#editPassword').attr('type') == 'password')
			$('#editPassword').attr('type', 'text')
		else
			$('#editPassword').attr('type', 'password')
	});

					$("#AddUserForm").validate({
						errorClass : "ErrorClass",
						rules : {
							userName : {
								required : true,
							},
							password : {
								required : true,
							},
							confPassword : {
								required : true,
								 equalTo: "#password"
							},

						},
						messages : {
							userName : {
								required : "Please enter UserName",
							},
							password : {
								required : "Please enter password",
							},
							confPassword : {
								required : "Re-enter password",
							},
						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							addNewUser(formData);
						}
					});

					
					$("#EditUserForm").validate({
						errorClass : "ErrorClass",
						rules : {
							userName : {
								required : true,
							},
							password : {
								required : true,
							},

						},
						messages : {
							userName : {
								required : "Please enter UserName",
							},
							password : {
								required : "Please enter password",
							},
						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							editUserInfo(formData);
						}
					});
					
					
					

					// Update table content
				userManagement.on('click',"#edit",function() {
					var currentRow = userManagement.row($(this).parents("tr"));
					var currentRowData = userManagement.row(currentRow).data();
					$('#editUser').on('show.bs.modal',function(event) {
						$("#EditUserForm #userId").val(currentRowData.userId);
						$("#EditUserForm #editUserName").val(currentRowData.userName);
						$("#EditUserForm #editPassword").val(currentRowData.password).change();
						$("#EditUserForm #editRole").val(currentRowData.role).change();
						$("#EditUserForm #editActive").val(currentRowData.active).change();
						$("#EditUserForm #editComment").val(currentRowData.comment);
						});
					});

					// Delete table content
					userManagement.on('click',"#delete",function() {
							var currentRow = userManagement.row($(this).parents("tr"));
							var currentRowData = userManagement.row(currentRow).data();
								$.confirm({
									title : '',
									content : "Do you want to delete the records in the filename  <b>"
												+ currentRowData.userName+ "</b>?",
									type : 'blue',
									buttons : {
									OK : {
										action : function() {
											removeUser(currentRowData.userId);
										}
									},
									CANCEL : {},
									}
								});
					});

					$("#EditUserForm").validate({
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
							editUserInfo(formData);
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
	userManagement.clear().draw();
	if (data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			data[i].sNo = i + 1;
			data[i].action = action;
			
			if(data[i].active == 1) {
				data[i].activeStatus = '<div class="text-center"><i class="fa fa-check" style="color:green"></i></div>'
			}else {
				data[i].activeStatus = "<div class='text-center'><i class='fa fa-times-circle' style='color:red'></i></div>"
			}
				userManagement.row.add(data[i]).draw();
		}
	}
}

function addNewUser(formData) {
	
	$.ajax({
		url : 'AddNewUser',
		method : 'Post',
		data : formData,
		success : function(response) {
			if (response) {
				$("#addUser").modal('hide');
				success('User has been created successfully');
				getAllUser();
			} else {
				failed('UserName already exist!')
			}

		}
	});
}

function editUserInfo(formData) {
	
	$.ajax({
		url : 'EditUserinfo',
		method : 'Post',
		data : formData,
		success : function(response) {
			if (response) {
				$("#editUser").modal('hide');
				success('User Info has been updated successfully');
				getAllUser();

			} else {
				failed('UserName already exist!')

			}
		}
	});
}

function removeUser(userId) {
	$.ajax({
		url : 'RemoveUser',
		method : 'Post',
		data : "userId=" + userId,
		success : function(response) {
			userManagement.row("#" + userId).remove().draw();
		}
	});
}

function getAllUser() {
	$.ajax({
		url : 'GetAllUser',
		method : 'Post',
		success : function(response) {
			var user = response.user;
			$("#user").empty().append("<option value=''> select user</option>");
			for (var i = 0; i < user.length; i++) {
				$("#user").append("<option value = '"+user[i].userId +"'>"+user[i].userName+"</option>");
			}
			drawTable(user)
		}
	});
}



function getUserByUserId(userId) {
	$.ajax({
		url : 'GetUserProject',
		method : 'Post',
		data : "UserId=" + userId,
		success : function(response) {
			mapProjectVal(response);
			var mappedProject=response.mappedProject;
			
			var project = new Array();
			var service = new Array();
			if(counter == 1) {
				for(var i= 0;i<mappedProject.length;i++) {
					$("#allocateProject").find('[value="'+mappedProject[i].ProjectId+'"]').remove();
			}
			} else if(counter == 2) {
				$("#allocateProject").empty();
				for (var j = 0; j < mappedProject.length; j++) {
					$("#allocateProject").append("<option value = '"+mappedProject[j].ProjectId+"'>"+mappedProject[j].ProjectName+"</option>");
				}
				
			} else  if(counter = 3){
				for(var i= 0;i<mappedProject.length;i++){
					project[i] = mappedProject[i].ProjectId;
					service[i] = mappedProject[i].ServiceId;
				}
				$("#allocateProject").val(project).trigger('change');
				$("#serviceName").val(service).trigger('change');
			}
		}
			
			
			
	});
}



function mapProjectVal(response) {
		var project = response.project;
		var service = response.service;
	
		$("#allocateProject").empty();
		for (var j = 0; j < project.length; j++) {
			$("#allocateProject").append("<option value = '"+project[j].projectId +"'>"+project[j].projectName+"</option>");
			
		}
		$("#serviceName").empty();
		for (var k = 0; k < service.length; k++) {
			$("#serviceName").append("<option value = '"+service[k].serviceId +"'>"+service[k].apiServiceName+"</option>");
			
		}
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

function confirmAlert(){
	$.alert({
		 title: '<b><i class="fa fa-exclamation-triangle"></i> Warning!</b>',
	    content: 'select the Values!',
	    autoClose: 'close|800',
	    buttons: {
	    	close: function () {
	        }
	    }
	});
}

function mapProject(formData){
	var mode = counter == 1 ? "ADD" : counter == 2 ? "DELETE" : "VIEW" ;
	formData = formData +"&Mode="+mode; 
	$.ajax({
		url : 'MapProject',
		method : 'Post',
		data : formData,
		success:function(response) {
			if(mode == "ADD" && response) {
				success("New project has been mapped successfully!") 
				 resetMapForm();
			} else if(mode="DELETE" && response) {
				success("Project has been deleted successfully!")
				 resetMapForm();
			}
			else {
				failed("Internal Sever error!");
			}
			}
		});
}



function resetMapForm () {
	$("#allocateProject").val(null).change();
	 $("#serviceName").val(null).change();
	 $("#user").val('')
	 }


