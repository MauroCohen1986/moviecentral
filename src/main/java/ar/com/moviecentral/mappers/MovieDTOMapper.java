package ar.com.moviecentral.mappers;

import ar.com.moviecentral.dtos.Protos.MovieDTO;
import ar.com.moviecentral.dtos.Protos.MovieDTO.ActorDTO;
import ar.com.moviecentral.model.Actor;
import ar.com.moviecentral.model.Movie;
import ar.com.moviecentral.utils.DateConverter;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDTOMapper {

  public static MovieDTO map(Movie movie){
    return MovieDTO.newBuilder()
        .setDate(DateConverter.toGoogleTimestamp(movie.getDate()))
        .setMovieId(movie.getId())
        .setTitle(movie.getTitle())
        .addAllActors(mapActors(movie.getActors()))
    .build();
  }

  private static Set<ActorDTO> mapActors(Set<Actor> actors) {
    return actors
        .stream()
        .map(actor-> ActorDTO.newBuilder()
            .setFirstName(actor.getFirstName())
            .setLastName(actor.getLastName())
            .setId(actor.getId())
            .build())
        .collect(Collectors.toSet()) ;
  }


}
