Description of the solution to Rogue bug:

The Rogue bug is caused by the setLocation method of the Rogue
class. As the implementation to this class is not available,
the bug cannot be fixed fully. The workaround I decided to 
follow was to not call the setLocation method, rather
change the location directly from the instance variables for 
Rogue instances.

There are two main places where the actors' location has been
set, namely in CPUMoving and SelectingNewLocation classes. In these
classes, after appropriate operations has taken place to choose the 
cell location, the setLocation() method is called upon the actor 
object. Here, I implemented a simple if-else block to check if the 
actor object is of Rogue instance, if so then directly set the 
location from the instance variable (loc) instead of calling the 
method of setLocation on Rogue instance. Otherwise, if it is not of 
Rogue instance, then invoke the setLocation() method as usual. The 
following is the if-else block: 

Addition in CPUMoving.java:
    if(a instanceof Rogue) { //if it is rogue instance
    //then set the location without invoking the setLocation method
      a.loc = nextLoc;
    } else {
    //at this point, actor is not of rogue instance
    //hence, call the setLocation() method as usual
      a.setLocation(nextLoc);
    }

Addition in SelectingNewLocation.java:
    if(theActor instanceof Rogue) {
      theActor.loc = clicked.get();
    } else {
      theActor.setLocation(clicked.get());
    }  

class files changed:
1. CPUMoving.java
2. SelectingNewLocation.java