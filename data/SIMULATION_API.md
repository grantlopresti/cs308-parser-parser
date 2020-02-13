# Group Discussion

### Introduction and Background
Grant teaches Turtle in Python and Scratch to little kids, CS308 is required and very interesting
Max did some Scratch in 6th grade, CS308 required and very interesting (useful for job interviews and general experience)
Alex did Scratch in middle school, and Alice (drag and drop blocks), course required and wants experience as a sophomore for software/hardware
Amjad - general experience (required), did some Turtle drawings senior year in high school

###  Group Experience
Teams should be transparent in asking for and giving help
Be proactive to complete interdependent components early (to help with testing)
Front loading design phase in the beginning which allows for easier flexibility later on for extension
Understanding that others were all at varying skill levels and all could work together to accommodate that.
Seperation of the project into very distinct projects, meant good design and easy distribution of tasks
Meeting in person and bouncing ideas off of each other adds to the team’s productivity

### API (Collections API)
Quite easy to use, well-documented and with easy to understand methods
Mistakes are relatively easy to avoid as all methods that can be called are very explicit, well named, and obvious about what they do.
Interfaces (such as linked-list) that a specific concrete class implements 5 - 7 interfaces.
Most of the concrete classes have between 4 and 5 superclasses and 6-7 implemented interfaces at their specific level. Their superclasses also have 
Utility classes can be used when data and general object encapsulation isn’t necessary. These methods operate on just the parameters passed in to them, with a single return. When there is overlap in methods between the utility classes and specific collections types, use the one most specific to the collection type

### Simulation Project Review


### Initial SLogo Design
JavaFX Circle
JavaFX Line
Required Basic Commands

Parsing process:
	First option:
Layered (first to read files, unwind the code into single line sequential commands (“compiler”)
Second layer processes single line commands
User clicks “Run” button to do the second step
The one-time parsed file can be stored in memory (in a data structure), or (maybe additional feature) be exported to the hard disk if the user wants
Challenge is to determine what set of commands are allowed in the “compiled’ file
Most basic commands
Conditionals? Which parser handles this?
“pseudo-commands” ? special math commands

	Second Option:
Parse once and use programming “logic” to look for certain keywords execute commands 

Single-line commands should be parsed executed immediately

General Pipeline

General Design
Clear cut separation of Model, View, and Controller
Turtle needs to know direction and location (x and y)
Turtle is part of model, just kind moves around
Visualizer sole purpose is to visualize thing
After each command, turtle
TurtleState (in Visualization) to represent color and visibility
Pen object with thickness and color (alternatively mapping to color that JavaFX can interpret), up/down state

Case Study (forward 50)
GUI (Visualization) inputs text (from line or file), sends over to parser
Parser (Controller) handles exception, formats text to run appropriately


Duvall Discussion
APIs on turtle, interfaces that it can implement
String command, tree/array of commands to create
Creating view stuff out of the model
Model and View should be “pure”
Controller class is the “barter” between, impure and has handshakes between the two, coordinates the distinction
Turtle shouldn’t know it's color

Implementation ideas:
Nothing should be case sensitive - maybe filtering process from input -> uppercase -> map for command
“FoRWard” → “FORWARD” → “FD” (all cases of forward processed this way)
Use an unwraveler to take input text, then separate into line by line commands (factors in lists, repeats, etc.)
Queue to hold single commands execute in order
“Reflection” to verify if object has a specific command

Types of Commands:
Interfaces to group together “similar” commands
Movement
Math
Conditionals
Loops
