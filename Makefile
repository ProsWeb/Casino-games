.DEFAULT_GOAL := update-build-run

run:
	java -jar ./target/Project1Java-1.0-SNAPSHOT-jar-with-dependencies.jar

clean:
	rm -rf ./target

update-build-run: update build run

build:
	./mvnw verify

update:
	./mvnw versions:update-properties versions:display-plugin-updates
