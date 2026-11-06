@smoke
Feature: SauceDemo login and shopping cart
  Each scenario starts with a fresh browser session and an empty cart.

  Background:
    Given the SauceDemo login page is displayed

  Scenario: Valid credentials display the inventory
    When I log in with the configured valid credentials
    Then the inventory page is displayed

  Scenario: Invalid credentials display the expected error
    When I log in with username "invalid_user" and password "invalid_password"
    Then the login error is "Epic sadface: Username and password do not match any user in this service"

  Scenario: Add a product and verify it in the cart
    When I log in with the configured valid credentials
    Then the inventory page is displayed
    When I add "Sauce Labs Backpack" to the cart
    And I open the cart
    Then the cart contains one "Sauce Labs Backpack" at its inventory price
