# Movie Central API.


#### This is an API rest that exposes all the backend services of the global social network for movies named MovieCentral.

To run locally you need to start a Neo4j locally and configure a user.

```
neo4j start
curl -v -u neo4j:neo4j POST localhost:7474/user/neo4j/password -H "Content-type:application/json" -d "{\"password\":\"secret\"}"
```


Then you can just run a maven clean install and run the project locally.

```
mvn clean install
java -jar target/movie-central-api-0.0.1-SNAPSHOT.jar
```


Once you start the application you can enter to the swagger UI where you can find all the published services.

```
http://localhost:8080/moviecentral/swagger-ui.html#/
```


