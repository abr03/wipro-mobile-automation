Feature: Product Cart Management

   @Regression
  Scenario: Verify user is able to add a product to the cart successfully
    Given the user is on the product detail page
    When the user validates the product specifications and price
    And the user adds the product to the cart
    Then the product should be successfully added to the cart view