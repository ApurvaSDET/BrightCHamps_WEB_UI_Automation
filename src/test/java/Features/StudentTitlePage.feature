Feature: Student Portal Title page


  @Regression
  Scenario: 1 #Verifying login via email and password for Multiple a/c holder

    Given  User is at Student portal
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters valid email and password

      |Email|Password|
      |jayraj.kachariya@brightchamps.com|jayraj|

    And    User clicks on Sign-in CTA
    Then   User is redirected to the Home Page
