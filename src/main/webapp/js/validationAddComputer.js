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
												required : "Veuillez entrer le nom de l'ordinateur",
												minlength : "Le nom doit avoir au moins 3 caractères"
											},
											introducedDate : {
												required : "Veuillez entrer la date où le produit a été introduit",
												date : "Veuillez entrer la date au format JJ/MM/AAAA",
												dpCompareDate : "Veuillez entrer une date postérieur à 1970"
											},
											discontinuedDate : {
												required : "Veuillez entrer la date d'abandon",
												date : "Veuillez entrer la date au format JJ/MM/AAAA",
												dpCompareDate : "Veuillez entrer une date postérieur à 1970"
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
