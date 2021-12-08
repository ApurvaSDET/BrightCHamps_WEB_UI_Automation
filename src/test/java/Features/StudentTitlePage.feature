Feature: Home Page Part 1
  Description: Test Automation for Login, Logout, Rescheduling and cancellation on Student Portal

  Background: User is Logged In

    Given  User is at Student portal title Page
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters valid email and password

      |Email|Password|
      |prathyusha.m@brightchamps.com|prat2021|
      |apurva.kushwaha@brightchamps.com|qwerty|

    And    User clicks on Sign-in CTA


  @Regression @SmokeTest
  Scenario: 1 #Verifying login via email and password for Single user a/c

    Then   User is at the Home Page of single user account

  @Regression @SmokeTest
  Scenario: 2 #Verifying logout after login via email and password for Single user a/c

    And    Clicks on Profile button
    Then   User is at Profile screen
    When   User clicks logout button
    Then   User is at Student portal title Page


  @Regression @SmokeTest
  Scenario: 3 #Verifying back button of Re-scheduling Next Class screen

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Reschedule button
    Then   User is at Reschedule screen and Next class should be pre-selected
    When   User clicks on back button
    Then   User should be redirected to dashboard screen


  @Regression
  Scenario: 4 #Verifying Re-scheduling Next Class

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
  Scenario: 5 #Verifying Skip button of rescheduled screen

    Given  User is at reschedule successful screen
    When   User clicks on Skip link
    Then   User should be redirected to dashboard screen


  @Regression
  Scenario: 6 #Verifying back button of rescheduled screen

    Given  User is at reschedule successful screen
    When   User clicks on back button
    Then   User should be redirected to dashboard screen


  @Regression1
  Scenario: 7 #Verifying Re-scheduling All Class

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


  @Regression @SmokeTest
  Scenario: 8 #Verifying X button on Cancel Class modal

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    When   User clicks on X button
    Then   Cancel modal should get closed


  @Regression @SmokeTest
  Scenario: 9 #Verifying No button on Cancel Class modal

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    When   User clicks on NO button
    Then   Cancel modal should get closed


  @Regression @SmokeTest
  Scenario: 10 #Verifying YES button on Cancel Class modal without accepting the student policy

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    And    YES button should be disabled

  @Regression @SmokeTest
  Scenario: 11 #Verifying student policy link on Cancel Class modal

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    When   User clicks on student policy link
    Then   A web page with student policy should get opened

  @Regression @SmokeTest
  Scenario: 12 #Verifying YES button on Cancel Class modal after accepting the student policy

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    When   User selects the checkbox to accept the agreement
    Then   YES button should be enabled
    When   User clicks on YES button
    Then   User should be able to successfully cancel the class
    And    Reason asking for Cancel should appear
    Then   User should be able to select the cancel reason and click on Submit CTA
    And    Successful cancel modal should get closed and Webpage should reload


  @Regression @SmokeTest
  Scenario: 13 #Verifying Skip button of Successful cancel screen

    Given  User is at cancel successful screen
    When   User clicks on Skip link
    Then   Successful cancel modal should get closed and Webpage should reload


  @Regression @SmokeTest
  Scenario: 14 #Verifying X button of Successful cancel screen

    Given  User is at cancel successful screen
    When   User clicks on X button
    #Then   Successful cancel modal should get closed and Webpage should reload
           #https://brightchamps.atlassian.net/browse/STUD-199