# Vacation-Module
Take-Home Assessment Tremend

Spring Boot version: v2.4.5
Dependencies: spring-boot-starter-data-jpa
	Spring-boot-starter-web
	Mysql-connector-java
	Lombok
	Spring-boot-starter-test

    
Used MySQL persistence database with MySQL Workbench to keep track of the data.
Tested the functionalities using Postman.

In the packet “controllers” I created “VacationRequestController” where the functions that a user need to operate the system are implemented.

Creating a new vacation request
The function verifies if the start date and the end date of the vacation request body are weekdays or national holidays.
If the dates are ok, then it will count how many days are in the request and see if the user has enough vacation days available.
The request will be marked as pending, waiting for approval, and will be saved.
 
 
 

Modifying an existing vacation request
The modifying request body provides the id of the request needed to be modified and all the functionalities of creating a new vacation request.
If the request was approved before it got modified, the status will be changed back to pending.
 
 
Get all requests
Using the function “findByUserId” from the “VacationRequestService”, we get a list of the vacation requests of the user.
 

Get national holidays
Using the function “findAllByCountry” from the “NationalHolidaysService”, we get a list of the national vacation days from a country.
 

Get vacation days
Using the function “findUserById” from the “UserRepository”, we get the user and we can use the vacation days getter.
 



The “AdminController” has functions for setting the status of a request as approved, can get all requests from all users, and can add national holidays for the current year.

Approve a vacation request
 

Get all vacation requests
 
Adding a national holiday

