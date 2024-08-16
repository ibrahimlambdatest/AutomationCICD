	@tag
	Feature: Submit Order for purchase on the ecommerce site
  I want to use this template for my feature file

	Background:
	Given I land on the ecommerce page

  @Regression
  Scenario Outline: Submit Order
    Given Logged in with username <usrname> and password <password>
    When I add product <productName> to cart
    And checkout and submit order
    Then "THANKYOU FOR THE ORDER." is displayed on the confirmation page

    Examples: 
      | usrname  									| password 						| productName	  	|
      | ibrahim.said@gmail.com 		|     @Ibrahim123 		| ZARA			 			|
  