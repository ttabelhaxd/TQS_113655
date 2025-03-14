Feature: Search for books in the online library

  Scenario: Search for a book by title
    Given I am on the online library homepage
    When I search for the book "Harry Potter"
    Then I should see the book "Harry Potter and the Sorcerer's Stone" by "J. K. Rowling"

  Scenario: Search for a book and view details
    Given I am on the online library homepage
    When I search for the book "Harry Potter"
    And I click on the book "Harry Potter and the Sorcerer's Stone"
    Then I should see the book details for "Harry Potter and the Sorcerer's Stone"