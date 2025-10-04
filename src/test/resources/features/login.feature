Feature: Login functionality for an e-commerce site

 As a user of the shopping cart website 
 I want to login with valid credentials 
 and validate I am not able to login with Invalid credentials

 Background: 
   Given I am able to view the login page
 
 Scenario: Successful login with valid credentials
   Given I have entered valid username and password
   When I click on login button
   Then I am able to login
 
 Scenario Outline: Unsuccessful user credentials
   Given I have entered invalid "<username>" and "<password>"
   When I click on login button
   Then I should see error message "<message>"
   
   Examples:
     | username | password | message |
     | rk | invalid | Epic sadface: Username and password do not match any user in this service |
     | xyz@gmail.com | infinite | Epic sadface: Username and password do not match any user in this service |
     | rk | secret_sauce | Epic sadface: Username and password do not match any user in this service |
  
 

