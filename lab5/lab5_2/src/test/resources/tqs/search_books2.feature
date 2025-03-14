Feature: Book search2
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given the following books
      | title             | author         | published   |
      | One good book     | Anonymous      | 2013-03-14  |
      | Some other book   | Tim Thomson     | 2014-08-23  |
      | How to cook a dino| Fred Flintstones| 2012-01-01  |
    When the customer searches for books published between 2013-01-01 and 2014-12-31
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'One good book'

  Scenario: Search books by author
    Given the following books
      | title             | author         | published   |
      | One good book     | Anonymous      | 2013-03-14  |
      | Some other book   | Tim Thomson     | 2014-08-23  |
      | How to cook a dino| Fred Flintstones| 2012-01-01  |
    When the customer searches for books by author 'Anonymous'
    Then 1 books should have been found
    And Book 1 should have the title 'One good book'