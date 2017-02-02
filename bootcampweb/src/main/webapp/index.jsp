<html>
<body>
<h2>Welcome!</h2>
<div>Learning Java Web Services</div>
<div>Globant Java Bootcamp 2017</div>
<div>by Santiago Cocco</div>
<div>santi-cocco@hotmail.com</div>
<div>Using IntelliJ Community Edition, Maven, Springframework, cxf jaxrs client and MySQL</div>
<br>
<div>Available services:</div>
<div>Get countries */bootcampweb/country  (Http.GET)</div>
<br>
<div>Get states from IND (India) */bootcampweb/state/IND  (Http.GET)  If no state can be found, local database will be searched</div>
<br>
<div>Add a country to local database */bootcampweb/country/add  (Http.POST, expects application/json)</div>
<div>Example country { "name": "Brazil", "countryID2": "BR", "countryID3": "BRA" } </div>
<br>
<div>Add a state to local database (country must exist beforehand!) */bootcampweb/state/add  (Http.POST, expects application/json)</div>
<div>Example state { "countryID3": "ARG", "name": "Cordoba", "abbreviation": "CO", "area": 268596, "capital": "Cordoba" } </div>
<br>
</body>
</html>
