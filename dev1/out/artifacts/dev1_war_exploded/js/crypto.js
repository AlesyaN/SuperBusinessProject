var table = document.getElementById("table");
var tbody = table.tBodies[0];
var currencies = ['BTC', 'ETH', 'XRP', 'BCH', 'EOS', 'XLM', 'LTC', 'USDT', 'ADA', 'XMR', 'TRX'];
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

