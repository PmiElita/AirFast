/**
 * 
 */
function loadCityFrom(select)
{ 

   var citySel = document.getElementById("_cityFrom");
   $.ajax({
	url:'reloadCity',
	type: 'get',
	dataType: 'json',
	data: {"selected" : getSelectedOption (select).value},
	 complete : function(data) {
            citySel.length = 1;
		    for (var i = 0; i<data.responseJSON.length ; i++) {
		        citySel.options[i+1] = new Option(data.responseJSON[i]["text"],data.responseJSON[i]["value"]);
		    }
		   }
});
   var airportSel = document.getElementById("_airportFrom");
   $.ajax({
		url:'reloadAirports',
		type: 'post',
		dataType: 'json',
		data: {"selected" : getSelectedOption (select).value},
		 complete : function(data) {
	            airportSel.length = 1;
			    for (var i = 0; i<data.responseJSON.length ; i++) {
			        airportSel.options[i+1] = new Option(data.responseJSON[i]["text"],data.responseJSON[i]["value"]);
			    }
			   }
	});
}

function loadAirportFrom(select)
{ 

	var countrySel = document.getElementById("_countryFrom");
   var airportSel = document.getElementById("_airportFrom");

   $.ajax({
	url:'reloadAirports',
	type: 'get',
	dataType: 'json',
	data: {"selectedCity" : getSelectedOption (select).value,"selectedCountry" : getSelectedOption(countrySel).value }, // передати 2 параметра!!!!!!!
	 complete : function(data) {
	
            airportSel.length = 1;
		    for (var i = 0; i<data.responseJSON.length ; i++) {
		        airportSel.options[i+1] = new Option(data.responseJSON[i]["text"],data.responseJSON[i]["value"]);
		    }
		   }
});
   
}


function loadCityTo(select)
{ 

   var citySel = document.getElementById("_cityTo");
   $.ajax({
	url:'reloadCity',
	type: 'get',
	dataType: 'json',
	data: {"selected" : getSelectedOption (select).value},
	 complete : function(data) {
            citySel.length = 1;
		    for (var i = 0; i<data.responseJSON.length ; i++) {
		        citySel.options[i+1] = new Option(data.responseJSON[i]["text"],data.responseJSON[i]["value"]);
		    }
		   }
});
   var airportSel = document.getElementById("_airportTo");
   $.ajax({
		url:'reloadAirports',
		type: 'post',
		dataType: 'json',
		data: {"selected" : getSelectedOption (select).value},
		 complete : function(data) {
	            airportSel.length = 1;
			    for (var i = 0; i<data.responseJSON.length ; i++) {
			        airportSel.options[i+1] = new Option(data.responseJSON[i]["text"],data.responseJSON[i]["value"]);
			    }
			   }
	});
}

function loadAirportTo(select)
{ 

	var countrySel = document.getElementById("_countryTo");
   var airportSel = document.getElementById("_airportTo");

   $.ajax({
	url:'reloadAirports',
	type: 'get',
	dataType: 'json',
	data: {"selectedCity" : getSelectedOption (select).value,"selectedCountry" : getSelectedOption(countrySel).value }, // передати 2 параметра!!!!!!!
	 complete : function(data) {
	
            airportSel.length = 1;
		    for (var i = 0; i<data.responseJSON.length ; i++) {
		        airportSel.options[i+1] = new Option(data.responseJSON[i]["text"],data.responseJSON[i]["value"]);
		    }
		   }
});
   
}

function getSelectedOption (oListbox)
{

  for (var i=0; i < oListbox.options.length; i++)
  {
      if (oListbox.options[i].selected)  return oListbox.options[i];
  }
  return -1;
};
