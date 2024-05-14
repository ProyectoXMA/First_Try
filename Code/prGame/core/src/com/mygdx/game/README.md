We will use a MVC architecture for the game
The key idea is that the business model and the interface are separated.
The code is divided in 3 sections:
- Model: responsible for managing the system data and the operations  associated with the data (business data).
Represents the problem to be solved. 
Encapsulates the the state of the application
- View: defines and manages how the data is displayed to the user (visual representation of the model).
- Controller: responsible for the interaction with the user (e.g. mouse  clicks, keystrokes) and for passing those
interactions to the View and  Model.