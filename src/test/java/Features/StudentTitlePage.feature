Feature: Home Page Part 1
  Description: Test Automation for Login, Logout, Rescheduling and cancellation on Student Portal

  Background: User is Logged In

    Given  User is at Student portal title Page
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters valid email and password

      |Email|Password|
      |apurva.kushwaha@mailinator.com|qwerty  |
      |prathyusha.m@brightchamps.com |prat2021|

    And    User clicks on Sign-in CTA


  @Regression @SmokeTest
  Scenario: 1 #Verifying login via email and password for Single user a/c

    Then   User is at the Home Page of single user account

  @Regression @SmokeTest
  Scenario: 2 #Verifying logout after login via email and password for Single user a/c

    And    Clicks on Profile button
    Then   User is at Profile screen
    When   User clicks logout button
    Then   User is at Student Portal Title Page


  @Regression @Reschedule
  Scenario: 3 #Verifying back button of Re-scheduling Next Class screen

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Reschedule button
    Then   User is at Reschedule screen and Next class should be pre-selected
    When   User clicks on back button
    Then   User should be redirected to dashboard screen


  @Regression @Reschedule @SmokeTest
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


  @Regression @Reschedule @SmokeTest
  Scenario: 5 #Verifying Skip button of rescheduled screen

    Given  User is at reschedule successful screen
    When   User clicks on Skip link
    Then   User should be redirected to dashboard screen


  @Regression @Reschedule
  Scenario: 6 #Verifying back button of rescheduled screen

    Given  User is at reschedule successful screen
    When   User clicks on back button
    Then   User should be redirected to dashboard screen


  @Regression @Reschedule @SmokeTest
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
    And    User should be redirected to dashboard screen


  @Regression @Cancel
  Scenario: 8 #Verifying No button on Cancel Class modal

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    When   User clicks on NO button
    Then   Cancel modal should get closed


  @Regression @Cancel
  Scenario: 9 #Verifying YES button on Cancel Class modal without accepting the student policy

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    And    YES button should be disabled

  @Regression @Cancel
  Scenario: 10 #Verifying student policy link on Cancel Class modal

    When   User Clicks on three dots button
    Then   Dropdown should appear
    When   User Selects Cancel button
    Then   Cancel modal should appear
    When   User clicks on student policy link
    Then   A web page with student policy should get opened

  @Regression @Cancel @SmokeTest
  Scenario: 11 #Verifying YES button on Cancel Class modal after accepting the student policy

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


  @Regression @Cancel @SmokeTest
  Scenario: 12 #Verifying Skip button of Successful cancel screen

    Given  User is at cancel successful screen
    When   User clicks on Skip link of Cancel Modal
    Then   Successful cancel modal should get closed and Webpage should reload


  @Regression @Smoke
  Scenario: 13 #Verifying disabled JOIN CTA logic

    Given   User is at the Home Page of single user account
    When    User has an Upcoming Appointment
    #Then    Verify JOIN CTA is enabled or disabled

  @Regression @GlobalHouse
  Scenario: 14 #Verifying global House CTA

    Given   User is at the Home Page of single user account
    When    User clicks on Confirm Now CTA of global house card
    Then    User is redirected to Global House Screen
    When    User selects all the Preference
    Then    Book your Slot CTA should get enabled
    When    User clicks on Book your Slot CTA
    #Then    Success alert message should appear
    And     User should be redirected to dashboard screen
    Then    Verify Congratulations! message on Global House Card
    And     Verify Confirm Now CTA is not visible anymore

  @Regression @Referral @SmokeTest
  Scenario: 15 #Verifying referral modal on Home Page of paid user

    Given   User is at the Home Page of single user account
    When    User clicks on Book Free Trial CTA of referral card
    Then    User should be navigated to the new tab with referral link in it
    When    User click on COPY LINK
    Then    Text on CTA should be changed to COPIED
    When    User opens a new tab and paste copied link in it
    Then    Verify Same URL should be there which is associated with Book Free Trial CTA

  @Regression @KnowMore @Referral
  Scenario: 16 #Verifying 'Know More' CTA on referral modal

    Given   User is at the Home Page of single user account
    When    User clicks on 'Know More' CTA of referral card
    Then    User should be navigated to the new policy tab


  @Regression @Facebook @Referral
  Scenario: 17 #Verifying 'Facebook' link on referral modal

    Given   User is at the Home Page of single user account
    When    User clicks on Facebook link of referral card
    Then    Verify new tab with facebook link should get opened


  @Regression @WhatsApp @Referral
  Scenario: 18 #Verifying 'WhatsApp' link  on referral modal

    Given   User is at the Home Page of single user account
    When    User clicks on WhatsApp link of referral card
    Then    Verify new tab with WhatsApp link should get opened


  @Regression @Leaderboard @Referral
  Scenario: 19 #Verifying Leaderboard section on referral modal

    Given   User is at the Home Page of single user account
    When    User clicks on Leaderboard section of referral card
    Then    Leaderboard section should gets opened
    And     Verify all the data on Leaderboard section


  @Regression @UpcomingClasses @SmokeTest
  Scenario: 20 #Verifying 'Check your schedule classes' expand button

    Given   User is at the Home Page of single user account
    When    User clicks on Check your schedule classes expand button
    Then    Upcoming classes should appear
    When    User clicks on Hide your schedule class button
    Then    Upcoming classes should disappear