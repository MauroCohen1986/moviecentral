package ar.com.moviecentral.controller;

import static org.junit.Assert.assertEquals;

import ar.com.moviecentral.configuration.SpringBootNeo4JTestConfiguration;
import ar.com.moviecentral.dtos.ActorDTO;
import ar.com.moviecentral.dtos.MovieDTO;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = { SpringBootNeo4JTestConfiguration.class })
public class MovieControllerTest {

  @Autowired
  MovieController movieController;

  @Test
  public void testCreateMovie_integration(){
    MovieDTO movie_with_no_actors = movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-01"))
        .title("Movie With No Actors")
        .actors(Lists.newArrayList())
        .build());
    assertEquals("Movie With No Actors",movie_with_no_actors.getTitle());
    assertEquals(LocalDate.parse("2019-01-01"),movie_with_no_actors.getDate());
    assertEquals(0,movie_with_no_actors.getActors().size());

    MovieDTO movie_with_one_new_actor= movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-02"))
        .title("Movie With One Actor")
        .actors(Lists.newArrayList(
            ActorDTO.builder().firstName("TestName").lastName("LastName").build()
        ))
        .build());
    assertEquals("Movie With One Actor",movie_with_one_new_actor.getTitle());
    assertEquals(LocalDate.parse("2019-01-02"),movie_with_one_new_actor.getDate());
    assertEquals(1,movie_with_one_new_actor.getActors().size());
    assertEquals("TestName",movie_with_one_new_actor.getActors().get(0).getFirstName());
    assertEquals("LastName",movie_with_one_new_actor.getActors().get(0).getLastName());


    MovieDTO movie_with_one_existing_actor= movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-03"))
        .title("Movie With One Existing Actor")
        .actors(Lists.newArrayList(
            ActorDTO.builder().id(movie_with_one_new_actor.getActors().get(0).getId()).build()
        ))
        .build());
    assertEquals("Movie With One Existing Actor",movie_with_one_existing_actor.getTitle());
    assertEquals(LocalDate.parse("2019-01-03"),movie_with_one_existing_actor.getDate());
    assertEquals(1,movie_with_one_existing_actor.getActors().size());
    assertEquals("TestName",movie_with_one_existing_actor.getActors().get(0).getFirstName());
    assertEquals("LastName",movie_with_one_existing_actor.getActors().get(0).getLastName());

  }


  @Test
  public void testAddActorToMovie_integration(){
    MovieDTO emptyMovie = movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-01"))
        .title("EmptyMovie")
        .actors(Lists.newArrayList())
        .build());

    MovieDTO result = movieController.addActorToMovie(emptyMovie.getId(), ActorDTO.builder()
        .firstName("Thomas")
        .lastName("House")
        .build());

    assertEquals(1,result.getActors().size());
    assertEquals("Thomas",result.getActors().get(0).getFirstName());
    assertEquals("House",result.getActors().get(0).getLastName());

    MovieDTO anotherEmptyMovie = movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-01"))
        .title("EmptyMovie")
        .actors(Lists.newArrayList())
        .build());

    result = movieController.addActorToMovie(anotherEmptyMovie.getId(), ActorDTO.builder()
        .id(result.getActors().get(0).getId())
        .build());

    assertEquals(1,result.getActors().size());
    assertEquals("Thomas",result.getActors().get(0).getFirstName());
    assertEquals("House",result.getActors().get(0).getLastName());

    //ADD SAME ACTOR TWICE
    result = movieController.addActorToMovie(anotherEmptyMovie.getId(), ActorDTO.builder()
        .id(result.getActors().get(0).getId())
        .build());

    assertEquals(1,result.getActors().size());
    assertEquals("Thomas",result.getActors().get(0).getFirstName());
    assertEquals("House",result.getActors().get(0).getLastName());



  }

  @Test
  public void testSearchMoviesByActor_integration(){
    MovieDTO newMovie1= movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-02"))
        .title("Movie With Three Actors")
        .actors(Lists.newArrayList(
            ActorDTO.builder().firstName("Actor1").build(),
            ActorDTO.builder().firstName("Actor2").build(),
            ActorDTO.builder().firstName("Actor#").build()
        ))
        .build());

    Long actorId = newMovie1.getActors().get(0).getId();

    movieController.createMovie(MovieDTO
        .builder()
        .date(LocalDate.parse("2019-01-02"))
        .title("Movie With One Actor")
        .actors(Lists.newArrayList(
            ActorDTO.builder().id(actorId).build()
        ))
        .build());

    List<MovieDTO> movieDTOS = movieController.searchMoviesByActor(actorId);
    assertEquals(2,movieDTOS.size());

    Long oneSingleMovieActor = newMovie1.getActors().stream().map(ActorDTO::getId).filter(id->id!=actorId).findFirst().get();
    movieDTOS = movieController.searchMoviesByActor(oneSingleMovieActor);
    assertEquals(1,movieDTOS.size());
  }

}
