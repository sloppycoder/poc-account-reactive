#!/bin/bash

CLI_JAR=~/Downloads/swagger-codegen-cli-3.0.26.jar

if [ ! -f CLI_JAR ]; then
  java -jar $CLI_JAR generate --config config.json --input-spec accounts-api.yaml --lang java --type SERVER
else
  echo Cannot find $CLI_JAR file.
fi

if [ "$1" != "--keep" ]; then
  # remove the junk files genereated by generator
  rm -rf .gitignore .swagger-codegen-ignore git_push.sh .travis.yml README.md
  rm -f build.gradle gradlew gradlew.bat settings.gradle gradle.properties
  rm -f pom.xml build.sbt
  rm -rf gradle docs .swagger-codegen
fi