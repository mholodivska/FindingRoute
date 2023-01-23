This application is created to calculate any possible land route from one country to another.
The objective is to take a list of country data in JSON format and calculate the route by utilizing individual
countries border information.

To launch the app run:
1) mvn clean install
2) mvn exec:java -Dexec.mainClass="com.example.findingroute.FindingRouteApplication"

Then you will be able to do a GET request to the endpoint http://localhost:8080/routing/origin/destination
(e.g. http://localhost:8080/routing/UKR/ITA). You can do it via Postman or your console (run
"curl http://localhost:8080/routing/UKR/ITA"). You will get either response (e.g. [UKR, HUN, AUT, ITA]) or an error
response. Also, you will get an error response if you enter wrong country code.