package io.github.dumijdev.restdb4j.server.infra.config;

import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.service.DeleteOperationService;
import io.github.dumijdev.restdb4j.server.application.core.service.InsertOperationService;
import io.github.dumijdev.restdb4j.server.application.core.service.SelectOperationService;
import io.github.dumijdev.restdb4j.server.application.core.service.UpdateOperationService;
import io.github.dumijdev.restdb4j.server.application.ports.input.DeleteOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.InsertOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.SelectOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.input.UpdateOperationInputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.DeleteOperationOutputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.InsertOperationOutputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.SelectOperationOutputPort;
import io.github.dumijdev.restdb4j.server.application.ports.output.UpdateOperationOutputPort;
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
  public InsertOperationInputPort insertOperationInputPort(InsertOperationOutputPort outputPort) {
    return new InsertOperationService(outputPort);
  }

  @Bean
  public UpdateOperationInputPort updateOperationInputPort(UpdateOperationOutputPort outputPort) {
    return new UpdateOperationService(outputPort);
  }

  @Bean
  public DeleteOperationInputPort deleteOperationInputPort(DeleteOperationOutputPort outputPort) {
    return new DeleteOperationService(outputPort);
  }

  @Bean
  public SQLGenerator sqlGenerator(@Value("${restdb4j.platform}") String database) {
    return new SQLGenerator(SQLGenerator.Database.from(database));
  }
}
