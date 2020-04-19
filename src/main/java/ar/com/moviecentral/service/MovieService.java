package ar.com.moviecentral.service;

import ar.com.moviecentral.daos.ActorDAO;
import ar.com.moviecentral.daos.MovieDAO;
import ar.com.moviecentral.dtos.Protos.MoviesDTOS;
import ar.com.moviecentral.dtos.Protos.MoviesDTOS.MovieDTO;
import ar.com.moviecentral.dtos.Protos.MoviesDTOS.MovieDTO.ActorDTO;
import ar.com.moviecentral.exceptions.ActorNotFoundException;
import ar.com.moviecentral.exceptions.MovieNotFoundException;
import ar.com.moviecentral.mappers.MovieDTOMapper;
import ar.com.moviecentral.model.Actor;
import ar.com.moviecentral.model.Movie;
import ar.com.moviecentral.utils.DateConverter;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  private MovieDAO movieDAO;

  @Autowired
  private ActorDAO actorDAO;

  public MoviesDTOS findMoviesByActor(Long actorId){
    Set<MovieDTO> moviesDTOS = Sets
        .newHashSet(movieDAO.findAllById(movieDAO.getMoviesIdsByActors(actorId)))
        .stream()
        .map(movie -> MovieDTOMapper.map(movie))
        .collect(Collectors.toSet());
    return MoviesDTOS.newBuilder().addAllMovies(moviesDTOS).build();
  }

  public MovieDTO createMovie(MovieDTO movieDTO){
    Movie movie = new Movie();
    movie.setDate(DateConverter.toLocalDate(movieDTO.getDate()));
    movie.setTitle(movieDTO.getTitle());
    addActorsToMovie(movie,movieDTO.getActorsList());
    return MovieDTOMapper.map(movieDAO.save(movie));
  }

  public MovieDTO addActorToMovie(Long movieId, ActorDTO actorDTO) {
    Movie movie = movieDAO.findById(movieId).orElseThrow(()->new MovieNotFoundException());
    movie.addActor(getActorFromDTO(actorDTO));
    return MovieDTOMapper.map(movieDAO.save(movie));
  }


  private void addActorsToMovie(Movie movie, List<ActorDTO> actorDTOS){
    if(actorDTOS!=null){
      for (ActorDTO actorDTO : actorDTOS) {
        movie.addActor(getActorFromDTO(actorDTO));
      }
    }
  }

  private Actor getActorFromDTO(ActorDTO actorDTO) {
    Actor actor;
    if(actorDTO.hasId()){
      actor = actorDAO.findById(actorDTO.getId()).orElseThrow(()->new ActorNotFoundException());
    }else{
      actor = new Actor();
      actor.setFirstName(actorDTO.getFirstName());
      actor.setLastName(actorDTO.getLastName());
      actor= actorDAO.save(actor);
    }
    return actor;
  }

}
