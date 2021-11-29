Feature: Student Portal Title page

  Background: User is Logged In

    Given  User is at Student portal title Page
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters valid email and password

      |Email|Password|
      |apurva.kushwaha@brightchamps.com|qwerty|

    And    User clicks on Sign-in CTA
    Then   Referral Pop-up screen appears after login
    And    User dismiss referral modal


  @Regression
  Scenario: 1 #Verifying login via email and password for Single user a/c

    Then   User is at the Home Page of single user account

  @Regression
  Scenario: 2 #Verifying logout after login via email and password for Single user a/c

    And    Clicks on Profile button
    Then   User is at Profile screen
    When   User clicks logout button
    Then   User is at Student portal title Page



  @Regression
  Scenario: 3 #Verifying Re-scheduling Next Class

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Reschedule button
    Then   User is at Reschedule screen and Next class should be pre-selected
    When   User selected next class date and time
    Then   Book your Slot CTA should get enabled
    When   User clicks on Book your Slot CTA
    Then   User should be able to successfully Reschedule the class
    And    Reason asking for Reschedule should appear
    Then   User should be able to select the reason and click on Submit CTA
    And    User should be redirected to dashboard screen


  @Regression
  Scenario: 4 #Verifying Skip button of reschedule screen

    Given  User is at reschedule successful screen
    When   User clicks on Skip link
    Then   User should be redirected to dashboard screen


  @Regression
  Scenario: 5 #Verifying back button of reschedule screen

    Given  User is at reschedule successful screen
    When   User clicks on back button
    Then   User should be redirected to dashboard screen


  @Regression
  Scenario: 6 #Verifying Re-scheduling All Class

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Reschedule button
    Then   User is at Reschedule screen
    And    User selects All Class tab
    Then   By default three class per week should be shown
    When   User selects classes one by one
    Then   User should see the option to select date amd time based on number of classes selected
    When   User selected all four classes date and time
    Then   Book your Slot CTA should get enabled
    When   User clicks on Book your Slot CTA
    Then   User should be able to successfully Reschedule the class
    And    Reason asking for Reschedule should appear
    Then   User should be able to select the reason and click on Submit CTA
    And    User should be redirected to dashboard screen


  @Regression
  Scenario: 7 #Verifying Cancel Class

