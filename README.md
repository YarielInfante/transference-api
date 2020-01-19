# transference-api

Functionalities 

- Create account
    - require User info. Ex. Name, email, etc.
    - require account currency type. 
    - Generate secure random account number
- Top up an account
- Transfer money between accounts
    - Save reference
- Check balance
- Transactional history

Quality attributes

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

- JaxRS API - Jersey
- Grizzly server
- Reflection
- H2 database 
- mockito
- Hickori CP