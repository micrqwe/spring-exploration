#!/bin/bash
git pull
./mvnw clean package -Dmaven.test.skip=true
mv spring-exploration-gateway/target/*.jar ../spring-exploration-jar
mv spring-exploration-consumer/target/*.jar ../spring-exploration-jar
mv spring-exploration-file/target/*.jar ../spring-exploration-jar
