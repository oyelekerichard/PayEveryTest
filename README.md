# PayEveryTest

Brief Description
This project is a simple Java Spring Boot application to store and view Expenses of a user 

Steps to run the application

1. Pull or download this code from the git repository from
2. Create a schema named payeverydb on PostgreSQL workbench on your local system
3. Open the application-dev.properties file(the dev which describes the profile ith which to run the project), set the db username to “payevery” and password to “123” or you can put your database username and password in these fields.
4. Now you can run your application. The table “expenses” will be automatically created in “payeverydb” schema created before now. 
5. By default, it runs on port 9898. 
6. You can check the documentation on swagger ui from following url: http://localhost:9898/swagger-ui.html
7. Once the db has been set up, we can also run the unit tests.
