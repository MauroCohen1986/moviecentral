package ar.com.moviecentral.daos;


import ar.com.moviecentral.model.Movie;
import java.util.Set;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieDAO extends CrudRepository<Movie,Long> {

  @Query("MATCH(actor:Actor)-[:ACTED_BY]-(movie:Movie) WHERE id(actor)={0} RETURN id(movie);")
  Set<Long> getMoviesIdsByActors(Long actorId);



}
