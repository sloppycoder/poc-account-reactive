#!/bin/bash

# run generator
../mvnw -f apigen.xml

# there are some java files in src folder but we don't really need them.
# one day we'll override the templates to solve these problems

# cleanup
rm -f pom.xml
rm -rf .openapi-generator
rm -rf src

