#!/usr/bin/env bash
mvn assembly:assembly
cp src/main/resources/questions.txt target/
java -Djava.awt.headless=true -jar target/learnbooster-1.0-SNAPSHOT-jar-with-dependencies.jar