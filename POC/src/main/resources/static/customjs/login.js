$(document).ready(function() {
	$("#login").validate({
		errorClass : "error",
		rules : {
			identityName : "required",
			watchWord : "required",
		},
		submitHandler : function(form) {
			validateUser(form);
		}
	})

});

function validateUser(form) {
	var formData = $("#login").serialize();
	$.ajax({
		url : "ValidateUser",
		method : "POST",
		data : formData,
		success : function(response) {
			if (response == "555") {
				form.submit();
			} else if (response == "444") {
				logMessage("Couldn't login until change active status !")
			} else if (response == "333") {
				logMessage("Invalid Credntials!")
			} else if (response == "222") {
				logMessage("Server Internal Problem!")
			}

		}
	});

}

function logMessage(message) {
	$.confirm({
		title : '',
		content : message,
		type : 'red',
		buttons : {
			OK : {},
		}
	});
}