Feature: Login to Umarkets Trading
  In order to get access data to trading account
  As a new lead user og Umarkets
  I want to get json file with response data after login to Umarkets Trading account

  Scenario: Get json after login to trading umarkets
    Given User's registered in the system
    And User logged out from the system
    When user gets special token
    Then user can log into the other related system

