var pocMenu;

var action = "<div class='text-center btn-type'>"
		+ "<button type='button' id='edit'   class='btn btn-edit btn-sm' data-toggle='modal' data-target='#editMenu' data-toggle='tooltip' title='modify' ><i class='fa fa-edit fa-lg'> </i></button> </div>"
/*
 * "<button type='button' id='delete' class='btn btn-trash btn-sm'
 * data-toggle='tooltip' title='delete'><i class='fa fa-trash fa-lg'></i></button>
 * </div> "
 */;

$(document)
		.ready(
				function() {
					getAllMenu();

					pocMenu = $("#pocMenu").DataTable({
						'lengthChange' : false,
						'sorting' : false,
						'rowId' : 'serviceId',

						columns : [ {
							data : "sNo",
							"width" : "5%"
						}, {
							data : "apiServiceName",
							"width" : "15%"
						}, {
							data : "languageMenu",
							"width" : "15%"
						}, {
							data : "voiceNameMenu",
							"width" : "15%"
						}, {
							data : "genderMenu",
							"width" : "20%"
						}, {
							data : "audiotypeMenu",
							"width" : "20%"
						}, {
							data : "action",
							"width" : "15%"
						}, ],
					});

					$('#addService').on('hidden.bs.modal', function() {
						// $("#audiotypeMenuAdd").val(null).change();
						// $("#genderMenuAdd").val(null).change();
						// $("#voiceNameMenuAdd").val(null).change();
						$("#languageMenu").val("en-us");
						$(this).find("#apiServiceName,select").val("");
						$("#AddServiceForm").validate().resetForm();
						$(this).find("select,input").removeClass("ErrorClass");

					});

					$("#AddServiceForm").validate({
						errorClass : "ErrorClass",
						rules : {
							apiServiceName : {
								required : true,
							},
							languageMenu : {
								required : true,
							},
							audiotypeMenu : {
								required : true,
							},
							voiceNameMenu : {
								required : true,
							},
							genderMenu : {
								required : true,
							},

						},
						messages : {
							apiServiceName : {
								required : "Please enter ApiServiceName",
							},
							languageMenu : {
								required : "Please enter LanguageMenus",
							},
							audioTypeMenu : {
								required : "Please enter AudioTypeMenus",
							},
							voiceNameMenu : {
								required : "Please enter VoiceNameMenus",
							},
							genderMenu : {
								required : "Please enter GenderMenus",
							},
						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							addNewService(formData);
						}
					});

					// Update table content
					pocMenu
							.on(
									'click',
									"#edit",
									function() {
										var currentRow = pocMenu.row($(this)
												.parents("tr"));
										var currentRowData = pocMenu.row(
												currentRow).data();
										var genderNames = [];
										var audioTypeNames = [];
										var voiceNames = [];
										var languageNames = [];
										var audioTypeArr = new Array();
										var voiceNamesArr = new Array();
										var genderNamesArr = new Array();
										var languageNamesArr = new Array();
										$('#editMenu')
												.on(
														'show.bs.modal',
														function(event) {

															$(
																	"#editMenu  #serviceId")
																	.val(
																			currentRowData.serviceId);
															$(
																	"#editMenu  #apiServiceName")
																	.val(
																			currentRowData.apiServiceName);

															languageNames = currentRowData.languageMenu
																	.split(",");
															for (var j = 0; j < languageNames.length; j++) {
																languageNamesArr[j] = languageNames[j];

															}
															$(
																	"#editMenu #languageMenuSelect")
																	.val(
																			languageNamesArr)
																	.trigger(
																			'change');

															audioTypeNames = currentRowData.audiotypeMenu
																	.split(",");
															// $("#audiotypeMenuSelect").empty();
															for (var j = 0; j < audioTypeNames.length; j++) {
																// $("#audiotypeMenuSelect").append("<option
																// value="+audioTypeNames[j]+">"+audioTypeNames[j]+"</option>");
																audioTypeArr[j] = audioTypeNames[j];
															}
															$(
																	"#editMenu #audiotypeMenuSelect")
																	.val(
																			audioTypeArr)
																	.trigger(
																			'change');

															voiceNames = currentRowData.voiceNameMenu
																	.split(",");
															// $("#voiceNameMenuSelect").empty();
															for (var j = 0; j < voiceNames.length; j++) {
																// $("#voiceNameMenuSelect").append("<option
																// value="+voiceNames[j]+">"+voiceNames[j]+"</option>");
																voiceNamesArr[j] = voiceNames[j];
															}
															$(
																	"#editMenu #voiceNameMenuSelect")
																	.val(
																			voiceNamesArr)
																	.trigger(
																			'change');

															genderNames = currentRowData.genderMenu
																	.split(",");
															// $("#genderMenuSelect").empty();
															for (var j = 0; j < genderNames.length; j++) {
																// $("#genderMenuSelect").append("<option
																// value="+genderNames[j]+">"+genderNames[j]+"</option>");
																genderNamesArr[j] = genderNames[j];
															}
															$(
																	"#editMenu #genderMenuSelect")
																	.val(
																			genderNamesArr)
																	.trigger(
																			'change');

														});

									});

					// Delete table content
					pocMenu
							.on(
									'click',
									"#delete",
									function() {
										var currentRow = pocMenu.row($(this)
												.parents("tr"));
										var currentRowData = pocMenu.row(
												currentRow).data();
										$
												.confirm({
													title : '',
													content : "Do you want to delete the records in the service  <b>"
															+ currentRowData.apiServiceName
															+ "</b>?",
													type : 'blue',
													buttons : {
														OK : {
															action : function() {
																deleteServiceRecord(currentRowData.serviceId);
															}
														},
														CANCEL : {

														},
													}
												});

									});

					$("#EditMenuForm").validate({
						errorClass : "ErrorClass",
						rules : {
							apiServiceName : {
								required : true,
							},
							languageMenu : {
								required : true,
							},
							audiotypeMenu : {
								required : true,
							},
							voiceNameMenu : {
								required : true,
							},
							genderMenu : {
								required : true,
							},

						},
						submitHandler : function(form) {
							var formData = $(form).serialize();
							editMenuInfo(formData);
						}
					});
				});

function drawMenuTable(data) {
	pocMenu.clear().draw();
	if (data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			data[i].sNo = i + 1;
			data[i].action = action;
			pocMenu.row.add(data[i]).draw();
		}
	}
}

function addNewService(formData) {

	$.ajax({
		url : 'AddNewService',
		method : 'Post',
		data : formData,
		success : function(response) {
			if (response) {
				$("#addService").modal('hide');
				success('New Service has been created successfully');
				getAllMenu();
			} else {
				failed('ApiServiceName already exist!')
			}
		}
	});
}

function editMenuInfo(formData) {
	console.log(formData);
	$.ajax({
		url : 'EditMenuinfo',
		method : 'Post',
		data : formData,
		success : function(response) {
			if (response) {
				$("#editMenu").modal('hide');
				success('Menu Info has been updated successfully');
				getAllMenu();

			} else {
				failed('ApiServiceName already exist!')

			}
		}
	});
}

function deleteServiceRecord(serviceId) {
	$.ajax({
		url : 'DeleteServiceRecord',
		method : 'Post',
		data : "ServiceId=" + serviceId,
		success : function(response) {
			reportTable.row("#" + audioId).remove().draw();
		}
	});
}

function getAllMenu() {
	$.ajax({
		url : 'GetAllMenu',
		method : 'Post',
		success : function(response) {
			drawMenuTable(response.data)
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
