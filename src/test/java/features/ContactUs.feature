Feature: Submit data to webdriveruniversity.com using contact us form
  A user should be to sumbit information via the contact us form

  Background:
    Given I access webdriverunivsity.com
    When I click on the contact us button

  Scenario: Submit valid data via contact us form
    And I enter a valid first name
    And I enter a valid last name
      | woods | jackson | jones |
    And I enter a valid email address
    And I enter comments
      | example comment one | example comment two |
      | example comment one | example comment two |
    When I click on the submit button
    Then the information should successfully be submitted via the contact us form

  Scenario: Submit non valid data via contact us form
    And I enter a non valid first name
    And I enter a non valid last name
    And I enter a non valid email address
    And I enter no comments
    When I click on the submit button
    Then the information should not be successfully be submitted via the contact us form
    But the user should also be notified of the problem
