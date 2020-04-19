package ar.com.moviecentral.controller;


import ar.com.moviecentral.dtos.Protos.MoviesDTOS;
import ar.com.moviecentral.dtos.Protos.MoviesDTOS.MovieDTO;
import ar.com.moviecentral.dtos.Protos.MoviesDTOS.MovieDTO.ActorDTO;
import ar.com.moviecentral.service.MovieService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "MovieController")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @RequestMapping(method = RequestMethod.POST, path = "/movie")
  @ResponseBody
  public MovieDTO createMovie(
      @RequestBody @Valid MovieDTO movieDTO) {
    return movieService.createMovie(movieDTO);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/movie/byactor/{id}",produces = "application/x-protobuf;charset=utf-8")
  @ResponseBody
  public MoviesDTOS searchMovieByActor(
      @PathVariable("id") Long actorId) {
    return movieService.findMoviesByActor(actorId);
  }


  @RequestMapping(method = RequestMethod.POST, path = "/movie/{id}/addActors")
  @ResponseBody
  public MovieDTO addActorToMovie(
      @PathVariable("id") Long movieId,
      @RequestBody @Valid ActorDTO actorDTO) {
    return movieService.addActorToMovie(movieId,actorDTO);
  }


}
