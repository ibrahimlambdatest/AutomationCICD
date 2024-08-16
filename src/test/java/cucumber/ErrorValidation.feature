
@tag
Feature: Validating Error Message on the login page
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I land on the ecommerce page
    When Logged in with username <usrname> and password <password>
    Then "Incorrect email or password." is displayed on the login page

    Examples: 
      | usrname  									| password 						|
      | ibrahim.said@gmail.com 		|     @Ibrahim12	 		|
