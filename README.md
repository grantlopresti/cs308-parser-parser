parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Alex Xu, Amjad Syedibrahim, Max Smith, Grant LoPresti

### Timeline

**Start Date:** 2/13/2020

**Finish Date:** 3/7/2020

**Hours Spent:** 200 Hours Total

### Primary Roles

Alex Xu:
* Wrote code for the model package (ModelObjects, Turtles, Pens, ModelCollection, etc.), codefilters package, worked on the 
LogicalController and Parser design, as well as creating the command objects, variable objects, and certain exceptions.

Amjad Syedibrahim:
* Wrote code for the various functionalities of the Parser's basic implementation. Wrote helper methods in UserInput class to deal with proper creation of command variables.
Implemented controlFlowExtractor utility class, worked on user-defined commands and control flow structures.


Max Smith:
* Wrote code for the visualController, implemented and designed the latest iteration of the Parser, redesigned Command object hierarchy
and interfaces, wrote exceptions to improve error handling, implemented and designed and multiple turtles.

Grant LoPresti:
* Created the front-end design and implementation of the GUI in the SLogo program. (Add more here)

### Resources Used

* Oracle Java Online Documentation
* Computer Science 308 Course Website, Piazza, and Readings
* StackOverflow postings

### Running the Program

Main class: Main.java

Data files needed:
* All files in ```/parser_team10/resources```, including ```/images/```, ```/languages/```, and ```/stylesheets/```
* All files in ```src/properties```

##### Features implemented:
###### Basic
Front End
* Enter commands to interact with turtle
* See results visually
* See errors from backend (command generation)
* Set background color for display area
* Set image for turtle
* See commands previously run in environment
* Choose language available in environment
* Access help about available commands

Back End
* Recognize basic slogo commands (all in some capacity) 
* Throw errors from incorrectly entered commands (formatted)


###### Complete
Front End
* Current state of turtle (ID, heading, pen state, etc.)
* Palettes of images and colors to select (via GUI, not command)
* Click to execute commands from history
* Move current turtle(s) graphically by fixed amount
* Create multiple, independent workspaces through splash screen
* Preferences for workspaces, setting defaults through splash screen
* Animations on turtles as command executes

Back End
* Additional commands - infrastructure for extensibility (e.g. tell works)
* Support for multiple turtles

### Notes/Assumptions

Assumptions or Simplifications:
* Assumed that user input would be space separated
* Assumption that control flow statements (for, repeat) have brackets on the first line
* Assumed that errors could be combined into a single error for user display (e.g. all reflection exceptions get packaged into one exception)

Interesting data files:
* See ```data/examples/interesting```

Known Bugs:
* Any command that does not cause an error will be displayed as a user input (e.g. blah blah, however these do not get executed)
* Variables can be created, but not used (part of parsing design)

Extra credit:
* ```slogo/logicalcontroller/codefilters``` package to massage data before parsing (with toggleable filters through properties file)
* Toggleable ```DarkMode``` in view to change style sheet of whole page

### Impressions

* Designing the parsing algorithm was a challenge for this project
* Interesting to see how data-driven design and design patterns can be used to create a program that evaluates another
"programming language".
* Late night, pair programming sessions were surprisingly productive, many benefits (accountability, perspective, motivation)
seen in group programming sessions from design and implementation perspective.
* Design emphasis in the beginning definitely payed off in the end
* EVERYTHING is a tradeoff, cannot be open to everything, must evaluate pros and cons of each decision and decide on the 
"best" and/or "least bad" option.