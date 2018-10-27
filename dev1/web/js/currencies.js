var table = document.getElementById("table");
var tbody = table.tBodies[0];
function CBR_XML_Daily_Ru(rates) {
    var currencies = ['USD', 'EUR', 'GBP', 'CHF', 'PLN', 'JPY', 'CAD', 'CNY', 'DKK', 'NOK', 'SEK'];
    currencies.forEach(function (currency) {
       var rate = rates.Valute[currency].Value.toFixed(4);
       tbody.innerHTML += `<tr>
             <td>` + currency+`</td>
             <td>` +  rate + `</td>
             <td>`+  trend(rates.Valute[currency].Value, rates.Valute[currency].Previous) + `</td>
             </tr>`
    });

    function trend(current, previous) {
        if (current > previous) return ' ▲';
        if (current < previous) return ' ▼';
        return '';
    }
}
