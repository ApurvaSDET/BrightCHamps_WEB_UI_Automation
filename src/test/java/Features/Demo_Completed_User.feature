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

  @Regression @DemoCompleted
  Scenario: 2 #Verifying Free Scratch Course Video on the Demo Dashboard

    Then    Verify Video should be playing on the dashboard

  @Regression @DemoCompleted
  Scenario: 3 #Verifying Try Our Course CTA on the Demo Dashboard

    When    User clicks on Try our Course CTA
    Then    User should be redirected to Scratch Basics screen


  @Regression @DemoCompleted
  Scenario: 4 #Verifying Buy This Plan CTA of Accelerator Plan

    When    User clicks on Buy This Plan CTA of Accelerator Plan
    Then    User should be redirected to Payment Gateway

  @Regression @DemoCompleted
  Scenario: 5 #Verifying Buy This Plan CTA of Achiever Plan

    When    User clicks on Buy This Plan CTA of Achiever Plan
    Then    User should be redirected to Payment Gateway

  @Regression @DemoCompleted
  Scenario: 6 #Verifying Buy This Plan CTA of Champion Plan

    When    User clicks on Buy This Plan CTA of Champion Plan
    Then    User should be redirected to Payment Gateway

  @Regression @DemoCompleted
  Scenario: 7 #Verifying referral modal on Home Page of paid user

    When    User clicks on Book Free Trial CTA of referral card
    Then    User should be navigated to the new tab with referral link in it
    When    User click on COPY LINK
    Then    Text on CTA should be changed to COPIED
    When    User opens a new tab and paste copied link in it
    Then    Verify Same URL should be there which is associated with Book Free Trial CTA

  @Regression @DemoCompleted
  Scenario: 8 #Verifying 'Know More' CTA on referral modal

    When    User clicks on 'Know More' CTA of referral card
    Then    User should be navigated to the new policy tab


  @Regression @DemoCompleted
  Scenario: 9 #Verifying 'Facebook' link on referral modal

    When    User clicks on Facebook link of referral card
    Then    Verify new tab with facebook link should get opened


  @Regression @DemoCompleted
  Scenario: 10 #Verifying 'WhatsApp' link  on referral modal

    When    User clicks on WhatsApp link of referral card
    Then    Verify new tab with WhatsApp link should get opened


  @Regression @DemoCompleted
  Scenario: 11 #Verifying Leaderboard section on referral modal

    When    User clicks on Leaderboard section of referral card
    Then    Leaderboard section should gets opened
    And     Verify all the data on Leaderboard section

  @Regression @DemoCompleted
  Scenario: 12 #Verifying 'Join our community on Facebook' banner on the Dashboard

    When    User clicks on Facebook banner card
    Then    Verify new tab with facebook link should get opened

  @Regression @DemoCompleted
  Scenario: 13 #Verifying ‘Our Students love us’ section on the Demo Dashboard

    When    User clicks on Right Arrow
    Then    Testimonial Cards should Scroll to Left
    When    User clicks on Lefy Arrow
    Then    Testimonial Cards should Scroll to Right
