package ar.com.moviecentral.configuration;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.Neo4jContainer;

public class SpringBootNeo4JTestConfiguration implements
    ApplicationContextInitializer<ConfigurableApplicationContext> {

  private Neo4jContainer instance = EmbeddedNeo4j.getInstance();

  public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
    TestPropertyValues.of(
        "spring.data.neo4j.username=neo4j",
        "spring.data.neo4j.password=admin",
        "spring.data.neo4j.uri="+instance.getBoltUrl()
    ).applyTo(configurableApplicationContext.getEnvironment());
  }

}
