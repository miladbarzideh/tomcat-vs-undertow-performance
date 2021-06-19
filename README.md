Compare Performance with gatling for:

 - Servlet blocking call
 - Servlet with CompletableFuture call
 - Servlet with WebFlux
 - undertow


## Usage

 - `mvn spring-boot:run` to start the sleeping service

 - For Servlet(Tomcat) use "tomcat" maven profile (default)
`mvn spring-boot:run -P tomcat` to start the tomcat performance test. For undertow use "undertow" maven profile
`mvn spring-boot:run -P undertow` to start the tomcat performance test

 - `mvn gatling:test` while the webapp is running to run the performance test
