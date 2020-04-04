# springboot-graceful-shutdown-example
## Graceful Shutdown in Spring Boot
Grace Period for shutdown can be configured with `server.shutdown.grace-period` property.
- `/hello` returns `HelloWorld` and triggers a task internally which needs to be taken care before shutting down.
    - `curl http://localhost:8080/hello -X GET` - Command used to trigger the endpoint
- By default `@PreDestroy` can be used to hook a custom implementation after tomcat says all requests are processed and timeout has expired.

