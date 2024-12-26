@import io.github.dumijdev.restdb4j.server.application.core.domain.common.Where.Operator
@import io.github.dumijdev.restdb4j.server.application.core.domain.select.JoinType
@import io.github.dumijdev.restdb4j.server.adapters.output.database.utils.SQLGenerator.Database


@param Operator[] operators
@param JoinType[] joinTypes
@param Database[] databases
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description"
          content="Comprehensive documentation for RestDB4J: Simplify operations with relational databases via a RESTful API."/>
    <meta name="keywords" content="RestDB4J, documentation, REST API, relational database, integration"/>
    <meta name="author" content="DumiJDev"/>
    <title>RestDB4J | Official Documentation</title>
    <link rel="stylesheet" href="styles.css"/>
</head>
<body>
<!-- Top Navigation Bar -->
<header class="navbar">
    <div class="navbar-logo">RestDB4J</div>
    <button id="darkModeToggle" class="dark-mode-toggle">🌙</button>
</header>

<!-- Main Layout with Sidebar -->
<div class="layout">
    <aside class="sidebar">
        <h3>Sections</h3>
        <ul>
            <li><a href="#introduction">Introduction</a></li>
            <li><a href="#motivation">Motivation</a></li>
            <li><a href="#installation">Installation</a></li>
            <li><a href="#operations">Operations</a></li>
            <li><a href="#constants">Constants</a></li>
            <li><a href="#configuration">Configuration</a></li>
            <li><a href="#contribute">Contribute</a></li>
        </ul>
    </aside>

    <main class="docs-content">
        <section id="introduction">
            <h2>Introduction</h2>
            <p>
                RestDB4J is an innovative solution that simplifies access to relational databases through
                a RESTful interface. It allows you to perform operations such as querying, inserting,
                updating, and deleting using HTTP calls. This approach promotes simple, secure, and standardized
                integration between systems.
            </p>
            <h4>Key Benefits</h4>
            <ul>
                <li><b>Interoperability:</b> Supports multiple databases, including MySQL, PostgreSQL, and SQL Server.
                </li>
                <li><b>Ease of Use:</b> Perform operations via REST endpoints accessible from any language or framework
                    that supports HTTP.
                </li>
                <li><b>Standardization:</b> Unified interface abstracts differences across databases.</li>
                <li><b>Security:</b> Configurable access control ensures data protection.</li>
                <li><b>Scalability:</b> Ideal for microservices architectures and distributed APIs.</li>
            </ul>
        </section>

        <section id="motivation">
            <h2>Motivation</h2>
            <p>
                In an increasingly connected world, integrating systems is essential. However, directly managing
                relational databases can be challenging due to differences in drivers, the need for SQL knowledge,
                and security concerns. RestDB4J addresses these issues with a simple and efficient REST API.
            </p>
            <h4>Why a RESTful API for Databases?</h4>
            <ul>
                <li><b>Decentralization:</b> Enables access by multiple services without driver dependencies.</li>
                <li><b>Simplified Access:</b> Eliminates the need to directly interact with SQL.</li>
                <li><b>Consistency:</b> Follows REST standards for uniform communication.</li>
                <li><b>Security:</b> Reduces risks with controlled endpoints.</li>
                <li><b>Technological Interoperability:</b> Any HTTP-capable language can integrate easily.</li>
            </ul>
        </section>

        <section id="installation">
            <h2>Installation</h2>
            <p>Follow the steps below to install RestDB4J:</p>
            <h3>Requirements</h3>
            <ul>
                <li>Java 21+</li>
                <li>Maven 3.9.9+</li>
                <li>Docker (for containerized use)</li>
            </ul>

            <h4>Running on the JVM</h4>
            <ol>
                <li><code>git clone <a href="https://github.com/DumiJDev/restdb4j.git" target="_blank">https://github.com/DumiJDev/restdb4j.git</a></code>
                </li>
                <li><code>cd restdb4j</code></li>
                <li><code>mvn clean install package</code></li>
                <li><code>cd target</code></li>
                <li><code>java -jar restdb4j.jar</code></li>
            </ol>

            <h4>Running with Docker</h4>
            <ol>
                <li><code>docker run --name restdb4j --rm -p 8082:8080 dumijdev/restdb4j:latest</code></li>
            </ol>
        </section>

        <section id="operations">
            <h2>Supported Operations</h2>
            <ul>
                <li>Querying (fields, tables, filters, sorting, joins, and pagination)</li>
                <li>Insertion</li>
                <li>Deletion</li>
                <li>Updating</li>
            </ul>
        </section>

        <section id="constants">
            <h2>Constants</h2>
            <h4>Operators</h4>
            <ul>
                @for(var operator : operators)@if(!operator.value().isEmpty())
                    <li>SQL: ${operator.value()}, API: <code>${operator.symbol()}</code></li>
                @endif@endfor
            </ul>

            <h4>Join Types</h4>
            <ul>
                @for(var joinType : joinTypes)
                    <li>SQL: ${joinType.name()}, API: <code>${joinType.value()}</code></li>
                @endfor
            </ul>

            <h4>Supported Databases</h4>
            <ul>
                @for(var database : databases)
                    <li>${database.name()}, API: <code>${database.value()}</code></li>
                @endfor
            </ul>
        </section>

        <section id="configuration">
            <h2>Configuration</h2>
            <p>
                Configurations can be defined in the <code>application.yml</code> file or via environment variables
                (also compatible with Docker setups).
            </p>

            <h4>Docker Environment</h4>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Default</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>RESTDB_NAME</td>
                    <td>RestDB4J</td>
                    <td>Name for application instance</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_PLATFORM</td>
                    <td>h2</td>
                    <td>Database platform (API value, see <a href="#constants">Constants</a>)</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_PORT</td>
                    <td></td>
                    <td>Database port</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_USER</td>
                    <td>sa</td>
                    <td>Database user</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_PWD</td>
                    <td>password</td>
                    <td>Database password</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_NAME</td>
                    <td>restdb.db</td>
                    <td>Database name</td>
                </tr>
                <tr>
                    <td>RESTDB_DRIVER_CLASS</td>
                    <td></td>
                    <td>Java Driver Class for Database</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_URL</td>
                    <td></td>
                    <td>Database custom url String connection</td>
                </tr>
                <tr>
                    <td>RESTDB_SERVER_NAME</td>
                    <td>restdb</td>
                    <td>Name for instance in url</td>
                </tr>
                <tr>
                    <td>RESTDB_DB_HOST</td>
                    <td>localhost</td>
                    <td>Database host</td>
                </tr>
                </tbody>
            </table>
        </section>

        <section id="contribute">
            <h2>Contribute</h2>
            <p>
                Help improve RestDB4J by adding features, fixing bugs, or refining the codebase. Follow these steps:
            </p>
            <ol>
                <li>Fork the repository and create a branch for your contribution.</li>
                <li>Open an issue describing your goal or problem.</li>
                <li>Implement changes following project standards.</li>
                <li>Submit a Pull Request for review.</li>
            </ol>
        </section>
    </main>
</div>

<footer>
    <p>
        &copy; 2024 RestDB4J | Created by
        <a href="https://github.com/DumiJDev" target="_blank">DumiJDev</a>
    </p>
</footer>

<script src="script.js"></script>
</body>
</html>
