# DESIGN_PLAN.md

##Basic Design

###Introduction
This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).
###Overview
This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. Include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). To keep these classes as flexible as possible, your team must describe two different implementations (i.e., data structures, file formats, etc.) and then design your method signatures so they do not reveal the specifics of either implementation option. Discuss specific classes, methods, and data structures, but not individual lines of code.
###User Interface
This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Also describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.
###Design Details
This section describes each API introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.
###API as Code
Your APIs should be written as Java interfaces, types that cannot contain instance variables or private methods, in appropriate packages. These should be Java code files that compile and contain extensive comments to explain the purpose of each interface and each method within the interface (note this code can be generated directly from a UML diagram). Include any Exceptions you plan to throw because of errors that might occur within your methods. Note, this does not require that all of these types will remain as interfaces in the final implementation, just that the goal is for you to focus on each type's behavior and purpose. Also include the steps needed to complete the Use Cases below to help make your ideas more concrete.
###Design Considerations
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length and describe at least one alternative in detail (including pros and cons from all sides of the discussion). Describe any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.
###Team Responsibilities
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

##API Design

Clearly describe the program's four APIs and justify your reasoning for the design of each:

###External: between the two separate sub-groups
- How you plan to separate the graphical interface from the interpreter and how you plan to let
  them communicate when necessary
- What objects will be used for communication, making it clear:
    - how needed information will get where it is needed
    - what information will be immutable
    - what data will be encapsulated
    - what errors may be thrown
    - _Note, all of these methods will need to be public_
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


##Use Cases
Clearly show the flow of calls to public methods described in your design needed to complete each example below, indicating in some way which class contains each method called:

- The user types 'fd 50' in the command window, and sees the turtle move in the display window
 leaving a trail, and the command is added to the environment's history.
- Note, it is not necessary to understand exactly how parsing works in order to complete this
 example, just what the result of parsing the command will be.
- The user sets the pen's color using the UI so subsequent lines drawn when the turtle moves use
 that color.
- Additionally, each member of the team should create two use cases of their own (and the
 appropriate example code) for the part of the project for which they intend to take responsibility.
- Note, these can still be done as a group, but should represent a variety of areas of the overall
 project.