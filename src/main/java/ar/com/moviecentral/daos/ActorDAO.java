package ar.com.moviecentral.daos;


import ar.com.moviecentral.model.Actor;
import ar.com.moviecentral.model.Movie;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface ActorDAO extends CrudRepository<Actor,Long> {

}
