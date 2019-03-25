Feature: Application login

Scenario Outline: Login validation
Given Initilize browser
And Navigate to url "http://qaclickacademy.com"
And Click Login link
Then Enter <username> and <password> and log in
When Verify User successfully logged In
And Close Browser

Examples:
|username			|password	|
|ludadess@gmail.com	|Zelenaya1	|
|test@gmail.com		|12345		|