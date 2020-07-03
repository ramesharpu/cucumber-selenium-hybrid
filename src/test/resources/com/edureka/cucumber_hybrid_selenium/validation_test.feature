@regression
Feature: Basic Validation for amazon site
  User should be able to see url redirection when valid short url is entered
  User should be able to validate the page title as “Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in”

  @positive
  Scenario: url redirection
    Given Browser is open
    When enter the short url "http://amzn.in"
    Then validate the amazon url

  @positive
  Scenario: url redirection
    Given Browser is open
    Then Validate the page title
