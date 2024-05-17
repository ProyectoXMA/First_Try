# Unit Test Documentation Template
**Project Name:**

*UMA-ISE24-E1*

Author(s):

*Francisco Javier Jordá Garay.*

## Test Case Overview

**Test Case ID:**

*TC\_NF001*

**Purpose:**

*The game must be developed using the Java language as a client's technical constraint (NFR001).*

**Test Case Description:**

*This is just a verification to confirm that the entire game, including its additional implementations such as the mini-game, is developed using the Java programming language or any supported framework such as libGDX.*

### Pre-Conditions
**Prerequisites:**

*Each member of the software team must be aware of this constraint.*

**Test Data:**

*Not applicable.*

### Test Steps
**Step Description:**

1. *Review the source code of the game to confirm that Java is used for development.*
1. *Check Dependencies to ensure that all libraries and frameworks, used in the project are compatible with Java.*
1. *Verify Compilation using a Java compiler.*

### Post-Conditions
**Expected Outcome:**

*The whole game is written in Java.*

**Cleanup:**

*Not applicable.*

### Notes
*Its more than enough to have a single meeting with the client or the development team to discuss this requirement.*










## Test Case Overview

**Test Case ID:**

*TC\_NF024*

**Purpose:**

*Testing that the game is playable at 30fps for smooth user experience (NFR002) and its within the boundaries of expected resolution (1920x1080, 1280x720, 1024x768, 800x600) (NFR004).*

**Test Case Description:**

*To test the game's performance across different hardware configurations to ensure that it achieves a frame rate of at least 30 frames per second (fps) with the option to run on different screen sizes.*
### Pre-Conditions
**Prerequisites:**

*Access to multiple computer models and OS with varying specifications to broad our testing ground.*

**Test Data:**

*Minimum and maximum specifications of the systems.*

### Test Steps
**Step Description:**

1. *Test on minimum requirements.*
1. *Play the game on a computer that meets the minimum hardware requirements to validate our lower bound.*
1. *Test on higher specifications.*
1. *Verify that the frame rate remains stable at 30fps or higher across different hardware configurations.*
1. *Test resolution settings on each system.*
1. *Playing the game on different resolutions may modify the way the game runs so the fps could depend on the resolution based on what configuration the player imposes for the game.*
### Post-Conditions
**Expected Outcome:**

*The game consistently achieves a frame rate of 30fps or higher on all tested hardware configurations and screen resolutions.*

**Cleanup:**

*Not applicable.*

### Notes
*Regular monitoring and testing of frame rates may be required, after implementing new features like the minigame or adding different components to the screen such as more obstacle types or powerups and if the resolution of the screen changes the way the game behaves in a critical way it may be needed to modify the resolution options available to the player further limiting the environments the game can execute.*














## Test Case Overview

**Test Case ID:**

*TC\_NF005*

**Purpose:**

*Verifies that the size of the game executables is less than 1GB (NFR005).*

**Test Case Description:**
### *Check the size of the game during implementation of each class and implementations to calculate that the final product is no more than 1GB is size.*
### Pre-Conditions
**Prerequisites:**

*Game executables and assets weight (such as size of images used for boats, obstacles, etc. and any music if added).*

**Test Data:**

*Not applicable.*

### Test Steps
**Step Description:**

1. *Locate the game executables on the system as well as visual assets in source code and see it does not pass the 1GB threshold.*
### Post-Conditions
**Expected Outcome:**

*The size of the game is within the desired scope.*

**Cleanup:**

*Not applicable.*

### Notes
*Executable size can be affected by factors such as included assets, libraries, and code so further optimization may be needed if this requirement is not met.*

