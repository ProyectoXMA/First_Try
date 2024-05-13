**Test case ID:**
Controls Navigation
**Purpose:**
Verify that user can navigate through the menues usign the selected controls in expected response time.
This test cases involves FR018(Controls), FR005(Main Menu), FR016(Boat Selection Menu) and NFR003(Low Latency Responses).

**Test case description:**
This test case evaluates the functionality of controls specifically for menu navigation within the game interface. It aims to ensure that players can traverse through main menu and boat selection screens and select options without encountering any issues or delays. 
Related JUnit tests will check methods relative to the controls listeners and event handlers of the keyboard.

**Pre-conditions:**
-Prerequisites:
Game is installed and running succesfully.
User is in main menu.
Controls settings are set to default (Arrows).
-Test data:
Not applicable.
**Test steps:**
-Steps description:
1. Press up and down arrow keys and verify that the different menu options are highlihted.
2. Press enter key on "Boat selection" and verify that it is opened.
3. Press esc key and verify that the main menu is opened.
4. Press enter key on "Settings" and verify that the settings menu is opened.
5. Press esc key and verify that the main menu is opened.
6. Press ener key on "Tutorial" and verify that the tutorial is opened.
7. Press esc key and verify that the main menu is opened.
8. Press enter key on "Credits" and verify that the credits are opened.
9. Press esc key and verify that the main menu is opened.
10. Repeat navigation process multiple times to check for consistency and reliability.
11. Press enter key on "Exit" and verify that the game is closed.

**Post-conditions:**
-Expected outcome:
Response time of each operation is less than 30 ms.

-Cleanup:
Not applicable.
**Notes:**
This specific test can be performed with different control settings after the controls customization test is completed.
##########################################################################################################################################################################

**Test case ID:**
Boat Selection
**Purpose:**
Verify that user can select a boat from the boat selection menu.
This test cases involves FR018(Controls), FR016(Boat Selection Menu) and NFR003(Low Latency Responses).
**Test case description:**
This test case ensure that the user can select a boat from the boat selection menu. It verifies that the variable that stores the selected boat is updated correctly and that the game is able to load the selected boat.
JUnit tests will check methods related to the controls listeners and event handlers related to the keyboard and the change of playerBoat variable.

**Pre-conditions:**
-Prerequisites:
Game is installed and running succesfully.
User is in boat selection menu.
Controls settings are set to default (Arrows).
-Test data:
playerBoat

**Test steps:**
-Steps description:
1. Press left and right arrow keys and verify that the different boats are highlighted.
2. Press enter key on a boat and verify that it is selected.
3. Press enter key on other boats and verify that the selected boat is updated correctly.

**Post-conditions:**
-Expected outcome:
playerBoat variable is updated correctly.
Response time of each operation is less than 30 ms.

-Cleanup:
Set playerBoat to default value.
Close the game.

**Notes:**
This test can be performed with different control settings after the controls customization test is completed.
##########################################################################################################################################################################

**Test case ID:**
Controls Customization

**Purpose:**
Verify that user can customize the controls.
This test cases involves FR018(Controls). 

**Test case description:**
This test case checks wether the above tests can be performed with different control settings.
JUnit tests related to this test case will check the methods that update the variables related to keyboard listeners.

**Pre-conditions:**
-Prerequisites:
Game is installed and running succesfully.
User is in settings.
Controls settings are set to default (Arrows).
-Test data:
Key bindings variables

**Test steps:**
-Steps description:
1. Press "Customize Controls" in the settings screen.
2. Desired new key binding for UP, DOWN, LEFT, RIGHT, ENTER and ESC keys are selected whenever it is informed on screen.
3. Press "Save" and verify that the new key bindings are saved.
4. Perform the above tests with the new key bindings.

**Post-conditions:**
-Expected outcome:
Key bindings variables are updated correctly.
Tests related to navigation and boat selection are performed successfully with the new key bindings.

-Cleanup:
Set key bindings to default values.
Close the game.

**Notes:**
This test depends on the navigation and boat selection tests.

##########################################################################################################################################################################

**Test case ID:**
Boats

**Purpose:**
Verify that boats have the correct properties.
This test cases involves FR016(Boat selection menu) and FR001(Player). 

**Test case description:**
This test case checks wether the above tests can be performed with different control settings.
JUnit tests related to this test case will check the numbers related to the player are equal to each property of the boat selected and with the test data, in case selection menu is incorrect.
**Pre-conditions:**
-Prerequisites:
Boat is selected in selection menu.
-Test data:
Structure with n number of boats with their properties.

**Test steps:**
-Steps description:
1. Selecting the desired boat in the selection menu.
2. Quitting the race.
3. Selecting the next desired boat.
4. Steps are repeated until all boats are tested.

**Post-conditions:**
-Expected outcome:
JUnit test verifies and returns if all boat properties were the same or not inside player, selection menu and test data.

-Cleanup:
Close the game.

**Notes:**
This test depends on the navigation, player and boat selection tests.