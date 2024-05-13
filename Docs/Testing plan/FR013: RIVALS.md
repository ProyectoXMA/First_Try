**Test case ID:**
Rivals Test

**Purpose:**
Verify that the AI-controlled rivals(FR013) behave appropriately and have correct properties.

**Test case description:**
This test case ensures that AI-controlled rivals during game execution exhibit a consistent behavior with their defined properties and characteristics. It involves testing various aspects of rival behavior such as movement, decision-making, and interaction with the player/obstacles. Additionally, it verifies that the rivals possess the correct attributes and statistics as per the game specifications.

**Pre-conditions:**
-Prerequisites:
Randow boats are assingned to the rivals
Game is running where the boats of the AI rivals are present.

-Test data: 
Structure with 3 number of AI rivals with their properties.

**Test steps:**
-Steps description:
1. Check if the attributes and statistics of AI rivals match the defined properties in the game specifications.
2. Observe the movement patterns of AI rivals during the level and ensure that the rivals stay in their respective lane.
3. Evaluate the decision-making process of AI rivals in response to player actions and environmental factors (obstacles and lane limits).
4. Repeat steps 1-3 for multiple race sessions to ensure consistency and reliability of AI behavior.

**Post-conditions:**
-Expected outcome:
AI rivals demonstrate realistic and diverse behavior patterns.
Attributes and statistics of AI rivals align with predefined properties.
JUnit tests verify the accuracy of AI behavior and properties based on the provided test data.
-Cleanup:
Close the game.

**Notes:**
This test relies on the effective implementation of decision-making algorithms for the AI rivals and their corresponding behavior within the game environment. A well-designed AI decision-making system is crucial to provide the players a challenging experience.

