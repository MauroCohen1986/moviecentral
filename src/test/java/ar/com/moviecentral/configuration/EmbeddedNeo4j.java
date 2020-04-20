package ar.com.moviecentral.configuration;

import org.testcontainers.containers.Neo4jContainer;

public class EmbeddedNeo4j {


  private static Neo4jContainer dockerContainer =null;

  private EmbeddedNeo4j(){}

  public static Neo4jContainer getInstance(){
    if(dockerContainer ==null){
      initializeInstance();
    }
    return dockerContainer;
  }

  private static void initializeInstance() {
    dockerContainer = new Neo4jContainer().withAdminPassword("admin");
    dockerContainer.start();
  }

}
