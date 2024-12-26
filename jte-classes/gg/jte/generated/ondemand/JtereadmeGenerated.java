package gg.jte.generated.ondemand;
import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator;
import io.github.dumijdev.restdb4j.server.application.core.domain.select.JoinType;
import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator.Database;
@SuppressWarnings("unchecked")
public final class JtereadmeGenerated {
	public static final String JTE_NAME = "readme.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,5,5,5,5,93,93,93,93,93,93,93,93,94,94,94,97,97,97,97,97,97,98,98,101,101,101,101,101,101,102,102,143,143,143,5,6,7,7,7,7};
	public static void render(gg.jte.TemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Operator[] operators, JoinType[] joinTypes, Database[] databases) {
		jteOutput.writeContent("# RestDB4J\r\n\r\nRestDB4J is a lightweight and scalable solution for interacting with relational databases using a RESTful API. It abstracts the complexities of direct database connections, allowing developers to perform operations like querying, inserting, updating, and deleting through HTTP/REST calls.\r\n\r\n## Table of Contents\r\n- [Introduction](#introduction)\r\n- [Motivation](#motivation)\r\n- [Installation](#installation)\r\n  - [On JVM](#on-jvm)\r\n  - [On Docker](#on-docker)\r\n- [Operations](#operations)\r\n- [Constants](#constants)\r\n- [Configuration](#configuration)\r\n- [Contribute](#contribute)\r\n\r\n## Introduction\r\nIn the modern world, integration between applications is a fundamental aspect of creating agile and efficient solutions. Relational databases play a critical role as repositories of structured information. However, direct access can be challenging, especially in scenarios requiring decentralized and secure communication.\r\n\r\nRestDB4J simplifies interactions with relational databases by providing a RESTful interface. It supports common database operations like querying, inserting, updating, and deleting. The solution is ideal for teams seeking to access or manipulate data without managing drivers, SQL languages, or complex configurations.\r\n\r\n### Key Benefits\r\n- **Interoperability**: Support for various relational databases (MySQL, PostgreSQL, SQL Server, etc.).\r\n- **Ease of Use**: Perform database operations through REST endpoints.\r\n- **Standardization**: Unified interface for consistent communication.\r\n- **Security**: Configurable access control to protect data.\r\n- **Scalability**: Perfect for distributed applications and microservices.\r\n\r\n## Motivation\r\nModern applications face challenges such as:\r\n- Diverse drivers and configurations for different databases.\r\n- The need to learn SQL for direct queries.\r\n- Security risks when exposing direct database connections.\r\n- Integration complexity in microservice architectures.\r\n\r\nRestDB4J addresses these issues by offering:\r\n- A RESTful API for decentralized and flexible database access.\r\n- A simplified HTTP interface eliminating the need for SQL.\r\n- Process standardization through REST conventions.\r\n- Improved security via controlled endpoints.\r\n- Interoperability with any HTTP-compatible language or tool.\r\n\r\n## Installation\r\n\r\n### Requirements\r\n- **Java**: 21+\r\n- **Maven**: 3.9.9+\r\n- **Docker**: Required for running in a container.\r\n\r\n### On JVM\r\n1. Clone the repository:\r\n   ```bash\r\n   git clone https://github.com/DumiJDev/restdb4j.git\r\n   ```\r\n2. Navigate to the project directory:\r\n   ```bash\r\n   cd restdb4j\r\n   ```\r\n3. Build the project:\r\n   ```bash\r\n   mvn clean install package\r\n   ```\r\n4. Navigate to the target directory:\r\n   ```bash\r\n   cd target\r\n   ```\r\n5. Run the application:\r\n   ```bash\r\n   java -jar restdb4j.jar\r\n   ```\r\n\r\n### On Docker\r\nRun the following command:\r\n```bash\r\ndocker run --name restdb4j --rm -p 8082:8080 dumijdev/restdb4j:latest\r\n```\r\n\r\n## Operations\r\nRestDB4J currently supports:\r\n- **Select**: ALIAS fields and table, WHERE, ORDER BY, JOIN, PAGINATION.\r\n- **Insert**.\r\n- **Update**.\r\n- **Delete**.\r\n\r\n## Constants\r\n### Operators\r\n");
		for (var operator : operators) {
			if (!operator.value().isEmpty()) {
				jteOutput.writeContent("- SQL: `");
				jteOutput.writeUserContent(operator.value());
				jteOutput.writeContent("`, API: `");
				jteOutput.writeUserContent(operator.symbol());
				jteOutput.writeContent("`\r\n");
			}
		}
		jteOutput.writeContent("\r\n\r\n### JoinType\r\n");
		for (var joinType : joinTypes) {
			jteOutput.writeContent("- SQL: ");
			jteOutput.writeUserContent(joinType.name());
			jteOutput.writeContent(", API: `");
			jteOutput.writeUserContent(joinType.value());
			jteOutput.writeContent("`\r\n");
		}
		jteOutput.writeContent("\r\n\r\n### Supported Databases\r\n");
		for (var database : databases) {
			jteOutput.writeContent("- ");
			jteOutput.writeUserContent(database.name());
			jteOutput.writeContent(", API: `");
			jteOutput.writeUserContent(database.value());
			jteOutput.writeContent("`\r\n");
		}
		jteOutput.writeContent("\r\n\r\n## Configuration\r\nConfiguration can be done using:\r\n- **application.yml**: For local setup.\r\n- **Environment variables**: Works seamlessly with Docker.\r\n\r\n### Docker Environment\r\n\r\n| Name               | Default     | Description                                                |\r\n|--------------------|-------------|------------------------------------------------------------|\r\n| RESTDB_NAME        | RestDB4J    | Name for application instance                              |\r\n| RESTDB_DB_PLATFORM | h2          | Database platform (API value, see [Constants](#constants)) |\r\n| RESTDB_DB_PORT     |             | Database port                                              |\r\n| RESTDB_DB_USER     | sa          | Database user                                              |\r\n| RESTDB_DB_PWD      | password    | Database password                                          |\r\n| RESTDB_DB_NAME     | restdb.db   | Database name                                              |\r\n| RESTDB_DRIVER_CLASS|             | Java Driver Class for Database                             |\r\n| RESTDB_DB_URL      |             | Database custom URL string connection                     |\r\n| RESTDB_SERVER_NAME | restdb      | Name for instance in URL                                   |\r\n| RESTDB_DB_HOST     | localhost   | Database host                                              |\r\n\r\n## Contribute\r\nYour contributions are vital! Follow the steps below to get started:\r\n\r\n1. **Fork the repository**: Create your own copy to work on.\r\n2. **Create a branch**: Name your branch descriptively, e.g., `feature/new-feature` or `fix/bug-fix`.\r\n3. **Open an issue**: Describe your idea or the bug you plan to address.\r\n4. **Implement your changes**: Follow coding standards and include tests.\r\n5. **Submit a Pull Request**: Target the `staging` branch with a detailed description of your changes.\r\n\r\n### Contribution Guidelines\r\n- Ensure code quality and proper documentation.\r\n- Add tests for any new features or bug fixes.\r\n- Follow the repository's coding standards.\r\n\r\n## License\r\nThis project is licensed under the MIT License.\r\n\r\n---\r\n\r\n&copy; 2024 RestDB4J - Powered by [DumiJDev](https://github.com/DumiJDev)");
	}
	public static void renderMap(gg.jte.TemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Operator[] operators = (Operator[])params.get("operators");
		JoinType[] joinTypes = (JoinType[])params.get("joinTypes");
		Database[] databases = (Database[])params.get("databases");
		render(jteOutput, jteHtmlInterceptor, operators, joinTypes, databases);
	}
}
