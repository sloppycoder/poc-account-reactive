# accounts-reactive project

A reactive RESTful API built with [Quarkus](https://quarkus.io).

## Quick Start
```text
# start server in dev model
./mvnw quarkus:dev

curl http://localhost:8080/accounts/11223344 | jq .

# server responds:
{
  "accountNo": "11223344",
  "currency": "SGD",
  "country": "SG",
  "branchCode": "1234",
  "balance": []
}

# the log output shows tracing info
21:14:30 INFO  traceId=64e9402fbefbddec, parentId=0, spanId=64e9402fbefbddec, sampled=false [or.vi.po.ap.im.AccountsApiServiceImpl] (vert.x-eventloop-thread-5) retriving account detail for 11223344

curl http://localhost:8080/q/metrics | tail -10
# prometheus metrics are visible
...
# TYPE http_server_bytes_written summary
http_server_bytes_written_count 4.0
http_server_bytes_written_sum 26961.0
# HELP jvm_threads_daemon_threads The current number of live daemon threads
# TYPE jvm_threads_daemon_threads gauge
jvm_threads_daemon_threads 18.0

# build a native executable binary
./mvnw package -Dnative

#takes more than 5 mins, go have a coffee....
ls -l target
...
-rwxr-xr-x 1 lee lee 97990472 Jun  6 11:22 accounts-reactive-1.0.0-SNAPSHOT-runner

```
