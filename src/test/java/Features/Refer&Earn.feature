Feature: Paid Users Refer & Earn Page
  Description: Test Automation for Paid Users Refer & Earn Page

  Background: User is Logged In

    Given  User is at Student portal master login Page
    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152|hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User Clicks on Refer & Earn tab
    Then   User is at the Refer & Earn Screen


  @Regression @Referral @SmokeTest
  Scenario: 1 #Verifying Copy Link on Refer & Earn Page

    When    User clicks on Book Free Trial CTA of referral card
    Then    User should be navigated to the new tab with referral link in it
    When    User click on COPY LINK
    Then    Text on CTA should be changed to COPIED
    When    User opens a new tab and paste copied link in it
    Then    Verify Same URL should be there which is associated with Book Free Trial CTA

  @Regression @T&C @Referral
  Scenario: 2 #Verifying 'T&C' link on Refer & Earn Page

    When    User clicks on T&C link on Refer & Earn Page
    Then    User should be navigated to the new policy tab


  @Regression1 @Facebook @Referral
  Scenario: 3 #Verifying 'Facebook' CTA on Refer & Earn Page

    When    User clicks on Facebook link of referral card
    Then    Verify new tab with facebook link should get opened


  @Regression @WhatsApp @Referral
  Scenario: 4 #Verifying 'WhatsApp' CTA on Refer & Earn Page

    When    User clicks on WhatsApp link of referral card
    Then    Verify new tab with WhatsApp link should get opened


  @Regression @Leaderboard @Referral
  Scenario: 5 #Verifying Leaderboard section on Refer & Earn Page

    When    User scrolls down till Leaderboard section of referral page
    Then    Verify all the data on Leaderboard section