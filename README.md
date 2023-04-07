# Vacation-Module

Spring Boot version: v2.4.5
Dependencies: spring-boot-starter-data-jpa
	Spring-boot-starter-web
	Mysql-connector-java
	Lombok
	Spring-boot-starter-test
    
Used MySQL persistence database with MySQL Workbench to keep track of the data. The database is called vacationmodule. 
Tested the functionalities using Postman. I added two Postman collections where functionalities can be tested.

In the packet “controllers” I created “VacationRequestController” where the functions that a user need to operate the system are implemented.

# Creating a new vacation request
The function verifies if the start date and the end date of the vacation request body are weekdays or national holidays. 
If the dates are ok, then it will count how many days are in the request and see if the user has enough vacation days available.
The request will be marked as pending, waiting for approval, and will be saved.
 
 ![CreateVacationRequest](https://user-images.githubusercontent.com/118290042/227799742-6b2d973c-6b2b-4da6-8969-38a8b4068f40.png)
 ![CreateVacationRequest2](https://user-images.githubusercontent.com/118290042/227799833-6b71f7b5-f15c-4be3-b542-d2001187221c.png)
 ![MYSQLCreateVacationRequest](https://user-images.githubusercontent.com/118290042/227799854-3e951a26-decf-4bf5-b5d5-7600e7af6cd2.png)

# Modifying an existing vacation request
The modifying request body provides the id of the request needed to be modified and all the functionalities of creating a new vacation request.
If the request was approved before it got modified, the status will be changed back to pending.

![ModifyVacationRequest](https://user-images.githubusercontent.com/118290042/227799909-738b23b0-d9ca-41e5-a742-355c4468159f.png)
![SQLModifyVacationRequest](https://user-images.githubusercontent.com/118290042/227799914-f9569fae-4a8c-4461-a32f-4c43d1277807.png)
 
# Get all user requests
Using the function “findByUserId” from the “VacationRequestService”, we get a list of the vacation requests of the user.
 
 ![GetAllUserRequests](https://user-images.githubusercontent.com/118290042/227799953-9f5d5a09-0b38-4d5a-9dd3-91dffd419e89.png)
 
# Get national holidays
Using the function “findAllByCountry” from the “NationalHolidaysService”, we get a list of the national vacation days from a country.
 
 ![Get all holidays](https://user-images.githubusercontent.com/118290042/227800032-690f37f8-dfd6-4993-981d-cdaf1862b22b.png)

# Get vacation days
Using the function “findUserById” from the “UserRepository”, we get the user and we can use the vacation days getter.
 
![get vacation days](https://user-images.githubusercontent.com/118290042/227800097-dd630e3a-c22c-43a5-898d-51dc417a3368.png)


The “AdminController” has functions for setting the status of a request as approved, can get all requests from all users, and can add national holidays for the current year.

# Approve a vacation request
![approve a vacation request](https://user-images.githubusercontent.com/118290042/227800206-456a3149-0be4-41a6-91d0-ee1c8df65d29.png)

# Get all vacation requests
![get all vacation requests](https://user-images.githubusercontent.com/118290042/227800189-fc22c07b-6476-43e3-ae20-f02504f5d7cd.png)

# Adding a national holiday
![add a national holiday](https://user-images.githubusercontent.com/118290042/227800174-f532a3df-1e3c-40e0-bf83-b3ab93facbec.png)

