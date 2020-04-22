# Movie Central API.


#### This is an REST API that exposes all the backend services of the global social network for movies named MovieCentral.

To run locally you need to install and start a Neo4j locally and configure a user. 
To install and start a neo4j in a MAC you can run the following commands:

```
brew install neo4j
neo4j start
curl -v -u neo4j:neo4j POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}"
```


Then you can just run a maven clean install and run the project locally. The tests are running as part of the maven build.

```
mvn clean install
java -jar target/movie-central-api-0.0.1-SNAPSHOT.jar
```


Once you start the application you can enter to the swagger UI where you can find all the published services.

```
http://localhost:8080/moviecentral/swagger-ui.html#/
```


Here are a few operations you can run:

To create a new movie you can run:

```
curl --location --request POST 'localhost:8080/moviecentral/movie' \
--header 'Content-Type: application/json' \
--data-raw '{
  "actors": [
{"firstName": "Bruce","lastName": "Willis"},
{"firstName": "Jason","lastName": "Bourne"}
  ],
  "date": "1998-06-01",
  "title": "Hard to Kill"
}
'
```

To add an actor to an existing movie:

````
curl --location --request POST 'localhost:8080/moviecentral/movie/{MOVIE_ID}/addActors' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "New Actor",
  "lastName": "Lastname"
}'
````
Note: Replace MOVIE_ID with the id of the movie you want to add the actor.

To search movies by an actor you can run:

```
curl --location --request GET 'localhost:8080/moviecentral/movie/byactor/{ACTOR_ID}'
```

Note: Replace ACTOR_ID with the id of the actor you are searching.
