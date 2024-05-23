We will use an MVC architecture for the game, which separates the business model from the interface.
The code is divided into three sections:

- Model:

Manages system data and operations (business data).
Represents the problem to be solved.
Encapsulates the application state.
Contains data and business logic, but no input/output operations.
Independent of the view and controller, with no references to either.
Provides methods for the view and controller to interact with it.

- View:

Manages the display of data to the user (visual representation of the model).
Provides a view of the model's state without assuming or storing it.
Contains interactive controls that trigger events but does not handle them.
Is a passive observer that does not affect the model.

- Controller:

Handles user interactions (e.g., mouse clicks, keystrokes) and passes them to the view and model.
Manages user-model interaction and updates the view based on model actions.
Translates view events into model actions and view updates.
Contains references to both the model and the view.
The interface consists of the view and the controller.