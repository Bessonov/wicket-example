# Apache Wicket Authentication and Authorization Example

Simple example include:
- i18n
- Hibernate (hsqldb is default and mysql example config is included)
- Authentication (Register/Login/Logout)
- Authorization (Render Panels, Register only for anonymous user access)
- Standalone application packaging

## Run application

To run application:

	git clone https://github.com/Bessonov/wicket-example
	cd wicket-example
	mvn jetty:run

To show:
	[http://localhost:8080/](http://localhost:8080/)

## Standalone application packaging

To package:

	mvn package

To run:

	java -jar target/wicketexmaple-1.0-SNAPSHOT.jar
	or
	java -cp target/wicketexmaple-1.0-SNAPSHOT.jar de.bessonov.EmbeddedJettyServer

To show:
	[http://localhost:8080/](http://localhost:8080/)

## Change package structure
For own package structure you can:
- Refactor
- Rearchetype (especially for non-IDE users)

### Rearchetype

To create archetype:

	git clone https://github.com/Bessonov/wicket-example
	cd wicket-example/
	mvn clean archetype:create-from-project
	cd target/generated-sources/archetype/
	mvn install

And then you can create application:

	cd work_folder
	mvn archetype:generate -DarchetypeCatalog=local -DarchetypeGroupId=de.bessonov -DarchetypeArtifactId=wicketexample-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=my.domain -DartifactId=myproject -Dversion=1.0-SNAPSHOT

# License
Copyright (c) 2012 Anton Bessonov.
All rights reserved. This program and the accompanying materials
are made available under the terms of the Creative Commons
Attribution 3.0 License which accompanies this distribution,
and is available at
http://creativecommons.org/licenses/by/3.0/
