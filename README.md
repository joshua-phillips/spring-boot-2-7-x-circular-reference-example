# spring-boot-2-7-x-circular-reference-example

Example project reproducing circular reference error in Spring Boot with dependencies `spring-boot-starter-data-jpa` and `spring-boot-starter-actuator` with tested versions 2.7.10 and 2.7.11 when upgrading from version 2.6.13.

When injecting a `RestTemplateBuilder` or `RestTemplate` class instance to either a Configuration class constructor with a `getDataSource` Bean or directly to the `getDataSource` Bean method a circular reference error is raised at run-time.

Adding the `spring.main.allow-circular-references=true` setting to the **application.properties** file resolves the error and allows the app to run successfully.

This code example is already set so that it will fail at run-time starting the application.

It will run successfully by either commenting and uncommenting the `getDataSource` methods shown here in the `DemoApplication` class.

```java
@Bean
public DataSource getDataSource(RestTemplate restTemplate) {
// public DataSource getDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    dataSourceBuilder.url("<DB URL>");
    dataSourceBuilder.username("<DB USER NAME>");
    dataSourceBuilder.password("DB PASSWORD");
    return dataSourceBuilder.build();
}
```

Or by uncommenting the `spring.main.allow-circular-references=true` setting in the **application.properties** file.
