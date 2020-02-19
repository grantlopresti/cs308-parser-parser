# DESIGN_PLAN.md

##Basic Design

###Introduction

The main problem that our team is trying to solve by writing this program is to create a development environment for the
SLogo programming language. Our design goals include maximizing flexibility in terms of adding new types of commands/inputs,
understood languages, appearance/aesthetics changes (interface, programmed objects like Turtles), adding new types of "objects" in the SLogo
environment, and running/saving custom LOGO files.

The overall design of our project will follow the Model-View-Controller design pattern, with APIs that define how each component
of the program interact and communicate with each other. The Model will contain all of the data, logic, and rules of our application.
This entails the data and methods that involve Observable Model objects, such as Turtles and Pens. The Model should be closed to
modification (should not be able to modify how Turtles behave), but instead should be open for extension (add new types of Observable
objects, or new types of functionality).

The View will contain the graphical representation of what is contained in the Model,
as well as other UI elements such as buttons and text boxes. These UI elements should be flexible in their appearance and location
 (based on resource files), but closed in terms of their core functionality (should not be able to modify what a text box does, for example).
 
Finally, the Controller will be split into the Logical Controller and the Visual Controller.
The logical Controller will handle the interaction/conversion between the user input and the Model, and the Visual Controller
will handle the interaction between the Model and its rendering in the View. Certain elements within these Controllers should
be extendable -- for example, the Logical Controller should be able to handle different / new types of commands from different
types of inputs (text, file, etc.) and languages, and the Visual controller should be able to handle taking in new types of
Model objects and associating them with visual information. Other parts of these Controllers should be closed, such as *how* the Visual controller
associates certain model objects (e.g. Turtles) to their graphical representations, and *how* the Logical Controller parses inputs.

###Overview

The program is divided up into three major components: The Model, View, and Controller. These are the three major components
of the program, and will communicate with teach other through interfaces (APIs). The APIs that define how each component
of the program interact and communicate with each other. The Model will contain all of the data, logic, and rules of our application.
This entails the data and methods that involve Observable Model objects, such as Turtles and Pens. The Model should be closed to
modification (should not be able to modify how Turtles behave), but instead should be open for extension (add new types of Observable
objects, or new types of functionality).

As described in the introduction, the Model will handle the encapsulation of data and logic that governs the "model" of the program,
which includes object including the Turtle and the Pen. The View of of the program will be responsible for rendering the images
that the end user sees on the screen. Finally, the Controllers (logical and visual) will be responsible for the "intermediate"
interactions between the user input (commands) and the model, and between the model and the front-end View. The controller will
interact with these different components via external APIs. Public methods that dictate the behavior *within* certain packages
will be handled by the internal APIs.

**The APIs we intend to create include:**

External (Interacts with the Controller):
1. ModelObject/Turtle API - Used by the Visual and Logical controllers
2. ViewObject API - Used by the Visual controller

Internal (Within a component/package):
1. Pen API - Used within the model component
2. View API - Used within the View component to update images and aesthetics
3. Command API - Used within the Logical controller to handle command types

An example discussed here would be the ModelObject / Turtle external API. In this case, there can be many different implementations
of the turtle's features. By using an API that does not reveal the implementation details, we aim to make our code as 
clean and flexible as possible. For example, in the API we plan to include a ```move()``` method that the Logical controller
may interact with. This method can be implemented internally via many different methods, including keeping track of polar 
coordinates based off an angle and distance value. Once ```getX()``` and ```getY()``` are used, these polar coordinates may be
converted to the Cartesian plane. Another implementation is that the ```move()``` method may automatically calculate updates
to X and Y cartesian coordinates internally, and do the conversion work "up front" in the ```move()``` method rather than in the
getter methods. In any case, the point is that the method signatures in the API will **not** be affected by these changes.

In another example also in the ModelObject/Turtle external API is the Pen feature. Whether the Pen is intrinsic to the ModelObject/Turtle
itself (is a set of properties that it stores internally), or further abstracted to be its own separate class (an instance object
that the ModelObject/Turtle has), does not affect the methods defined in the API, including  ```isPenActive()```, ```getPenThickness()```, etc.

These two examples demonstrate the type of flexibility we intend to have in our program through the use of APIs that obscure
implementation details (keeping it SHY). This way, developers can update/change the implementation of specific components of the
program without affecting the API and the functionality of the rest of the program.

###User Interface

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

APIs Include:

External (Interacts with the Controller):

1. ModelObject/Turtle API - 
    * Thus API is used by the Visual and Logical controllers to interact with the "model" component of the program.
    * The logical controller interacts with this API to manipulate the objects in the model with the provided API as defined
    in the ModelInterface Java Interface.
        * Methods include:
            * setPenThickness(double thickness);
            * move(double distance);
            * setHeading(double degree);
            * turn(double degree);
    * The visual controller interacts with this API to create VisualObjects in the model with the provided API as defined by the ModelInterface.
        * Methods include:
            * getX();
            * getY();
            * getHeading();
            * isPenActive();
            * getPenThickness();
2. ViewObject API
    * This external API is used by the Visual controller to manipulate objects in the View.
    * Methods include:
        * setImage();
        * setWidth();
        * setColor();

Internal (Within a component/package):

1. Pen API
    * This is an internal API used in the model component to provide additional abstraction.
    * The ModelObject / Turtle uses this API to communicate with its Pen.
    * Methods include:
        * setThickness();
        * isActive();
        * getThickness();
2. View API
    * This is an internal API that is used by the View/ViewManager to update certain aesthetics.
    * Methods planned include:
        * setBGColor()
        * setPanelLocation()
        * setImage()
3. Command API
    * This is an internal API used by the Logical controller to handle command types
    * Planned methods include:
        * getValue()
        * getCommandType()
        * toString()
    
###API as Code

Our API separates our project into 4 distinct packages, controllers.logicalcontroller
, controllers.visualcontroller, model, and view. Our API as Code includes the following Classes
 and Hierarchical Structure that can be viewed more in depth by exploring the classes and interfaces
  within the mentioned packages.
  - controllers
    - logicalcontroller
        - Command (Class)
    - visualcontroller
        - VisualCommand (Class)
        - VisualInterface (Interface)
        - VisualObject (Abstract Class)
        - VisualTurtle (Class)
  - exceptions
    - InvalidCommandException
    - InvalidXMLException
  - model
    - ModelInterface (Class)
    - ModelObject (Abstract Class)
    - ModelTurtle (Class)
    - Pen (Class)
  - view
    - Visible (Interface)

###Design Considerations

A major design discussion our group has discussed at length is how to organize the general flow of information in the program.
We believe that a classic MVC model is ideal for the general programmatic design. Our current plan is to restrict the majority
of logic to occur in the controller, and for the visualization and model to handle very specialized operations. For example,
there will be NO references to any JavaFX elements anywhere besides the Visualization. Elements will have a mirror representation
in the ```Model``` and in the ```View```, with a clean mapping between them that is negotiated by the controller. Model objects
will however have some indication of visual representation, but their communication with the GUI
 will be universal in nature (e.g. a hex color String).

<img src="https://media.geeksforgeeks.org/wp-content/uploads/MVC-Design-Pattern.png" width="500">

Furthermore, we would like to protect objects from modification, and intend to leverage a ```getImmutable()``` interface call to achieve this. 

To ensure integrity and encapsulation with regards to the flow of information, we are planning on implementing two types
of Controllers. One ```LogicController``` to massage and translate information that the user inputs into commmands on the model, as well
as a ```ViewController``` to translate  model updates into new/updated nodes in the ```View```. An alternate design we considered
is to have JavaFX binding between the Model and View to remove the intermediate ```ViewController```. However, due to inexperience
with the technology as well as wanting to minimize logic processed in the Model and View, we elected to have a  controller
serve as the intermediary.

###Team Responsibilities

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

###External: between the two separate sub-groups

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
 
  - Team case: User loads in text from file
  
  The procedure for executing all text as commands works towards the same goal - parsing a series of text into singularly
  executable commands, and maintaining a list of variables to display to the user. The text is sent from the GUI to the 
  LogicController, which goes through a two phase parsing process to first unravel the text into single line executables,
  and then interprets each executable and executes a method on some portion of the model.
  
  As commands are run on the model, new GUI nodes must be configured. These new nodes to be created (e.g. circle, line,
  turtle in new position) are added to a Queue in the ```ViewController```, and then fed into the view according to a configured animation
  rate. For the first spring, this rate is instantaneous but in future sprints, it would be possible to create an
  animation rate that processes new nodes every set period of time.
  
   - Team case: User types in "clearscreen"
   
   When the user types "clearscreen", the command is sent to the ```LogicController``` which parses/interprets the command
   and sends it off to the model. The model has a cache of all Objects (JavaFX nodes) created by a single turtle. 
   These nodes will then be passed on to the ViewController, which will instruct the ```View``` to remove these nodes from
   the view.
   
   - Team case: User wants to reconfigure color theme of the GUI
   
   Selecting a configuration button on the GUI will populate a selection to configure the GUI theme (e.g. winter, fire, fall, etc.).
   When the user selects a theme, the information is shuttled to the ViewController (with a potential intermediate stop
   in the LogicController), which then changes the current GUI CSS/FXML styling options. 
   
   Additionally, there will be options to configure specific components of the interfaces style, and these configurations
   will edit the CSS/FXML styling file associated with the current active theme. 