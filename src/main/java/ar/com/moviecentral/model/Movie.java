package ar.com.moviecentral.model;

import com.google.common.collect.Sets;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
@NoArgsConstructor
public class Movie {

  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private LocalDate date;

  @Relationship(type = "ACTED_BY", direction = Relationship.UNDIRECTED)
  private Set<Actor> actors;


  public void addActor(Actor actor){
    if(actors==null)
      actors= Sets.newHashSet();
    actors.add(actor);
  }

}
