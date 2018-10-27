var table = document.getElementById("table");
var tbody = table.tBodies[0];
var request1 = "https://min-api.cryptocompare.com/data/price?fsym=";
var request2 = "&tsyms=USD,EUR";
var currencies = ['BTC', 'ETH', 'XRP', 'BCH', 'EOS', 'XLM', 'LTC', 'USDT', 'ADA', 'XMR', 'TRX', 'MIOTA'];
var index = 0;
currencies.forEach(function (currency) {
    $.getJSON("https://min-api.cryptocompare.com/data/price?fsym=" + currency +"&tsyms=USD,EUR",
        function(results) {
            tbody.innerHTML += `<tr>
            <td>` + currency+`</td>
            <td>` +  results.USD + `</td>
            <td>`+  results.EUR + `</td>
            </tr>`;
        });
});

