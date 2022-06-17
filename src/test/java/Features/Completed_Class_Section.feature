Feature: Home Page Part 2 - Completed Class Section
  Description: Test Automation for covering all possible cases of a Completed Class Section for Paid users on Home Page

  Background: User is Logged In

    Given  User is at Student portal title Page
    When   User clicks on 'Login with Password' CTA
    Then   User is at 'Login with Password' screen
    When   User enters valid email and password

      |Email|Password|
      |apurva.kushwaha@mailinator.com|qwerty |
      |prathyusha.m@brightchamps.com|prat2021|

    And    User clicks on Sign-in CTA

  @Regression1 @CompletedClassCard
  Scenario: 1 #Verifying SESSION BOOKLET links on Completed Class Section

    Then   User is at the Home Page of single user account
    When   User clicks on SESSION BOOKLET link of all the completed class cards
    Then   Link should get opened in the new tab

  @Regression1 @CompletedClassCard
  Scenario: 2 #Verifying ASSIGNMENT links on Completed Class Section

    Then   User is at the Home Page of single user account
    When   User clicks on ASSIGNMENT link of all the completed class cards
    Then   Link should get opened in the new tab