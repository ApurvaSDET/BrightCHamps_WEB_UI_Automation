Feature: Student Portal Title page


  @Regression
  Scenario: 1 #Verifying login via email and password for Single user a/c

    Given  User is at Student portal
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters valid email and password

      |Email|Password|
      |apurva.kushwaha@brightchamps.com|qwerty|

    And    User clicks on Sign-in CTA
    Then   Referral Pop-up screen appears after login
    When   User dismiss referral modal
    Then   User is redirected to the Home Page of single user account
