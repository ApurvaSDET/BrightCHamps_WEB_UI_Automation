Feature: Home Page Part 3b - Multiple Paid Users with in a Single a/c
  Description: Test Automation for Multiple users under single a/c for Paid user.

  Background: User is Logged In

    Given  User is at Student portal master login Page
    When   User enters valid Phone no and password

      |Phone     |Password|
      |9993550809|hr26ec2 |
      #This is a Prod User.

    And    User clicks on SUBMIT CTA


  @Regression @MultiplePaidUsers
  Scenario: 1 #Verifying Select Your Account Screen

    Given   User is at the Select Your Account Screen


  @Regression @MultiplePaidUsers
  Scenario: 2 #Verifying User lands on the same a/c which is selected at Select Your Account Screen

    Given   User is at the Select Your Account Screen
    When    User clicks on each paid account
    Then    User should be redirected to their selected paid account only

  @Regression @MultiplePaidUsers
  Scenario: 3 #Verifying User lands on the same a/c when selected from dropdown on HomePage

    Given   User is at the Select Your Account Screen
    When    User clicks on any account
    Then    User lands on Home Page of Paid User
    When    User selects each account from dropdown on Profile Page
    Then    Verify selected account Profile Page should be opened