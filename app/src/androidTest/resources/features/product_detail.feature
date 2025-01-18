Feature: Product Detail
  Scenario: View product details
    Given the user is on the search screen
    When the user taps on a product from the list
    Then the user is taken to the product detail screen
    And the user sees the product title, best price, and description