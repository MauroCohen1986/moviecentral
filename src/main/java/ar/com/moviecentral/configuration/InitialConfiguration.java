package ar.com.moviecentral.configuration;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@Configuration
public class InitialConfiguration {

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
  webServerFactoryCustomizer() {
    return factory -> factory.setContextPath("/moviecentral");
  }

  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    return new ProtobufHttpMessageConverter();
  }

}
