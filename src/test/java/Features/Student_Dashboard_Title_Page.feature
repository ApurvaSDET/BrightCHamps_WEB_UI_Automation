Feature: Student Dashboard Title Page
  Description: 1. Test Automation for Login via OTP using Email, Mobile.
               2. Test Automation Forgot Password
               3. Negative test cases for login scenarios.


  @abc
  Scenario: 1 #Verifying login via OTP using Email

    Given  User is at Student portal title Page
    When   User clicks on Email button
    And    User enters valid Email address

           |Email|
           |apurva.kushwaha@mailinator.com|

    When   User clicks on Login CTA
    Then   OTP sent Successfully message should appear
    And    User lands on Enter Verification Code Screen
    When   User enters OTP
    And    Clicks on Verify OTP CTA
    #Then   Authenticated successfully message should appear
    And    User is at the Home Page of single user account