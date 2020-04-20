package ar.com.moviecentral.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorDTO {

  private String firstName;
  private String lastName;
  private Long id;

}
