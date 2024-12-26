package io.github.dumijdev.restdb4j.server.infra.docs;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.FileOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator;
import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.JoinType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DocsGenerator {
  public static void main(String[] args) throws IOException {
    var codeResolver = new DirectoryCodeResolver(Path.of("server/src/main/jte"));
    var templateEngineHtml = TemplateEngine.create(codeResolver, ContentType.Html);
    var templateEngineMarkdown = TemplateEngine.create(codeResolver, ContentType.Plain);

    Map<String, Object> data = Map.of(
        "operators", Where.Operator.values(),
        "joinTypes", JoinType.values(),
        "databases", SQLGenerator.Database.values()
    );

    try (var htmlOutput = new FileOutput(Path.of("./", "docs.html"), UTF_8);
    var markdownOutput = new FileOutput(Path.of("./", "README.md"), UTF_8)) {

      templateEngineMarkdown.render("readme.jte", data, markdownOutput);
      templateEngineHtml.render("docs.jte", data, htmlOutput);
    }
      System.out.println("Successfully wrote to html file");
  }
}
