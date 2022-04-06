# Adobe & AEM Engineering Test

This is my submission for AEM Engineering Task.


## Getting started

Instructions for starting and building the application.

### Starting the project

#### Standalone with maven-wrapper
Run `./mvnw spring-boot:run` using a bash terminal inside the project directory.

#### Docker
1) First build the docker image as explained below.
2) Run `docker run --publish 8080:8080 adobe-task:latest`.

### Building the project

The project can either be build into an executable jar file with maven or directly into a docker image.

#### Standalone with Maven
Run `mvn clean package`.

#### Docker
Run `docker build --tag=adobe-task:latest .`

## Engineering methodology

Development was based on test-driven development. After becoming familiar with the specification of roman numerals, thoughts were given to which inputs should lead to which results. Based on this, all relevant tests for valid and invalid inputs were created (More below under Tests).

After that, the implementation of the corresponding web controller was started, which is able to provide the required endpoint.

Once that was done, the only thing left to do was to implement the necessary business logic for converting the numbers to roman numerals.
I decided to implement these as value objects. This allows in the future the easy implementation of manipulation methods like arithmetic operations and avoids the use of helper classes.

For the conversion I use a TreeMap, because it allows me to assign and retrieve the translations to the numbers very efficiently.
More on that is covered inside the inline comments.

Finally, the only thing that needs to be done is returning the correct JSON schema. Since Spring Boot already provides Jackson as dependency, I am able to return objects directly from a RestController. For this I use a corresponding helper object, the ConversionResult.


### Packaging layout

- `de.lucaadams.adobetask`: Main package
  - `exception`: Contains all custom exceptions
  - `model`: Contains all model classes which may be used in different business logics
  - `romannumeral`: Classes specific to the roman numeral conversion business logic

### Tests

Test cases for valid and invalid requests. In this simple application, it only makes sense to treat the entire process as an integration test and not break it down further into unit tests.

- RomanNumeralControllerTest: Test class, housing all tests for the RomanNumeralController
  - testNoParameter: no query parameter is specified
  - testValidNumber: first, "random" and last number from the valid range
  - testInvalidNumber: inputs query parameter that is no valid number syntax
  - testNegativeNumber: number smaller than the valid range
  - testLargeNumber: number larger than the valid range and larger than the Integer range

### Exception handling

Since it was not specified how error messages are to be returned, I use the standard error handling of Spring. For number inputs outside the allowed range I use a custom exception (NumberRangeExcpetion).


## Dependencies

The following public dependencies were actively used in this project.

### Application
- [Spring Boot Web](https://spring.io/projects/spring-boot): Framework for building the web service skeleton
  - [Tomcat](https://tomcat.apache.org): Embedded webserver container
  - [Jackson](https://github.com/FasterXML/jackson): Used for turning my output to valid JSON
  - _All other subdependencies of Spring Boot..._

### Development
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html): Tooling for more pleasant development experience
- [Lombok](https://projectlombok.org): Reduces boilerplate code
- [JUnit](https://junit.org/): Testing
