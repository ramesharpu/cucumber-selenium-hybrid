@regression
Feature: This feature is used to test valid and invalid login scenarios
  I want to use this feature for valid login testing
  I want to use this feature for invalid login testing

  @negative
  Scenario Outline: This scenario is used to test various invalid inputs for login feature
    Given I am in login page
    When I enter the invalid credentials "<value>"
    Then I verify the login status as <status>

    Examples: 
      | name                  | value                | status |
      | invalid email-id      | invalid$5s5@jkil.com | Fail   |
      | invalid mobile-number |           9886098860 | Fail   |