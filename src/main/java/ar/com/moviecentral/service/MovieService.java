package ar.com.moviecentral.service;

import ar.com.moviecentral.daos.ActorDAO;
import ar.com.moviecentral.daos.MovieDAO;
import ar.com.moviecentral.dtos.ActorDTO;
import ar.com.moviecentral.dtos.MovieDTO;
import ar.com.moviecentral.exceptions.ActorNotFoundException;
import ar.com.moviecentral.exceptions.MovieNotFoundException;
import ar.com.moviecentral.mappers.MovieDTOMapper;
import ar.com.moviecentral.model.Actor;
import ar.com.moviecentral.model.Movie;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  MovieDAO movieDAO;

  @Autowired
  ActorDAO actorDAO;

  public List<MovieDTO> findMoviesByActor(Long actorId){
    return movieDAO.getMoviesByActorId(actorId)
        .stream()
        .map(movie -> MovieDTOMapper.map(movie))
        .collect(Collectors.toList());
  }

  public MovieDTO createMovie(MovieDTO movieDTO){
    Movie movie = new Movie();
    movie.setDate(movieDTO.getDate());
    movie.setTitle(movieDTO.getTitle());
    addActorsToMovie(movie,movieDTO.getActors());
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
    if(actorDTO.getId()!=null){
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
