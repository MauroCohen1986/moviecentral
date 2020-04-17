package ar.com.moviecentral.controller;


import ar.com.moviecentral.dtos.Protos.MovieDTO;
import ar.com.moviecentral.dtos.Protos.MovieDTO.ActorDTO;
import ar.com.moviecentral.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "MovieController")
@Api(value = "Movie Services")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @RequestMapping(method = RequestMethod.POST, path = "/movie")
  @ResponseBody
  @ApiOperation(value = "Create a new movie. If actor does not exist we create it and add it to the movie.")
  public MovieDTO createMovie(
      @RequestBody @Valid MovieDTO movieDTO) {
    return movieService.createMovie(movieDTO);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/movie/byactor/{id}")
  @ResponseBody
  @ApiOperation(value = "Search Movies By Actor.")
  public Set<MovieDTO> searchMovieByActor(
      @PathVariable("id") Long actorId) {
    return movieService.findMoviesByActor(actorId);
  }


  @RequestMapping(method = RequestMethod.POST, path = "/movie/{id}/addActors")
  @ResponseBody
  @ApiOperation(value = "Add actors to a movie.")
  public MovieDTO addActorToMovie(
      @PathVariable("id") Long movieId,
      @RequestBody @Valid ActorDTO actorDTO) {
    return movieService.addActorToMovie(movieId,actorDTO);
  }


}
