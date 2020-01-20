# transference-api

Functionalities
---

- Create account
- Top up an account
- Transfer money between accounts

Quality attributes
---

- Performance
    - It takes less than 2 second to complete a transaction 
- Usability
    - All customer’s inputs are validated
    - Feedback is given whenever something happens, either successfully or failure 
- Interoperability  
    - consistency 
        - Data viewed must be consistent
    - Publish API spec


Tech stack
---

- JaxRS API - Jersey
- Grizzly server
- H2 database 
- Junit
- Hickori CP
- logback


**Data Model**


![alt text](https://github.com/YarielInfante/transference-api/blob/development/revolut.png)



   
Definition of endpoint
---
There is a .json archive at /transference-api/postman_collection that can be easily imported into postman (api client tool. Download it at https://www.getpostman.com) where you can find 
all endpoint's definitions.  


Program Execution
----
System prerequisite:
- Java 10+
- Maven 3.6.0

To make all these requests possible, I used:

- **Java 10**: to handle the main code development.
- **Implementation of JaxRs API called Jersey**: a lightweight Framework for creating Restful API.
- **H2 Database**: relational database management system written in Java To store the information requested.
- **Maven**: To download dependencies and compile the project.

Project configuration
  
**application.properties** file has the program default configuration parameters that are:

**Connection String.** 

        dataSourceUrl=jdbc:h2:mem:trans;INIT=RUNSCRIPT FROM 'classpath:data.sql';
        dataSource.user=sa
        dataSource.password=password
        
**Application properties**

        server.http.port=8080
        server.http.path=/api/v1
        
             

This program is designed to run and create the whole schema.

**There are different tables for storing information such as:**

     - customer
     - account
     - transaction_type
     - journal
     - transaction
     

The file named **data.sql** contains schema sql creation


Compile source code
----

To compile source code run :

        mvn clena package
        
To run compiled code compiled run :
        
        unzip transference-1.0-SNAPSHOT.zip
        Then
        java -cp "transference-1.0-SNAPSHOT.jar:lib/*" com.revolut.transference.TransferenceApi
          
        
Tests
---

Integration tests were developed using :
  
       - JUnit
       - Jersey-test-framework
            