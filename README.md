Please use the Readme.txt for usage:

Pre-Requisite:
Files should be placed in project folder structure (records.csv and records.xml)

Running the application,
Run using Postman or SoapUI or Browser for running the Rest service.
http://localhost:8085/customer-api/getStatement?type=xml
http://localhost:8085/customer-api/getStatement?type=csv
http://localhost:8085/v2/api-docs
http://localhost:8085/swagger-ui.html

Output:
Records_processed.xml and Records_processed.csv will be generated accordingly with the files kept in Pre-Requisite.

Validation for CSV and XML:
-	Records with unique reference number and the endBalance value equals to startBalance+mutation will be present in final report 
generated with reference and description.

Note:
Used Techniques for Implementation:
-	SpringBoot and Maven
-   Sonarlint for quality(Attached)
-   Swagger for Rest API's
-	Factory Design Pattern (ParserFactory.java class)
-	Stream, Filter, Map, Collect from Java 8
-	JAXBContext for marshalling and unmarshalling
-	BufferedReader and FileReader for CSV file reading and manipulation