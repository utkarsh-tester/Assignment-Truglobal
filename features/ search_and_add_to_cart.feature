Feature: Search and Add to Cart on Amazon.in and

  Scenario: Search iPhone and Add to Cart
    Given User is on Amazon.in homepage
    When User searches for "iphone"
    And User selects the first result
    And User goes to the product details page and stores the price in a variable
    And User adds the product to the cart
    Then Product should be added to the cart successfully
