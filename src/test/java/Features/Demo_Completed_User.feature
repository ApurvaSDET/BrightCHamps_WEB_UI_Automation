Feature: Demo Completed User
  Description: Test Automation for covering all possible cases of a Demo Completed User

  Background: User is Logged In

    Given  User is at Student portal master login Page
    When   User enters valid Phone no and password

      |Phone     |Password|
      |5210000011|hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of Demo Completed account


  @Regression @DemoCompleted
  Scenario: 1 #Verifying Demo Completed Banner at the top of the Dashboard

    Then   Demo Completed banner should be present at the top of the Dashboard


  Scenario: 2 #Verifying Free Scratch Course Video on the Demo Dashboard

    Then    Verify Video should be playing on the dashboard


  Scenario: 3 #Verifying Try Our Course CTA on the Demo Dashboard

    When    User clicks on Try our Course CTA
    Then    User should be redirected to Scratch Basics screen


  @Regression @DemoCompleted
  Scenario: 4 #Verifying Buy This Plan CTA of Accelerator Plan

    When    User clicks on Buy This Plan CTA of Accelerator Plan
    #Then    User should be redirected to Payment Gateway

  @Regression @DemoCompleted
  Scenario: 5 #Verifying Buy This Plan CTA of Achiever Plan

    When    User clicks on Buy This Plan CTA of Achiever Plan
    #Then    User should be redirected to Payment Gateway

  @Regression @DemoCompleted
  Scenario: 6 #Verifying Buy This Plan CTA of Champion Plan

    When    User clicks on Buy This Plan CTA of Champion Plan
    #Then    User should be redirected to Payment Gateway

  @Regression @DemoCompleted
  Scenario: 7 #Verifying Copy Link on Refer & Earn Page

    When    User Clicks on Refer & Earn tab
    Then    User is at the Refer & Earn Screen
    When    User clicks on Book Free Trial CTA of referral card
    Then    User should be navigated to the new tab with referral link in it
    When    User click on COPY LINK
    Then    Text on CTA should be changed to COPIED
    When    User opens a new tab and paste copied link in it
    Then    Verify Same URL should be there which is associated with Book Free Trial CTA

  @Regression @DemoCompleted
  Scenario: 8 #Verifying 'T&C' link on Refer & Earn Page

    When    User Clicks on Refer & Earn tab
    Then    User is at the Refer & Earn Screen
    When    User clicks on T&C link on Refer & Earn Page
    Then    User should be navigated to the new policy tab


  @Regression1 @DemoCompleted1
  Scenario: 9 #Verifying 'Facebook' CTA on Refer & Earn Page

    When    User Clicks on Refer & Earn tab
    Then    User is at the Refer & Earn Screen
    When    User clicks on Facebook link of referral card
    Then    Verify new tab with facebook link should get opened


  @Regression @DemoCompleted
  Scenario: 10 #Verifying 'WhatsApp' CTA on Refer & Earn Page

    When    User Clicks on Refer & Earn tab
    Then    User is at the Refer & Earn Screen
    When    User clicks on WhatsApp link of referral card
    Then    Verify new tab with WhatsApp link should get opened


  @Regression @DemoCompleted
  Scenario: 11 #Verifying Leaderboard section on Refer & Earn Page

    When    User Clicks on Refer & Earn tab
    Then    User is at the Refer & Earn Screen
    When    User scrolls down till Leaderboard section of referral page
    Then    Verify all the data on Leaderboard section

  @Regression @DemoCompleted
  Scenario: 12 #Verifying 'Join our community on Facebook' banner on the Dashboard

    When    User clicks on Facebook banner card
    Then    Verify new tab with facebook link should get opened

  @Regression1 @Demo
  Scenario: 13 #Verifying Student Testimonial section on the Demo Dashboard

    When    User clicks on Right Arrow
    Then    Testimonial Cards should Scroll to Left
    When    User clicks on Lefy Arrow
    Then    Testimonial Cards should Scroll to Right

  @Regression @DemoCompleted
  Scenario: 14 #Verifying 'Code-O-Fiesta' banner on the Dashboard

    When    User clicks on Contest Now CTA of Code-O-Fiesta banner
    Then    Verify user is redirected to the Code-O-Fiesta WebPage

  @Regression @Referral @SmokeTest
  Scenario: 15 #Verifying MacBook CTA on Home Page

    When    User clicks on Give Me MacBook CTA
    Then    User is at the Refer & Earn Screen

  @Regression @Demo @NewsArticle
  Scenario: 16 #Verifying News Article section on the Demo Dashboard

    When   User Scrolls down till News section
    And    User clicks on Read article link of all the completed class cards
    Then   News Link should get opened in the new tab

  @Regression @Demo @NewsArticle
  Scenario: 17 #Verifying Know More CTA under News Article section

    When   User Scrolls down till News section
    And    User clicks on Know More CTA
    Then   News Link should get opened in the new tab

  @Regression @Demo @Projects
  Scenario: 18 #Verifying Games under Project section

    When   User Scrolls down till Project section
    And    User clicks on Play CTA of all the Project cards
    Then   Projects Link should get opened in the new tab