Feature: Shopping on a "https://mystore-testlab.coderslab.pl"
  Scenario Outline: Full shopping service for exixting accounts
    Given Can open chrome with "https://mystore-testlab.coderslab.pl"
    When Take "<email>" and "<password>" to login and add address "<alias>", "<address>", "<city>", "<zip/postal code>", "<phone>"
    And Buy sweater, check "<size>", "<quantity>", address
    And Pickup and payment
    Then Check user order and clean (delete address), user is logged in

    Examples:
      | email | password | alias | address | city | zip/postal code | phone | size | quantity |
      | tomaszflantowicz@coderslab.com | Test123 | TF | Kolejowa | Wroclaw | 51502 | 500600700 | S | 5 |