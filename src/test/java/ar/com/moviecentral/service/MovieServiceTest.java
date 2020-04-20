package ar.com.moviecentral.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.com.moviecentral.daos.ActorDAO;
import ar.com.moviecentral.daos.MovieDAO;
import ar.com.moviecentral.dtos.MovieDTO;
import ar.com.moviecentral.model.Actor;
import ar.com.moviecentral.model.Movie;
import com.google.common.collect.Sets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class MovieServiceTest {

  private MovieService instance;


  @Before
  public void before(){
    instance = new MovieService();
    instance.movieDAO = mock(MovieDAO.class);
    instance.actorDAO = mock(ActorDAO.class);
  }
  @Test
  public void testFindMoviesByActor(){
    Long actorId = 1L;
    List<Movie> movies = Lists.newArrayList(
        Movie.builder()
            .id(1L)
            .date(LocalDate.parse("2019-08-01"))
            .title("Title 1")
            .actors(Sets.newHashSet(
                Actor.builder().firstName("Actor 1").id(1L).lastName("LastName").build()
                )
            ).build(),
        Movie.builder()
            .id(2L)
            .date(LocalDate.parse("2019-08-01"))
            .title("Title 2")
            .actors(Sets.newHashSet(
                Actor.builder().firstName("Actor 1").id(1L).lastName("LastName").build()
                )
            ).build()
    );
    when(instance.movieDAO.getMoviesByActorId(actorId)).thenReturn(Lists.newArrayList());
    List<MovieDTO> result = instance.findMoviesByActor(actorId);
    assertEquals(0,result.size());

    when(instance.movieDAO.getMoviesByActorId(actorId)).thenReturn(movies);
    result = instance.findMoviesByActor(actorId);
    assertEquals(2,result.size());

    Optional<MovieDTO> movieDTO1 = result.stream()
        .filter(movieDTO -> movieDTO.getId() == 1L).findFirst();

    Optional<MovieDTO> movieDTO2 = result.stream()
        .filter(movieDTO -> movieDTO.getId() == 2L).findFirst();

    assertTrue(movieDTO1.isPresent());
    assertTrue(movieDTO2.isPresent());

    assertEquals("Title 1",movieDTO1.get().getTitle());
    assertEquals("Title 2",movieDTO2.get().getTitle());

    assertEquals(LocalDate.parse("2019-08-01"),movieDTO1.get().getDate());
    assertEquals(LocalDate.parse("2019-08-01"),movieDTO2.get().getDate());

    assertEquals("Actor 1",movieDTO1.get().getActors().get(0).getFirstName());
    assertEquals("Actor 1",movieDTO2.get().getActors().get(0).getFirstName());

    assertEquals("LastName",movieDTO1.get().getActors().get(0).getLastName());
    assertEquals("LastName",movieDTO2.get().getActors().get(0).getLastName());

    assertEquals(Long.valueOf(1l),movieDTO1.get().getActors().get(0).getId());
    assertEquals(Long.valueOf(1l),movieDTO2.get().getActors().get(0).getId());

  }

}