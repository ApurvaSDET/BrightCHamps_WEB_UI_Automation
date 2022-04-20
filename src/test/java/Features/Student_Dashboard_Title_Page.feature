Feature: Student Dashboard Title Page
  Description: 1. Test Automation for Login via OTP using Email, Mobile.
               2. Test Automation Forgot Password
               3. Negative test cases for login scenarios.


  @Regression @TitlePage @SmokeTest
  Scenario: 1 #Verifying login via OTP using Email for paid user

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

  @Regression @TitlePage @SmokeTest
  Scenario: 2 #Verifying login via OTP using Email for demo user

    Given  User is at Student portal title Page
    When   User clicks on Email button
    And    User enters valid Email address

      |Email|
      |kishan.patel@mailinator.com|

    When   User clicks on Login CTA
    Then   OTP sent Successfully message should appear
    And    User lands on Enter Verification Code Screen
    When   User enters OTP
    And    Clicks on Verify OTP CTA
    #Then   Authenticated successfully message should appear
    And    User is at the Home Page of Demo account

  @Regression @TitlePage
  Scenario: 3 #Verifying login via OTP using Mobile - Negative Test case

    Given  User is at Student portal title Page
    When   User clicks on Mobile button
    And    User enters Invalid Mobile no

      |Mobile|
      |12345|
      |7310000021|

    When   User clicks on Login CTA
    Then   Error message should appear
    When   User enters valid Mobile no

      |Mobile|
      |8130865152|

    And    User clicks on Login CTA
    Then   OTP sent Successful message should appear
    And    User lands on Enter Verification Code Screen
    When   User Clicks on Send again link
    Then   OTP resend message should appear
    When   User enters Invalid OTP
    And    Clicks on Verify OTP CTA
    Then   Incorrect OTP message should appear

  @Regression @TitlePage
  Scenario: 4 #Verifying login via OTP using Email - Negative Test case

    Given  User is at Student portal title Page
    When   User clicks on Email button
    And    User enters Invalid Email address

      |Email|
      |apurva.kushwaha|
      |apurva.kushwaha@gmail.com|


    When   User clicks on Login CTA
    Then   Error message should be shown
    When   User enters valid Email address

      |Email|
      |apurva.kushwaha@mailinator.com|

    And    User clicks on Login CTA
    Then   OTP sent Successfully message should appear
    And    User lands on Enter Verification Code Screen
    When   User Clicks on Send again link
    Then   OTP sent Successfully message should appear
    When   User enters Invalid OTP
    And    Clicks on Verify OTP CTA
    Then   Incorrect OTP message should appear


  @Regression @TitlePage
  Scenario: 5 #Verifying older OTP can’t be used after resending new one

    Given  User is at Student portal title Page
    When   User clicks on Email button
    And    User enters valid Email address

      |Email|
      |apurva.kushwaha@mailinator.com|

    When   User clicks on Login CTA
    Then   OTP sent Successfully message should appear
    And    User lands on Enter Verification Code Screen
    When   User enters OTP
    And    User Clicks on Send again link
    Then   OTP sent Successfully message should appear
    And    Clicks on Verify OTP CTA
    Then   Incorrect OTP message should appear

  @Regression @TitlePage
  Scenario: 6 #Verifying login via Resent OTP using Email

    Given  User is at Student portal title Page
    When   User clicks on Email button
    And    User enters valid Email address

      |Email|
      |apurva.kushwaha@mailinator.com|

    When   User clicks on Login CTA
    Then   OTP sent Successfully message should appear
    And    User lands on Enter Verification Code Screen
    And    User Clicks on Send again link
    Then   OTP sent Successfully message should appear
    When   User enters OTP
    And    Clicks on Verify OTP CTA
    #Then   Authenticated successfully message should appear
    And    User is at the Home Page of single user account

  @Regression @TitlePage
  Scenario Outline: 7 #Verifying login with E-mail/Passwords - All negative test cases

    Given  User is at Student portal title Page
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters "<Invalid Email>" and "<Password>" combination
    And    User clicks on SIGN IN CTA
    Then   Proper "<Validation Message>" should appear

    Examples:
      |Invalid Email                    |Password |Validation Message                         |
      |apur.kushwaha@brightchamps.com   |qwerty   |User with that email does not exist.       |
      |                                 |qwerty   |Must be a valid email address              |
      |apurva.kushwaha@mailinator.com   |         |Invalid value                              |
      |apurva.kushwaha                  |qwerty   |User with that email does not exist.       |
      |apurva.kushwaha@mailinator.com   |password |Invalid email and password                 |
      |apurva.kushwaha@brightchamps.com |qwer     |Password must be at least 6 characters long|



  @Regression @TitlePage
  Scenario: 8 #Verifying Forgot Password feature at client end

    Given  User is at Student portal title Page
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User clicks on forgot password link
    Then   User is redirected to forgot password screen
    When   User enters valid Email address

      |Email|
      |apurva.kushwaha@mailinator.com|

    And    Click on RESET PASSWORD CTA
    Then   Success alert should appear
    And    User gets redirected to the title page
