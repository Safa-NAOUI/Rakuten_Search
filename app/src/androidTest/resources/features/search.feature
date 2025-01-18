Feature: Search

  Scenario: Search for a product
    Given the user is on the search screen
    When the user enters "samsung" in the search bar
    And the user taps the search button
    Then the user sees a list of products