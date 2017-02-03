# Bootcamp-Cocco
Project for the Globant Java bootcamp 2017 by Santiago Cocco
Using IntelliJ Community Edition, Maven, Springframework, cxf jaxrs client and MySQL

Available services:
Get countries from WebService */bootcampweb/country  (Http.GET) 

Get states from IND (India) */bootcampweb/state/IND  (Http.GET)  (IND = 3 letters Country abbreviation) If no state can be found from WebService, local database will be searched.

Get weather data from Cordoba AR */bootcampweb/weather/AR/Cordoba  (Http.GET)  (AR = 2 letters Country abbreviation, Cordoba = State Capital)  If WebService fails (common issue), local database will be searched. 

Add a country to local database */bootcampweb/country/add  (Http.POST, expects application/json) 
Example country { "name": "Brazil", "countryID2": "BR", "countryID3": "BRA" } 

Add a state to local database (country must exist beforehand!) */bootcampweb/state/add  (Http.POST, expects application/json) 
Example state { "countryID3": "ARG", "name": "Cordoba", "abbreviation": "CO", "area": 268596, "capital": "Cordoba" } 

Add TX capital (Austin) USA weather data to local database (state must exist beforehand!) */bootcampweb/weather/add/US/Austin (Http.POST, expects application/json) 
Example weather data { "todayWeather": { "date": "2017-01-19", "temperature": 60, "description": "Today's weather for Texas, USA" }, "wind": { "windSpeed": 50, "windDirection": 40 }, "atmosphere": { "humidity": 30, "pressure": 20, "visibility": 10, "rising": 9 }, "forecasts": [ { "date": "2017-01-19", "day": "jueves", "high": 60, "low": 40, "text": "Weather for Texas at Thu Jan 19 12:42:36 ART 2017" }, { "date": "2017-01-20", "day": "viernes", "high": 61, "low": 41, "text": "Weather for Texas at Fri Jan 20 12:42:36 ART 2017" }, { "date": "2017-01-21", "day": "sábado", "high": 62, "low": 42, "text": "Weather for Texas at Sat Jan 21 12:42:36 ART 2017" }, { "date": "2017-01-22", "day": "domingo", "high": 63, "low": 43, "text": "Weather for Texas at Sun Jan 22 12:42:36 ART 2017" }, { "date": "2017-01-23", "day": "lunes", "high": 64, "low": 44, "text": "Weather for Texas at Mon Jan 23 12:42:36 ART 2017" }, { "date": "2017-01-24", "day": "martes", "high": 65, "low": 45, "text": "Weather for Texas at Tue Jan 24 12:42:36 ART 2017" }, { "date": "2017-01-25", "day": "miércoles", "high": 66, "low": 46, "text": "Weather for Texas at Wed Jan 25 12:42:36 ART 2017" }, { "date": "2017-01-26", "day": "jueves", "high": 67, "low": 47, "text": "Weather for Texas at Thu Jan 26 12:42:36 ART 2017" }, { "date": "2017-01-27", "day": "viernes", "high": 68, "low": 48, "text": "Weather for Texas at Fri Jan 27 12:42:36 ART 2017" }, { "date": "2017-01-28", "day": "sábado", "high": 69, "low": 49, "text": "Weather for Texas at Sat Jan 28 12:42:36 ART 2017" } ] } 
