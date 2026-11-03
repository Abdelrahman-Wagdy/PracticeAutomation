# PracticeAutomation — Web UI Testing

A Java, Selenium, Cucumber, and TestNG portfolio project for https://practice.automationtesting.in/. Browser-specific runners execute scenarios on Chrome and Firefox, with page services separating UI actions from step definitions.

## Requirements

- JDK 18 or newer capable of compiling the Java 18 target configured in `pom.xml`.
- Maven 3.9 recommended.
- Chrome and Firefox installed for the default suite; only the selected browser is needed for a single-runner execution.
- Network access to the demo website, Maven dependencies, and driver downloads.
- A graphical desktop: the current driver setup does not enable headless mode.

## Quick start

```bash
git clone https://github.com/Abdelrahman-Wagdy/PracticeAutomation.git
cd PracticeAutomation
mvn clean test
```

The default suite is `src/test/resources/testng.xml`. It includes `ChromeTestRunner` and `FirefoxTestRunner` with class-level parallelism. Both runners also enable parallel scenario data providers; the configured class thread count is not a strict cap on total browser sessions.

For a smaller first run:

```bash
mvn clean test -Dtest=ChromeTestRunner
mvn clean test -Dtest=FirefoxTestRunner
```

Edit application settings in `src/test/resources/config.properties`. The current loader reads that file into system properties, so file values can override command-line values.

## Project map

| Location | Responsibility |
| --- | --- |
| `src/main/java/services/` | Home, cart, and billing page services |
| `src/main/java/support/` | Browser actions, browser selection, and thread-local drivers |
| `src/main/java/utils/Config.java` | Configuration loading |
| `src/test/java/feature/` | Gherkin feature files |
| `src/test/java/stepdefs/` | Step definitions and browser lifecycle hooks |
| `src/test/java/runners/` | Cucumber runners |
| `src/test/resources/testng.xml` | Default two-browser suite |

## Reports

- Browser-specific HTML/JSON: `target/cucumber-reports/`.
- Surefire execution reports: `target/surefire-reports/`.
- Allure results: `target/allure-results/`.

With Allure CLI installed:

```bash
allure serve target/allure-results
```

Generated reports are excluded from Git. Add a screenshot from an actual run when demonstrating results; do not use a passing badge until automated checks exist.

## Add a scenario

1. Add a feature under `src/test/java/feature/`.
2. Implement its steps under `src/test/java/stepdefs/`.
3. Put reusable locators and UI actions in the relevant class under `services/`.
4. Run the affected browser runner, then the full suite.

## Scope and planned improvements

Docker packaging and CI workflows are not included in this repository. There is no `DockerHelper` or `ReportMerger` implementation.

Next improvements: failure screenshot attachments, configurable headless execution, predictable concurrency, explicit waits for unstable interactions, and a CI smoke run. These are planned capabilities, not completed features.
