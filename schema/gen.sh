
CLI_JAR=~/Downloads/swagger-codegen-cli-3.0.26.jar

if [ ! -f CLI_JAR ]; then
  rm -rf out
  java -jar $CLI_JAR generate --config config.json --input-spec accounts-openapi.yaml -o out
else
  echo Cannot find $CLI_JAR file.
fi

if [ "$1" = "--copy" ]; then
  echo
  echo Copying generated files to src folder
  echo
	cp out/src/main/java/org/vino9/poc/model/*.java ../src/main/java/org/vino9/poc/model/.
	cp out/src/main/java/org/vino9/poc/api/AccountsApi*.java ../src/main/java/org/vino9/poc/api/.
	cp out/src/main/java/org/vino9/poc/api/impl/AccountsApiServiceImpl.java ../src/main/java/org/vino9/poc/api/impl/.
fi

echo
echo check out directory for generated stuff
echo do not forget to reformat all generated code
