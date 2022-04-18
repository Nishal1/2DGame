Explanations of causes of rogue bug:

The Rougue bug causes the program to freeze for a few seconds 
before running the gameplay normaly again. There are two
rogue characters, a helicopter and a submarine. The freeze
occurs when the helicopter is moved to water type cell or
when a submarine is moved to a non-water type cell. It happens
when after the move has taken place, and when the user choses
his character and moves the character and presses end turn.
At that point, the game freezes for a few seconds. 

The bug is caused from the setLocation() method of the Rogue
class. After setting the location, the thread is put to sleep
or timeout for a few seconds before it can be started again.
This is the root cause of the bug.