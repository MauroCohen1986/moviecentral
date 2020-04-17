package ar.com.moviecentral.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@NoArgsConstructor
public class Actor {

  @Id
  @GeneratedValue
  private Long id;

  private String firstName;
  private String lastName;

}
