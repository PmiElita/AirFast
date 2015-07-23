function editCountry() {
	var selectCountry = document.getElementById("_country");
	var selectedValue = getSelectedOption(selectCountry).text;
	var countryInput = document.getElementById("countryName");
	countryInput.value = selectedValue;
	document.getElementById("countryId").value = getSelectedOption(selectCountry).value;
	$("#editForm").slideDown("slow");

}

function editCity() {

	$
			.ajax({
				url : 'getCountry',
				type : 'get',
				dataType : 'json',
				data : {
					"cityId" : getSelectedOption(document
							.getElementById("_city")).value
				},
				complete : function(data) {

					var selectCity = document.getElementById("_city");
					var selectedValue = getSelectedOption(selectCity).text;
					var selectCountry = document.getElementById("_country");
					var selectedCountry;
					selectedCountry = data["responseJSON"]["countryId"];
					setSelectedOption(selectCountry, selectedCountry);
					var cityInput = document.getElementById("cityName");
					cityInput.value = selectedValue;
					document.getElementById("cityId").value = getSelectedOption(selectCity).value;

					$("#editForm").slideDown("slow");

				}
			});

}

function editAirport() {

	$
			.ajax({
				url : 'getCity',
				type : 'get',
				dataType : 'json',
				data : {
					"airportId" : getSelectedOption(document
							.getElementById("_airport")).value
				},
				complete : function(data) {

					var selectAirport = document.getElementById("_airport");
					var selectedValue = getSelectedOption(selectAirport).text;

					var selectCity = document.getElementById("_city");
					var selectedCity;

					selectedCity = data["responseJSON"]["cityId"];

					setSelectedOption(selectCity, selectedCity);

					var airportInput = document.getElementById("airportName");

					airportInput.value = selectedValue;
					document.getElementById("airportId").value = getSelectedOption(selectAirport).value;

					$("#editForm").slideDown("slow");

				}
			});

}

function getSelectedOption(oListbox) {

	for (var i = 0; i < oListbox.options.length; i++) {
		if (oListbox.options[i].selected)
			return oListbox.options[i];
	}
	return -1;
};

function setSelectedOption(select, id) {
	for (var i = 0; i < select.options.length; i++) {
		if (select.options[i].value == id) {
			select.options[i].selected = true;
		}
	}
}