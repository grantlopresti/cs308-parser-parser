# DESIGN_PLAN.md

##Basic Design

###Introduction
*This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).*

###Overview
*This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. Include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). To keep these classes as flexible as possible, your team must describe two different implementations (i.e., data structures, file formats, etc.) and then design your method signatures so they do not reveal the specifics of either implementation option. Discuss specific classes, methods, and data structures, but not individual lines of code.*

###User Interface
*This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Also describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.*

The user will interact with the program in two phases. The first point of contact is a splash/startup screen where the user
has various configuration options for how they want the visualization to appear (e.g. background, pane information, turtle
color), as well as where the first stage of input for commands will come from (either read in from a text file or open a blank).

Following the initial configuration, the user then sees the "standard stage", which is created using a dynamic ```LayoutPane```
type (e.g. ```GridPane```). Some regions of the screen will be a text box to input commands, a region showing the current
drawing, a ```GO``` button to submit a chunk of text, as well as other panels/buttons to display a command history and current
variables that can be accessed.

All nodes in the LayoutPane that makes up the view will themselves be a custom Object (e.g. ```HistoryView```). These objects
will extend a JavaFX element (e.g. ```TextInputBox```) but with a set of added functionality to achieve our goals. Command 
inputs can also be loaded in from a text file. The user will have the ability to interact with components via button selection 
(for configurations, toggling between panes, etc.) as well as for inputting commands (via loading a file or typing text into a command window).

Regarding erroneous situations, we are currently playing with the idea of having a terminal-esque console which displays 
status updates for all commands run (e.g. "Loaded in files...Parsed Inputs...Unraveled to 13 commands...Executed 13 commands")
which communicate successful runs, as well as any errors (potentially displayed in a different color). An interesting extension
to these console outputs would be user configured degrees of output (simlar to logfiles in python). Alternatively, we 
may decide to implement a simple popup to display any types of errors while running/displaying commands.

<img src="https://i.imgur.com/mI08ur8.jpg" width="500">

<img src="https://i.imgur.com/ReAJrfJ.jpg" width="500">

###Design Details
*This section describes each API introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.*

###API as Code
*Your APIs should be written as Java interfaces, types that cannot contain instance variables or private methods, in appropriate packages. These should be Java code files that compile and contain extensive comments to explain the purpose of each interface and each method within the interface (note this code can be generated directly from a UML diagram). Include any Exceptions you plan to throw because of errors that might occur within your methods. Note, this does not require that all of these types will remain as interfaces in the final implementation, just that the goal is for you to focus on each type's behavior and purpose. Also include the steps needed to complete the Use Cases below to help make your ideas more concrete.*

###Design Considerations
*This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length and describe at least one alternative in detail (including pros and cons from all sides of the discussion). Describe any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.*

A major design discussion our group has discussed at length is how to organize the general flow of information in the program.
We believe that a classic MVC model is ideal for the general programmatic design. Our current plan is to restrict the majority
of logic to occur in the controller, and for the visualization and model to handle very specialized operations. For example,
there will be NO references to any JavaFX elements anywhere besides the Visualization. Elements will have a mirror representation
in the ```Model``` and in the ```View```, with a clean mapping between them that is negotiated by the controller. Model objects
will however have some indication of visual representation, but they will GUI universal in nature (e.g. a hex color String).

<img src="https://media.geeksforgeeks.org/wp-content/uploads/MVC-Design-Pattern.png" width="500">

Furthermore, we would like to protect objects from modification, and intend to leverage a ```getImmutable()``` interface call to achieve this. 

###Team Responsibilities
*This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.*

- Alex Xu is responsible for design and implementation of the Model (e.g. ```Turtle``` and ```Pen```, subject to change). 
Alex is also responsible for providing support on the ```LogicController``` that handles information exchange between the ```View``` and ```Model```

- Amjad Syedibrahim is responsible for design and implementation of the ```LogicController``` that serves as an intermediary shuttling information
from the ```View``` to the ```Model```. This portion will involve two stages, a ```Parser``` to convert text input (via file or ```TextBox```)
into a ```Command``` Object that can be executed on the ```Model```.

- Grant LoPresti is responsible for the design and implementation of the ```FrontEnd``` (View) of the program. He will additionally
offer support on other portions and help guide the exchange of information between the ```LogicController``` and ```ViewController```.

- Max Smith is responsible for the design and implementation of the ```ViewController```, which will translate updates in the
```Model``` to visual objects displayed to the User.

##API Design
*Clearly describe the program's four APIs and justify your reasoning for the design of each:*

###External: between the two separate sub-groups

- How you plan to separate the graphical interface from the interpreter and how you plan to let
  them communicate when necessary
- What objects will be used for communication, making it clear:
    - how needed information will get where it is needed
    - what information will be immutable
    - what data will be encapsulated
    - what errors may be thrown
    - _Note, all of these methods will need to be public_

The graphical interface will be run by the "View" portion of the MVC model. However, it will be kept seperate from the
command interpreter to ensure a streamlined design. To achieve this, a controller will be placed as an intermediary between
the view and the input to the graphical interface. This controller will handle the interpretation of commands and parse
them, so that they may be executed later by the model. The communication between the view and this controller class will
require the view elements to send the input data over to the controller method, likely through the creation of an object.
This object and any methods used in this transfer will be apart of the external API between the View and the controller.

More generally, at least 2 controller objects will be required as a means of communication for the MVC model. A controller
will be placed in the middle of the View and the Model components to facilitate information exchange between the two, such
as converting Model turtle objects into View turtle objects so that they are ready to be displayed. Another controller will
be utilized to receive incoming commands, parse them, and send them over to Model. These intermediary controllers will call
methods native to the Model and View classes and run the computational aspects, whether that be processing turtle movement
or displaying the turtle after it has moved.

Immutable information will be used to ensure that variables that stay constant do not have the chance to be altered. This
includes a Turtle's pen color once the object has been created for instance. It also includes the Model Turtle object and 
View Turtle object once it has been created in each iteration of the program.

For data encapsulation, the Turtle object's private information will be kept in the Model, and the controller will be able
to call the various public methods that are laid out in the Model class, but will not directly have access to the data of
the Turtle. This is referring specifically to the Model Turtle object. The View Turtle object is another example of an
encapsulated piece of data that will be kept hidden from the Model for example. As mentioned before, an intermediary controller
class will handle the conversion process between Model and View Turtle objects. The Model class will not be given the data
of the View Turtle object and vice versa. Although each class will have its own data and methods, the controller classes
will be the ones primarily responsible for executing the desired actions on that data without directly having access to it.
This will be achieved via the external API methods that are available.

As far as errors are concerned, we would have to deal with the possibility that the API will ask for data that is not present,
in which case a null value error will be thrown. An error will also be thrown if a given public method is fed in data that
is inconsistent with the type that it accepts. This can happen if the user input for example is such that the parser has
not been trained to handle it.


###Internal: between each sub-group and its future programmers (maintainers)
- How you plan to provide paths for extension through interfaces, inheritance, and design patterns
 for new features you might reasonably expect to be added to the program
- What subclasses or implementing classes will be used to extend each part to add new features
, making it clear:
    - what parts of your code you expect to be closed to modification
    - what freedom future coders will have in choosing how to implement new features
    - what kind of code someone will be expected to write to implement new features
    - what errors may be thrown
    - Note, while some of these methods may be public, many may be protected or package friendly


As mentioned, we anticipate having a minimum of two controllers in our design. To allow for extension into the types of
controllers that we will likely need, there will be a general controller interface that outlines some of the basic methods
common to all the controllers. This can include, for example, a data transfer methods. Inheritance can be utilized for
extending classes of Turtle objects, as we will need a View Turtle object and Model Turtle object. 

There are many parts of the code that will be closed to programmers for modification and others that are open to improvement.
THe View class for example will likely be closed, as once the task of displaying a Turtle object has been complete, it can 
be used for any future Turtles created. Other elements such as the parser class for example cannot necessarily be closed.
New commands and structures may be added in the future to the program that will need to be parsed, which requires new capabilities.
The Model class also will be made flexible to incorporate future changes such as multiple Turtles. For coders to write new features,
we are hoping to have it sufficient for them to add existing methods to the selection of classes we have, and avoid writing
new classes altogether. This will allow the overall structure of the program to remain lean and allow for change as well.

Errors thrown can include type incompatibilities when calling methods inside a class on certain objects that are not of
the correct type.


##Use Cases
*Clearly show the flow of calls to public methods described in your design needed to complete each example below, indicating in some way which class contains each method called:*

- The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.

When the user types 'fd 50' into the command window, the text is shuttled off to the ```Controller``` in raw form. The controller
then goes through a series of parsing/unraveling processes (very simple for a single command) to create the ```Command``` object
representing "Move forwards by 50 pixels". The ```Controller``` then sends the command to the ```Model```, which interprets
the ```Command``` and performs it on the ```Turtle```. The ```Turtle``` within the ```Model``` has its position updated (using
an internal method, dependent on its current position, heading, and distance traveled), and creates a ```Line``` DrawableObject 
(in accordance with its ```Pen``` color and thickness attributes). Once the ```Model``` has completed the command, all
new DrawableObjects are communicated to the ```View``` (via an external API to the ```Model```) and visualized approriately.
In this instance, there would be a line (trail) that is displayed in the window, as well as the ```Turtle``` would be cast to 
a ```TurtleView``` object with a new position (50 pixels forwards with respect to its current heading). After the command 
has been run, it is appended to a ```History``` object within the ```Model```, which encapsulates a List of Commands 
(each with a ```toString()``` method for displaying to the user). The updated ```History``` Object is also communicated to the View,
and displayed according to current user configurations.

- The user sets the pen's color using the UI so subsequent lines drawn when the turtle moves use
 that color.
 
 The user navigates through a series of settings/configuration options from the standard stage, which get them to an
 option to set the pen's attributes (color and thickness). After the user makes and confirms a selection, the information
 is then shuttled off (via an external API) to the ```Controller```, which in turn updates the appropriate pen's attributes
 (internal instance variables) in the ```Model```. For all subsequent graphics created, the DrawableObject that is communicated 
 between the ```Model``` and ```View``` will be created with these updated attributes.
 
- Additionally, each member of the team should create two use cases of their own (and the
 appropriate example code) for the part of the project for which they intend to take responsibility.
 
 - Team case:  User types in a series of commands into the command window
 
 When the user types a series of commands into command window, the visualization packages the text and sends it (via an external API)
 to the controller. The controller then goes through a series of parsing and unraveling stages to go from a stream of text -
 which may contain variable declaration/modification as well as boolean logic, loops, or math operations - into a series of
 commands that can be executed on the turtle, as well as logging any variables created. Each ```Command``` Object is then 
 sent (via an external API) to the ```Model```, where an ```Interpreter``` determines how the command operates in the Model environment.
 
 As commands are executed, they are added to a ```History``` object, which encapsulates a List of Commands (each with a ```toString()``` method
 for displaying to the user). Additionally, commands will often create a drawable object (e.g. Line or Circle). Depending
 on the ```Turtle``` that is creating these commands, these drawable objects will have an associated color and thickness.
 
 Regarding the visualization of these drawable objects, after each step (step has yet to be defined), the View will request
 an immutable collection of drawable objects from the model (including turtle(s) and shape(s)), cast them into ```ViewableObjects``` 
 (each with an instance variable corresponding to the ```Turtle``` that created them), and then creates these JavaFX Objects
 in the scene at the proper location.
