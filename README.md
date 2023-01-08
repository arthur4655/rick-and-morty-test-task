# Rick and Morty test task
## 📝Project description
This project uses http-requests to
<a href="https://rickandmortyapi.com/documentation/#rest">Rick and Morty API</a>
to collect data about characters from series and save to local database. 
Project provides two methods:<br>
1. The request randomly fetch from local db info about one character in the universe
the animated series Rick & Morty.
2. The request takes a string as an argument, and returns a list of all
characters whose name contains the search string On a regular basis,
the web application downloads data from a third-party service to the 
internal database. Implemented API requests must work with a local
database (i.e. fetch data from a database).
## 🔎Endpoints description
* `GET: /rick-and-morty-characters/random` - to get info about one character
in the universe the animated series Rick & Morty.
* `GET: /rick-and-morty-characters/by-name` - Get a list of characters
from Rick and Morty series whose names contain the entered string.
## 🌐Technologies
* JDK 17
* Maven 4.0
* Spring and Spring Boot 3.0.1
* PostgreSQL 14.6
* Swagger 3.0.0
* Liquibase 4.17.2
* Docker 20.10.21
## 🚀Instructions for launching the project
1. Clone project from github.
2. Make sure you have installed docker on your PC
3. Run
```bash
docker-compose up
```
