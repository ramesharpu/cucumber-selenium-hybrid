@regression
Feature: This feature is used to test the page navigation
  user should be able to navigate to various pages in the amazon.in website

  @positive
  Scenario: Navigate to Wish List page and verify the page is displayed
    Given Browser is open
    When I want to go to Wish List page
    Then I verify that I am in Wish List page

  @positive
  Scenario: Navigate to Amazon Pay page and verify the page is displayed
    Given Browser is open
    When I want to go to Amazon Pay page
    Then I verify that I am in Amazon Page page

  @positive
  Scenario: Navigate to New Releases page and verify the page is displayed
    Given Browser is open
    When I want to go to New Releases page
    Then I verify that I am in New Releases page
