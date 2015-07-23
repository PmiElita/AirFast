function rePriceBaggage(baggage) {

	var priceSpan = document.getElementById("_price");
	var price = parseFloat(document.getElementById("_inPrice").value);

	if (baggage.value == "") {
		baggage.value = "0";
	}
	var b = parseFloat(baggage.value);
    if (b>0){
	priceSpan.innerText = (b * 1.25 * price + price).toString();
    }
}

function primeChange(check) {
	if (check.checked) {
		document.getElementById("_price").innerText = (parseFloat(document.getElementById("_price").innerText) + parseFloat(document
				.getElementById("_inPrime").value)).toString();
	} else {
		document.getElementById("_price").innerText = (parseFloat(document.getElementById("_price").innerText) - parseFloat(document
				.getElementById("_inPrime").value)).toString();
	}
}

