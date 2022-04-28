Feature: Paid and Demo Users Certificates
  Description: Test Automation for Paid and Demo Certificate Scenarios

  Background: User is Logged In

    Given  User is at Student portal master login Page


  @Regression @DemoCertificate
  Scenario: 1 #Verifying Unlocked Certificate

    When   User enters valid Phone no and password

      |Phone      |Password|
      |85171152007|hr26ec2 |
      #This is a prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of Demo Completed account
    When   User Scrolls down till Certificate section
    Then   Verify Certificate Should be unlocked

  @Regression @DemoCertificate
  Scenario: 2 #Verifying Unlocked Certificate can be downloaded

    When   User enters valid Phone no and password

      |Phone      |Password|
      |85171152007|hr26ec2 |
      #This is a prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of Demo Completed account
    When   User Scrolls down till Certificate section
    Then   Verify Certificate Should be able to Download

  @Regression @PaidCertificate
  Scenario: 3 #Verifying Certificates on the Home Page

    When   User enters valid Phone no and password

      |Phone     |Password|
      |503670250 |hr26ec2 |
      #This is Prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    And    Verify Certificate shown on the Dashboard
    When   User clicks on Certifcates
    Then   User should be redirected to Certificates screen

  @Regression @PaidCertificate
  Scenario: 4 #Verifying Unlocked Certificates on Certificate Screen

    When   User enters valid Phone no and password

      |Phone     |Password|
      |503670250 |hr26ec2 |
    #This is Prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is at Certificates Screen
    Then   Verify Unlocked Certifiates should be available under Certificates Earned Section
    And    Unlocked Certificate can be downloaded

  @Regression @PaidCertificate
  Scenario: 5 #Verifying Locked Certificates on Certificate Screen

    When   User enters valid Phone no and password

      |Phone     |Password|
      |503670250 |hr26ec2 |
    #This is Prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is at Certificates Screen
    Then   Verify Locked Certificates should be available Under Upgrade Now Section
    And    Verify lock icon on all the locked Certificates


  @Regression @PaidCertificate
  Scenario: 6 #Verifying Upgrade Now on Certificate Screen

    When   User enters valid Phone no and password

      |Phone     |Password|
      |503670250 |hr26ec2 |
    #This is Prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is at Certificates Screen
    When   User Clicks on Update this Plan CTA
    Then   User is redirected to the Curriculum selection Page