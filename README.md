# user-managment

(*)This project was generated using gradle 7.3.3 and JAVA 11

## Quick Start & Documentation

 - Make sure you must intall JAVA version 11 <br>
 - Check documentation image "NVC user managment.jpg"

## Data base script for postgres DB into this folder
  - db-script.sql

## Generate an application
 - Open a linux console for example hyper or gitbash
 - Run `./gradlew clean build` to generate a jar file and build app and running JUnit test.
 
## Run Application
- Run `./gradlew bootRun --args='--PORT=8191 --DATABASE_HOST=localhost --DATABASE_PORT=5432 --DATABASE_NAME=postgres --DATABASE_USER=postgres --DATABASE_PASSWORD=postgres'`
    ****Changing or removing the parameter values that you need for DATABASE_HOST change local for corresping IP address

- **Or
- Run `./gradlew clean build`
- Run `java -Dserver.port=8191 -Dspring.datasource.url=jdbc:postgresql://localhost:5432/user_management_db  -Dspring.datasource.username=postgres -Dspring.datasource.password=postgres -jar build/libs/user-managment-0.0.1-SNAPSHOT.jar`
(****)Changing or removing the parameter values that you need 


## Testing endpoints
****Use the following curl messages
- ****Getting list of users

 curl -X GET \
  http://localhost:8191/api/v1/user/ \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' 
  
  
-- ****Creating Users

  curl -X POST \
  http://localhost:8191/api/v1/user/ \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '  {
        "email": "test@gmail.com",
        "firstName": "abc",
        "lastName": "abc"
    }'
-- ****Validating if mail exist	

curl -X GET \
  'http://localhost:8191/api/v1/user/validate-email/?email=test%40gmail.com' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
