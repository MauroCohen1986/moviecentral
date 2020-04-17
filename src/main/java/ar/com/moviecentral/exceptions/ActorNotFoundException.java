package ar.com.moviecentral.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Does Not Exists.")
public class ActorNotFoundException extends RuntimeException{

}
