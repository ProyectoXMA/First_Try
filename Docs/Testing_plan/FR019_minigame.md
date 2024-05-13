# Unit Test Mini-game
**Project Name:**

*UMA-ISE24-E1*

Author(s):

*Francisco Javier Jordá Garay.*

## Test Case Overview

**Test Case ID:**

*Mini-game Test*

**Purpose:**

*We’re testing that mini-game (FR019) works as intended while following non-functional requirements of Resolution (NFR004), Low Latency Responses (NFR003) since it is another instance inside the main game.*

**Test Case Description:**

*Verifying the correct behavior of the mini-game first by launching the mini-game upon player death, checking functionality in response to different player actions such as detecting wrong sequences, and correctly restoring player’s HP and respawn point upon successful completion or displaying the Game Over message on failure.*

### Pre-Conditions
**Prerequisites:**

*The main game must be set correctly with the mini-game implementation complete so that when player fails in main game by dropping to zero HP the mini-game is triggered only upon death.*

**Test Data:**

*Initial player state (position of death and level progress to restore gameplay if successful) and boat’s HP.* 

### Test Steps
**Step Description:**

1. *Launch Mini-game*
   1. *Only on player's death within the main game due to boat’s HP is zero.*
   1. *Verify that the mini-game launches properly (for instance, the commands the player must execute are visible and keystrokes register accordingly).*

1. *Test Wrong Sequences*
   1. *Input of wrong sequences multiple times and not being penalized.*
   1. *Check wrong keystrokes punishes the player accordingly (killing the player and ending current run).*

1. *Test Correct Sequences*
   1. *Input of sufficient correct sequences returns the player to the point where they died in the main game with of their HP fully restored.*

1. *Synchronization with NFRs*
   1. *Measure the response time of player’s actions complying with NFR003.*
   1. *Verify that the mini-game displays correctly as stated by NFR004.*

### Post-Conditions
**Expected Outcome:**

*The mini-game meets all constraints described before for a good player experience.*

**Cleanup:**

*Reset the main game’s state.*
### Notes
*Sticking to non-functional requirements is crucial to ensure overall performance of the mini-game along with the main game’s features. Since there is no random component involved, once the tests pass, the correct functionality of the mini-game is guaranteed.*

