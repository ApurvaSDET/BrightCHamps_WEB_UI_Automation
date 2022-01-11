Feature: Home Page Part 3a - Multiple Demo Users with in a Single a/c
  Description: Test Automation for Multiple users under single a/c for Demo user.


  Background: User is Logged In

    Given  User is at Student portal master login Page
    When   User enters valid Phone no and password

      |Phone     |Password|
      |7310000088|hr26ec2 |


    And    User clicks on SUBMIT CTA


  @Regression @MultipleDemoUsers
  Scenario: 1 #Verifying Select Your Account Screen

    Given   User is at the Select Your Account Screen


  @Regression @MultipleDemoUsers
  Scenario: 2 #Verifying User lands on the same a/c which is selected at Select Your Account Screen

    Given   User is at the Select Your Account Screen
    When    User clicks on each account
    Then    User should be redirected to their selected account only

  @Regression1 @MultipleDemoUsers
  Scenario: 3 #Verifying User lands on the same a/c when selected from dropdown on HomePage

    Given   User is at the Select Your Account Screen
    When    User clicks on any account
    Then    User lands on Home Page
    When    User select each account from dropdown
    Then    Verify selected account Home Page should be opened