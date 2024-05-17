# Unit Test Mini-game
**Project Name:**

*UMA-ISE24-E1*

Author(s):

*Francisco Javier Jordá Garay.*

## Test Case Overview

**Test Case ID:**

*TC\_MiniGame*

**Purpose:**

*We’re testing that mini-game (FR019) works as intended while following non-functional requirements of Resolution (NFR004), Low Latency Responses (NFR003) since it is another instance inside the main game.*

**Test Case Description:**

*Verifying the correct behavior of the mini-game first by launching the mini-game upon player death, checking functionality in response to different player actions such as detecting correct/wrong sequences, and correctly restoring player’s HP and respawn point upon successful completion of the mini-game or displaying the Game Over message on failure.*

### Pre-Conditions
**Prerequisites:**

*The player collides in main game with enough obstacles that boat’s HP drops to zero, meaning its destroyed (player dies), then the mini-game is triggered upon death.*

**Test Data:**

*Initial player state (position of death and level progress to restore gameplay if successful) and boat’s HP.* 

*Rival boats and obstacles states (current position).*

### Test Steps
**Step Description:**

1. *Launch mini-game only on player's death within the main game due to boat’s HP is zero.*
1. *Verify that the mini-game launches properly (for instance, the commands the player must execute are visible and keystrokes register accordingly).*
1. *While the mini-game is being played, rival boats should not continue the race and only resume movement when the player re-enters the race.*
1. *Test player’s input sequences. If enough incorrect keystrokes punish the player by ending current run triggering a Game Over. Otherwise, input of sufficient correct sequences will trigger a win condition thus, ending the mini-game on a successful scenario.*
1. *Player respawns after completing successfully the mini-game, the boat regains the correct amount of HP and the location of boat’s revival is exactly the same as the death point.*
1. *When the player is respawned, the game continues as it was, meaning existing obstacles stay on their original track except for the obstacle that triggered the last collision; that one is removed to avoid problems on return.*

### Post-Conditions
**Expected Outcome:**

*The mini-game works as intended by initializing on player’s death, the gameplay is smooth due to synchronization with NFRs; the response time of player’s actions (NFR003) is the expected and that the mini-game displays correctly as stated by NFR004. Success scenario gives the player to a second chance on the current run or on fail scenario finally ends the game displaying a Game Over message.*

**Cleanup:**

*Return boat to main game and last collided obstacle is removed.*
