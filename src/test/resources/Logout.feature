@UPGN-Logout
Feature: As a user, I should be able to log out

  Background:
    Given user is on the dashboard page

  @UPGN-784
  Scenario: User logs out
    When user clicks the logout button
    Then user sees the login page

  @UPGN-785
  Scenario: User can not go to the dashboard page again by step back button after logged out
    When user clicks the logout button
    And user clicks the <Step Back_Previous Page> button
    And user sees the <session expired message>
    Then user sees the login page

  @UPGN-786
  Scenario: User logs out by closing tab
    When user clicks the tab close button
    Then user sees the login page