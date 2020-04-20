package ar.com.moviecentral.controller;


import ar.com.moviecentral.dtos.ActorDTO;
import ar.com.moviecentral.dtos.MovieDTO;
import ar.com.moviecentral.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "MovieController")
@Api(value = "Movies REST Services")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @RequestMapping(method = RequestMethod.POST, path = "/movie")
  @ResponseBody
  @ApiOperation(value = "Create new Movie. When actor id is null we create new actors.")
  public MovieDTO createMovie(
      @RequestBody @Valid MovieDTO movieDTO) {
    return movieService.createMovie(movieDTO);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/movie/byactor/{id}")
  @ResponseBody
  @ApiOperation(value = "Search movies by actor id.")
  public List<MovieDTO> searchMoviesByActor(
      @PathVariable("id") Long actorId) {
    return movieService.findMoviesByActor(actorId);
  }


  @RequestMapping(method = RequestMethod.POST, path = "/movie/{id}/addActors")
  @ResponseBody
  @ApiOperation(value = "Add new actor to an existing movie. If actor id is null we create a new one.")
  public MovieDTO addActorToMovie(
      @PathVariable("id") Long movieId,
      @RequestBody @Valid ActorDTO actorDTO) {
    return movieService.addActorToMovie(movieId,actorDTO);
  }


}
