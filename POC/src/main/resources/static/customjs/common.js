/*var reportTable;

var action = "<div class='text-center'>"
		+ "<span class='fa fa-edit fa-lg' id='edit' data-toggle='tooltip' title='edit'> </span>  "
		+ "<span id='delete' class='fa fa-trash fa-lg' data-toggle='tooltip' title='delete'> </span>  "
		+ "<span class='fa fa-play fa-lg' id='play' data-toggle='modal' data-target='#audioPlayer' data-toggle='tooltip' title='play audio'> </span>  </div>";

$(document).ready(
		function() {
			reportTable = $("#reportTable").DataTable({
				"searching" : false,
				'lengthChange' : false,
				'sorting' : false,
				'rowId' : 'id',

				columns : [ {
					data : "sNo"
				}, {
					data : "viewData"
				}, {
					data : "audioType"
				}, {
					data : "language"
				}, {
					data : "voiceName"
				}, {
					data : "gender"
				}, {
					data : "fileName"
				}, {
					data : "action"
				} ],
			});

			$("#reset").click(function() {
				reportTable.clear().draw();
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
					if (formCounter > 0) {
						filter(formData);
					} else {
						alert('Enter values');
					}

				}
			});
			reportTable.on('click', "#viewText", function() {
				var currentRow = reportTable.row($(this).parents("tr"));
				var currentRowData = reportTable.row(currentRow).data();
				$("#viewAudioContent #audioContent").html(
						currentRowData.audioInput)

			});

			reportTable.on('click', "#delete", function() {
				var currentRow = reportTable.row($(this).parents("tr"));
				var currentRowData = reportTable.row(currentRow).data();
				alertify.reset().confirm(
						"Do you want Delete <b>" + currentRowData.fileName
								+ "</b>?", function() {
							deleteAudioRecord(currentRowData.id);
						}, function() {
							// log("File download cancelled!");
						});

			});

			reportTable.on('click', "#play", function() {
				var currentRow = reportTable.row($(this).parents("tr"));
				var rowData = reportTable.row(currentRow).data();

				$.ajax({
					url : "FileExist",
					method : "POST",
					data : "FileName=" + rowData.fileName + '.'
							+ rowData.audioType,
					success : function(response) {
						if (response) {
							var html = "<audio controls  controlsList='nodownload' src='PlayAudio?fileName=" + rowData.fileName+ "." + rowData.audioType+"'></audio>";
							$("#audioPlayer #isExist").html(html);
						} else {
							$("#audioPlayer #isExist").html("<div class='not-found'> <i class='fa fa-exclamation-circle'></i> Audio doesn't exist </div>")						}
					}

				});
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
			data[i].viewData = "<div class='text-center'><span class='fa fa-eye fa-lg' id='viewText' data-toggle='modal' data-target='#viewAudioContent'> </span></div>"
			reportTable.row.add(data[i]).draw();
		}
	}
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

$("#filter").click(function() {
	$(".filter").slideToggle();
});*/