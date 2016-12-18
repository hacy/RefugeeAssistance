#Project
RefugeeAssist is for refugees in Turkey. For finding nearest health institutions, schools and vocational training centers. These places can be added by government,sponsors and societies. Take users' latitude and longitude data and show the places where do they need in foreign country. Gather refugees together and show them what they mostly need in Turkey.
Especially for women and children refugees needs hospitals. Older men needs vocational centers to learn how to work in Turkey. I developed the backend side of the project. Backend project and API has been developed which can be used by web client and mobile client projects. 

#Technologies
Spring Boot, Spring Security, Spring MVC
DB: PostgreSQL
Using Liquibase database refactoring
slf4j for logging
Integration Tests and Unit testing to recover API endpoints

#Run Application
PostgreSql
-Create db called 'refugeeassist_master_db'
-Create schema called 'refugee'

After logged in with username and password. Token has been created for 5 min usage. Endpoints can be request with this token.

# API Endpoints
#Base URL
http://localhost:4000/

### Externals:
- /api/v1/login (Login with username password)
- /api/v1/health/{id} GET, PUT, DELETE
- /api/v1/health/ POST, GET
- /api/v1/school/{id} GET, PUT, DELETE
- /api/v1/school/ POST, GET
- /api/v1/voct/{id} GET, PUT, DELETE
- /api/v1/voct/ POST, GET
- /api/v1/city/{id} GET, PUT, DELETE
- /api/v1/city/ POST, GET
- /api/v1/city-district/{id} GET, PUT, DELETE
- /api/v1/city-district/ POST, GET

- /api/v1/

