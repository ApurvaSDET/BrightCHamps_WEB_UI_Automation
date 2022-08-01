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
    When   User Scrolls down till Goodies section
    And    User Click on View Certificate button
    Then   Verify Certificate Should be enlarged
    When   User clicks on X button
    Then   Certificate Modal should be closed

  @Regression @DemoCertificate
  Scenario: 2 #Verifying Unlocked Certificate can be downloaded

    When   User enters valid Phone no and password

      |Phone      |Password|
      |85171152007|hr26ec2 |
      #This is a prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of Demo Completed account
    When   User Scrolls down till Goodies section
    And    User Click on View Certificate button
    Then   Verify Certificate Should be enlarged
    Then   Verify Certificate Should be able to Download

  @Regression @PaidCertificate
  Scenario: 3 #Verifying Certificates on the Home Page

    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152 |hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    And    Verify Certificate shown on the Dashboard
    When   User clicks on Certifcates
    Then   User should be redirected to Certificates screen

  @Regression @PaidCertificate
  Scenario: 4 #Verifying Unlocked Certificates on Certificate Screen

    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152 |hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is at Certificates Screen
    Then   Verify Unlocked Certifiates should be available under Certificates Earned Section
    When   User Click on Certificate image
    Then   Pop-up Should Open
    When   User Click on Download on APP CTA on Modal
    Then   User should be redirected to APP Promotion link

  @Regression1 @PaidCertificate
  Scenario: 5 #Verifying Locked Certificates on Certificate Screen

    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152 |hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is at Certificates Screen
    Then   Verify Locked Certificates should be available Under Upgrade Now Section
    And    Verify lock icon on all the locked Certificates


  @Regression @UnLockedBETReport
  Scenario: 6 #Verifying Unlocked BET Report

    When   User enters valid Phone no and password

      |Phone      |Password|
      |1127690222|hr26ec2 |
      #This is a prod User

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of Demo Completed account
    When   User Scrolls down till Goodies section
    And    User Click on View BET Report button
    Then   Verify User should be at BET Report Screen