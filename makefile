ejecutar:
	mvn compile assembly:single

javadoc:ejecutar
	mvn javadoc:javadoc

run:ejecutar
	java -jar target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar
