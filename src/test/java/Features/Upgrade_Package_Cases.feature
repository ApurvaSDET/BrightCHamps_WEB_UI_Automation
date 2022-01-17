Feature: Home Page Part 4 - Upgrade Package Scenarios
  Description: Test Automation for Upgrade Package Scenarios

  Background: User is Logged In

    Given  User is at Student portal master login Page

  @Regression @UpgradePackage
  Scenario: 1 #Verifying Upgrade Package Card in case of 5 or less classes are left

    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152|hr26ec2 |
      |8433918423|hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is left with below classes in his account to be completed

      |ClassesLeft|
      |     5     |

    Then   Verify there is an Upgrade Package Card shown for Non-Champion User
    When   User clicks on Upgrade CTA
    Then   User is redirected to the Curriculum selection Page
    When   User selects curriculum
    Then   User is redirected to the Payment screen of BrightChamps

  @Regression @UpgradePackage
  Scenario: 2 #Verifying Upgrade Package Card in case no classes are left

    When   User enters valid Phone no and password

      |Phone     |Password|
      |503670250 |hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is left with below classes in his account to be completed

      |ClassesLeft|
      |     0     |

    Then   Navigate back to the Home Page
    And    Verify there is an Upgrade Now Card at the top of Home Page
    When   User clicks on Upgrade Now Card at the top of Home Page
    Then   User is redirected to the Curriculum selection Page
    When   User selects curriculum
    Then   User is redirected to the Payment screen of BrightChamps

  @Regression @CurriculumPageBackButton
  Scenario: 3 #Verifying back button of Curriculum Page

    When   User enters valid Phone no and password

      |Phone     |Password|
      |8433918423|hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   User is left with below classes in his account to be completed

      |ClassesLeft|
      |     5     |

    Then   Verify there is an Upgrade Package Card shown for Non-Champion User
    When   User clicks on Upgrade CTA
    Then   User is redirected to the Curriculum selection Page
    When   User clicks on back button of Curriculum Page
    Then   User is at the Home Page of single user account

  @Regression @SchedulingFromDashboard
  Scenario: 4 #Verifying Class Schedule from Dashboard Screen

    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152|hr26ec2 |
      |8433918423|hr26ec2 |

    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    When   There is no Scheduled Class for a user to attend
    Then   Verify User can schedule a class from Dashboard Screen