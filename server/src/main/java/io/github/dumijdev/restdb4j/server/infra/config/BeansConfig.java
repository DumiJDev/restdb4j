package io.github.dumijdev.restdb4j.server.infra.config;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.service.SelectOperationService;
import io.github.dumijdev.restdb4j.server.application.ports.input.SelectOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.SelectOperationOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
  @Bean
  public SelectOperationInputPort selectOperationInputPort(SelectOperationOutputPort outputPort) {
    return new SelectOperationService(outputPort);
  }

  @Bean
  public SQLGenerator sqlGenerator(@Value("${restdb4j.platform}") String database) {
    return new SQLGenerator(SQLGenerator.Database.from(database));
  }
}
