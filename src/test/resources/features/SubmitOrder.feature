Feature: Purchase the order from E-commerce website 

Background: 
	Given I landed on Ecommerce page 
	
@selenium 
Scenario Outline: Positive test of submitting the order 
	Given Logged in with username "<username>" and password "<password>" 
	When I add product "<productName>" to Cart 
	And Checkout "<productName>" and Submit the order 
	Then "Thankyou for the order." message is displayed on confirmation page 
	
	Examples: 
		| username                  | password   | productName   |
		| rohit.gouda123@gmail.com  | Rohit@123  | IPHONE 13 PRO |
		
	