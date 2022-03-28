Feature: Add/Delete address to user account on https://mystore-testlab.coderslab.pl

  Scenario Outline: Add address parameters to existing account
    Given Can open chrome to "https://mystore-testlab.coderslab.pl"
    When Take "<email>" and "<password>" to login user and add address "<alias>", "<address>", "<city>", "<zip/postal code>", "<phone>"
    And Check and delete Address
    Then User is logged in

    Examples:
    | email | password | alias | address | city | zip/postal code | phone |
    | tomaszflantowicz@coderslab.com | Test123 | TF | Kolejowa | Wroclaw | 51502 | 500600700 |