package ar.com.moviecentral.daos;


import ar.com.moviecentral.model.Movie;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieDAO extends CrudRepository<Movie,Long> {

  @Query("MATCH (n:Movie)-[:ACTED_BY]-(actor:Actor) WHERE ID(actor) = {0} WITH n RETURN n,[ [ (n)<-[r_a1:ACTED_BY]-(a1:Actor) | [ r_a1, a1 ] ] ];")
  List<Movie> getMoviesByActorId(Long actorId);


}
