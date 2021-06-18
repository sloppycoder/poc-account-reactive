
## Initial thoughts
Things worked remarkably well and Quarkus tries to do the right thing with almost no setup required.
Things worked out out-of-box with almost no tinkering:
1. Build uses docker to run dependencies whenever they're not present on the existing system, including: postgresql, graavlvm (for build native image)
2. Comes with standard Dockerfile that uses RH UBI as base image. Builds container images with
3. Jacoco coverage report works no setup required.
4. The uber.jar is only 28M, compared to typical Spring JAR of 50-100M+. Native binary is about 90M.
5. Prometheus metrics works after adding dependency. no setup required.
6. Log output shows trace info after adding opentracing dependency.

The only thing didn't work too well is integrate into contract first workflow with Swagger/OpenAPI code generator. In both cases generated interface file needs some minor tweaking to work with reactive endpoing. In most cases chagne ```Response``` to ```Uni<Response>``` should do the trick. See [this commit](https://github.com/sloppycoder/poc-account-reactive/commit/48b2882e2f5f85c32dbf2e182c6c8376547a8ef0) for details.
This should be solvable by supply custom generator template.
At the moment the async database support is limited to DB2, MySQL and PostgreSQL. Oracle is not supported.


## To add new features to the API
1. Modify the [schema file](./schema/accounts-openapi.yaml)
2. Run ```cd schema ; ./gen.sh```. This step uses openapi-generator to generate new interfaces and models. It overwrites existing files.
3. Tweak the ```AccountsApi``` and ```AccountsApiService``` classes.
4. Implement the business logic in ```AccountsApiServiceImpl``` classes.

## To Dos
