Feature: Validating Profile Page
  Description: Test Automation for covering all possible cases for a Profile Page

  Background: User is Logged In

    Given  User is at Student portal master login Page
    When   User enters valid Phone no and password

      |Phone     |Password|
      |8130865152|hr26ec2 |


    And    User clicks on SUBMIT CTA
    Then   User is at the Home Page of single user account
    And    Clicks on Profile button
    Then   User is at Profile screen


  @Regression @Profile
  Scenario: 1 #Verifying Updating Profile Page information

    When User updates all the Profile page information
    Then Profile Updated Success message should appear
    And  Profile Page should get updated

  @Regression @Profile
  Scenario: 2 #Verifying Updating Hobbies on Profile Page

    When User clicks on X button of Hobbies
    Then Hobbies should be deleted
    When User enters new Hobbies from Dropdown
    Then New Hobbies should be entered
    When User clicks on Save Button
    Then Profile Updated Success message should appear
    And New hobbies should be updated on Profile Page


  @Regression @Profile
  Scenario Outline: 3 #Validating invalid input for Update Password

    When User clicks on Change Password
    Then Enter password text field should appear
    When User enters "<Password>" password
    And  Clicks on Save button
    Then Appropriate "<Validation Message>" should appear

    Examples:
      |Password|Validation Message                             |
      |        |Invalid value                                  |
      | qwert  |Password must be at least 6 characters long    |
      | qwerty |Great! Now you can login with your new password|

  @Regression @Profile
  Scenario: 4 #Validate Sub-heading on Profile Page

    And Verify Student first Name as Sub-heading on Profile Page

  @Regression @Profile
  Scenario: 5 #Verifying Update Now CTA from Home Page

    When User Clicks on Home button
    Then User is navigated to the Home Page
    When User Clicks on Update Now CTA
    Then User is at Profile screen

  @Regression @Profile1
  Scenario: 6 #Verifying Upload Profile Pic from Profile Page

    When User upload profile pic
    Then Profile pic updated message should appear
    And  Profile Pic should get changed

  @Regression @Profile
  Scenario: 7 #Verifying Updating DOB from Calendar on Profile Page

    When User enters DOB as input
    Then DOB should be entered
    When User Clicks on DOB field
    And  User selects any random date
    Then Newly selected date should appear on DOB field
    When User selects any random Month
    Then Newly selected Month should appear on DOB field
    When User selects any random Year
    Then Newly selected Year should appear on DOB field
    When User clicks on Save Button
    Then Profile Updated Success message should appear
    And  New DOB should be updated on Profile Page

  @Regression @Profile
  Scenario: 8 #Validating invalid input on Profile Page

    When User enters invalid input in Student Name
    Then User should not be able to enter anything except alphabets in Student Name
    When User enters invalid input in Mother Name
    Then User should not be able to enter anything except alphabets in Mother Name
    When User enters invalid input in Father Name
    Then User should not be able to enter anything except alphabets in Father Name
    When User enters invalid input in Father Phone Number
    Then User should not be able to enter anything except numeric values in Father Phone Number
    When User enters invalid input in Father Email
    Then User should not be able to enter invalid email format
    When User enters invalid input in Mother Phone Number
    Then User should not be able to enter anything except numeric values in Mother Phone Number
    When User enters invalid input in Mother Email
    Then User should not be able to enter invalid email format
    When User enters invalid input in City
    #Then User should not be able to enter anything except alphabets in City
    When User enters invalid input in School Name
    #Then User should not be able to enter anything except alphabets in School Name


