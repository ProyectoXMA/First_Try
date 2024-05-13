**Test case ID:**
Lanes
**Purpose:**
Verify that every player has its own space inside the race to clarify where the user must stay during the it.
**Test case description:**
this test evaluates the player's ability against the division of the entire map into lanes in order to check all illegal moves that the user may cause.
This ensures that players can stand their ground and that if they do not comply with this rule, we can penalise them for the illegal action they have committed.

**Pre-conditions:**
-Prerequisites:
Game is installed and running
User has already selected a boat and is inside a race
-Test data:
Not applicable
**Test steps:**
-Steps description:
1. Enter a race with the user selected boat
2. Check if the collision between the boat and the division lane is well implemented
3. Check that the lane collision penalty is correctly applied to the player
**Post-conditions:**
Expected outcome:
In case that the player collides with a division line, then the player must die and a Game Lost screen must pop up to tell the user that the race has ended because of an illegal movement.
**Notes:**
This test can be performed with any controls and its just to verify that the lanes between competitors are not crossable