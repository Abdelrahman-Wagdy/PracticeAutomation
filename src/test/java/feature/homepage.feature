Feature: Check the item in the home page
  Background:
    Given that the user opens the webpage successfully
    And that the user scrolled to the new arrivals section

  Scenario: Check that the Thinking in HTML book exist along with its price
    Then the "Thinking in HTML" book should exist
    And the price should be "₹400.00"

  Scenario: Add the Book to the basket and complete the checkout process
    Given the "Thinking in HTML" book should exist
    When the user adds it to basket
    And the user opens the shopping cart
    Then "Thinking in HTML" should appear in the cart priced "₹400.00"
    When the user clicks on proceed to checkout
    Then the billing details form is displayed


