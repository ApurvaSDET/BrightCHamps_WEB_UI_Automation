Feature: Demo User
  Description: Test Automation for covering all possible cases of a Demo User

  Background: User is Logged In

    Given  User is at Student portal master login Page
    When   User enters valid Phone no and password

      |Phone     |Password|
      |5310000004|hr26ec2 |


    And    User clicks on SUBMIT CTA


  @Regression @Demo
  Scenario: 1 #Verifying master login for demo a/c

    Then   User is at the Home Page of Demo account

  @Regression @Demo @SmokeTest
  Scenario: 2 #Verifying Reschedule Your Class feature for demo user

    Given   User is at the Home Page of Demo account
    When    User Clicks on Reschedule class for demo user
    Then    User should be on Reschedule Trial class screen
    When    User selected next class date and time
    And     User clicks on Book your Slot CTA
    Then    User should be able to successfully Reschedule the demo class
    And     User should be redirected to dashboard screen of demo user

  @Regression @Demo
  Scenario: 3 #Verifying back button of Re-scheduling Trail Class screen

    Given   User is at the Home Page of Demo account
    When    User Navigate to the Reschedule screen
    Then    User should be on Reschedule Trial class screen
    When    User clicks on back button
    And     User should be redirected to dashboard screen of demo user

  @Regression @Demo
  Scenario: 4 #Verifying disabled JOIN CTA logic

    Given   User is at the Home Page of Demo account
    When    User has scheduled demo class
    Then    Verify JOIN CTA is enabled or disabled

  @Regression @Demo @DemoVideo
  Scenario: 5 #Verifying Glimpse of Project section on Demo Home Page

    Given   User is at the Home Page of Demo account
    When    User Scrolls down till Videos section on the Page
    Then    Verify user should be able to successfully play and close all the videos

  @Regression @Demo
  Scenario: 6 #Verifying ‘Start Creating’ CTA on Demo Home Page

    Given   User is at the Home Page of Demo account
    When    User clicks on Start Creating CTA
    Then    User should be redirected to Our-Curriculum Page


  @Regression1 @Demo1
  Scenario: 7 #Verifying ‘Apple App Store’ CTA on Demo Home Page

    Given   User is at the Home Page of Demo account
    When    User clicks on Apple AppStore CTA
    Then    User should be redirected to the Apple AppStore Screen

  @Regression1 @Demo1
  Scenario: 8 #Verifying ‘Google PlayStore’ CTA on Demo Home Page

    Given   User is at the Home Page of Demo account
    When    User clicks on Google PlayStore CTA
    Then    User should be redirected to the Google PlayStore Screen

  @Regression1 @Demo1
  Scenario: 9 #Verifying ‘Grab Your Seat’ CTA on Demo User

    Given   User is at the Home Page of Demo account
    When    User clicks on Grab Your Seat CTA
    Then    User should be redirected to Our-Curriculum Page

  @Regression @Demo @SmokeTest
  Scenario: 10 #Verifying logout for Demo User

    Given   User is at the Home Page of Demo account
    And     Clicks on Profile button
    Then    User is at Profile screen
    When    User clicks logout button
    Then    User is at Student Portal Title Page

  @Regression @LockedCertificate @Demo
  Scenario: 11 #Verifying locked Certificate for Demo User

    Given   User is at the Home Page of Demo account
    When    User Scrolls till Certificate section
    Then    Verify Certificate Should be locked for non completed users