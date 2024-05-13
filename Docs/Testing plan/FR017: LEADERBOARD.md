**Test case ID:**
Leaderboard test

**Purpose:**
Verify that the Leaderboard (FR017) accurately displays the positions of the Player(FR001) and Rivals(FR013) during the race.

**Test case description:**
This test case ensures that the leaderboard provide real-time information about the positions of the player and rivals throughout the level. It involves ensuring that the leaderboard dynamically updates as a player or rival overtakes another player or rival. 

**Pre-conditions:**
-Prerequisites:
Game is running where the boats of the rivals and the player are present.
Leaderboard visible with a initial order.
-Test data: 
Player and AI rivals initial position.

**Test steps:**
-Steps description:
1. Begin the race session and ensure that the leaderboard is displayed.
2. Monitor the leaderboard throughout the race to observe real-time updates of player and rival positions.
3. Confirm that the positions and names of the player and rivals are accurately reflected on the leaderboard.
4. Ensure that the leaderboard remains legible and in the same place throughout the level.

**Post-conditions:**
-Expected outcome:
The leaderboard updates for providing acurate names and positions of both player and AI rivals.
Leaderboard enhances the player understanding of their position in the race relative to rivals.
JUnit tests validate the functionality and reliability of the leaderboard feature.
-Cleanup:
Close the game.

**Notes:**
For this test is essential that the updates to the leaderboard accurately correspond to real ones. This provides the player a competitive experience. 
