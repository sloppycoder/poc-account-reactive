## quarkus project study notes

### Pros

1. runs postgresql with docker automatically.
2. swagger-ui included with smallrye-openapi dependency.
3. docker files included in the generated project.
4. resteasy-reactive controller doesnot use reactive datatypes, easier to migrate from existing services and compatible
   with swagger generated code.
5. uber.jar is 28M

### Cons

1. generated pom.xml contains both resteasy and resteasy-reactive. can't run

### Issues

1. how does image build interact with JIB? JIB is much faster. the build depends on docker daemon? JIB doesn't.
2. swagger.io genreated code triggers ISO8601Utils is deprecated warning.

