package ar.com.moviecentral.mappers;

import ar.com.moviecentral.dtos.ActorDTO;
import ar.com.moviecentral.dtos.MovieDTO;
import ar.com.moviecentral.model.Actor;
import ar.com.moviecentral.model.Movie;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDTOMapper {

  public static MovieDTO map(Movie movie){
    return MovieDTO.builder()
        .date(movie.getDate())
        .id(movie.getId())
        .title(movie.getTitle())
        .actors(mapActors(movie.getActors()))
    .build();
  }

  private static List<ActorDTO> mapActors(Set<Actor> actors) {
    List<ActorDTO> result = Lists.newArrayList();
    if(actors!=null) {
      result = actors
          .stream()
          .map(actor -> ActorDTO.builder()
              .firstName(actor.getFirstName())
              .lastName(actor.getLastName())
              .id(actor.getId())
              .build())
          .collect(Collectors.toList());
    }
    return result;
  }


}
