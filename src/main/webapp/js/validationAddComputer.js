// When the browser is ready...
$(document)
		.ready(
				function() {
					// Setup form validation on the #register-form element
					$("#formAddComputer")
							.validate(
									{

										// Specify the validation rules
										rules : {
											name : {
												required : true,
												minlength : 3
											},
											introducedDate : {
												required : true,
												date : true,
												dateFormat : 'dd/mm/yyyy'
											},
											discontinuedDate : {
												required : true,
												date : true,
												dateFormat : 'dd/mm/yyyy'
											},
											agree : "required"
										},

										// Specify the validation error messages
										messages : {
											name : {
												required : "Please type the name",
												minlength : "The name contains at least 3 characters"
											},
											introducedDate : {
												required : "Please type the date",
												date : "The format is JJ/MM/AAAA",
												dpCompareDate : "Please type a date after 01/01/1970"
											},
											discontinuedDate : {
												required : "Please type the date",
												date : "The format is JJ/MM/AAAA",
												dpCompareDate : "Please type a date after 01/01/1970"
											}
										},

										submitHandler : function(form) {
											form.submit();
										}
									});
					$.datepicker.setDefaults({
						showOn : 'both',
						buttonImageOnly : true,
						buttonImage : 'img/calendar-green.gif'
					});
					$('#introducedDate').datepicker();
					$('#discontinuedDate').datepicker();
				});
