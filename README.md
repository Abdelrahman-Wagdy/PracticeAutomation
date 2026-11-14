# PracticeAutomation — SauceDemo Web Tests

A Selenium, Cucumber, and TestNG project that tests login and cart workflows on https://www.saucedemo.com/. It reuses the existing browser management and reporting framework while replacing the page services and scenarios for the unavailable practice.automationtesting.in target.

The original book-store implementation remains in Git history at commit `1e502cef6ffe5aa333f109e65e902f5e79169127`.

## Scenarios

| Scenario | Assertions |
| --- | --- |
| Valid login | Inventory is visible after signing in with the configured demo user |
| Invalid login | Exact error text is shown and the login page remains visible |
| Add a product to the cart | Exactly one item, the selected name, the inventory price, and quantity 1 |

Each scenario starts a new browser session. The cart scenario performs its own login and does not depend on another scenario. The default suite runs these three scenarios in Chrome and Firefox (six scenario executions).

## Requirements

- JDK 18 or newer capable of compiling the project's Java 18 target.
- Maven 3.9 recommended.
- Chrome and Firefox for the default suite, or only the browser selected for a single-runner execution.
- Network access to SauceDemo, Maven Central, and browser driver downloads.
- Optional Allure CLI for report viewing.

## Run

```bash
git clone https://github.com/Abdelrahman-Wagdy/PracticeAutomation.git
cd PracticeAutomation
mvn clean test -Dtest=ChromeTestRunner
```

Firefox only:

```bash
mvn clean test -Dtest=FirefoxTestRunner
```

Both browsers:

```bash
mvn clean test
```

Headless Chrome:

```bash
mvn clean test -Dtest=ChromeTestRunner -Dheadless=true
```

Cucumber step-binding check without opening a browser:

```bash
mvn clean test -Dtest=ChromeTestRunner -Dcucumber.execution.dry-run=true
```

A dry run verifies scenario bindings, not application behavior.

## Configuration

Defaults are in `src/test/resources/config.properties`. Java system properties override file values, for example `-Dheadless=true`.

| Property | Default / purpose |
| --- | --- |
| `url` | `https://www.saucedemo.com/` |
| `headless` | `false` |
| `username` | Public demo user `standard_user` |
| `password` | Public demo password `secret_sauce` |
| `chrome.binary` | Optional Chrome/Chromium executable path |
| `firefox.binary` | Optional Firefox executable path |

The tests assume a valid user with standard inventory behavior. The invalid-login scenario supplies its own deliberately incorrect credentials. These are public demo credentials; do not place production credentials in this project.

Chrome and Firefox runners can execute concurrently through TestNG's class-level setting. Scenarios within each runner execute sequentially, keeping browser concurrency predictable. Thread-local drivers and scenario teardown isolate sessions.

## Project map

| Location | Responsibility |
| --- | --- |
| `src/main/java/services/LoginPageServices.java` | Login form and error message |
| `src/main/java/services/InventoryPageServices.java` | Inventory, product selection, and cart navigation |
| `src/main/java/services/CartPageServices.java` | Cart contents |
| `src/main/java/support/` | Explicit waits, browser selection, and driver lifecycle |
| `src/main/java/utils/Config.java` | Classpath settings with system-property overrides |
| `src/test/resources/features/saucedemo.feature` | Three independent Gherkin scenarios |
| `src/test/java/stepdefs/SauceDemoSteps.java` | Assertions and scenario-specific values |
| `src/test/java/stepdefs/Hooks.java` | Fresh browser setup and cleanup |
| `src/test/java/runners/` | Cucumber/TestNG runners |
| `src/test/resources/testng.xml` | Default two-browser suite |

Page services use stable IDs/data-test attributes and explicit waits. Product selection matches the requested name; it does not assume the first inventory item is the correct product.

## Reports

- Browser-specific HTML/JSON: `target/cucumber-reports/`.
- TestNG/Surefire reports: `target/surefire-reports/`.
- Allure results: `target/allure-results/`.

```bash
allure serve target/allure-results
```

Generated outputs are ignored by Git. Allure CLI must be installed separately.

## Scope

SauceDemo is an external demo application, so availability, UI changes, and driver download failures can affect execution. This repository contains functional UI tests, not load tests. Docker packaging, CI workflows, checkout coverage, and failure screenshot attachments are future improvements.
