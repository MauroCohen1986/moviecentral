package ar.com.moviecentral.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDTO {

  private Long id;
  private String title;
  private LocalDate date;
  private List<ActorDTO> actors;


}
