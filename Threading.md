Explanation of the implemented solution:

Files altered in making the changes:
1. CPUMoving.java
2. SelectingNewLocation.java
3. Actor.java
4. ColorMixer.java
5. Stage.java

Descriptions of changes made:
* In paint method of CPUMoving.java class, new threads have been 
created while iterating over actors array list. Further, the thread
has been made synchronized to make sure that they are thread safe.
In the end of the synchronised block, a try-catch block has been 
added to handle the two second pause for actors moved by the AI. 
Within the try block, a while loop has been added to implement the
gradual color transition. The following code is after the additions 
made in the paint method of CPUMoving:
    public void paint(Graphics g, Stage s) {
        for (Actor a: s.actors) {
            new Thread(() -> { //new threads created for actors
            synchronized(a) {
                while(true) {
                    if(! a.isTeamRed()) {
                    List<Cell> possibleLocs = s.getClearRadius(a.location(), a.getMoves(), true);
                    possibleLocs.removeIf(a.filter);
                    Cell nextLoc = a.strategy().chooseNextLoc(a.location(), possibleLocs);

                    if(a instanceof Rogue) {
                        a.loc = nextLoc;
                    } else {
                        a.setLocation(nextLoc);
                    }
                
                    try {
                        Timer countdown = new Timer(2 * 1000);
                        while(!countdown.completed()) {
                        float proportion = (float) countdown.remaining() / countdown.jiffies;
                        float hue = (proportion * (a.color.CYAN - a.color.BLUE) + a.color.CYAN) / 360.0f;
                        a.color.setHue(hue);
                        a.isTransition = true;
                        }
                        a.isTransition = false;
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } 
                    }
                }
                }
            }).start();
        }
        s.currentState = new ChoosingActor();
        for (Actor a: s.actors) {
        a.resetTurns();
        }
    }

* The mouseClicked method of the SelectingNewLocation.java class has
been modified. The contents of this method is added onto a thread as
well. Further, the thread is made synchronized to avoid any race
conditions as well. At the end of the code, a timer and a while loop
has been added to gradually change colors within the two seconds 
interval as per the specifications. The following is the mouseClicked
method after addition of changes:

    public void mouseClick(int x, int y, Stage s) {
        new Thread(() -> { //new thread created
        synchronized(this) {
            Optional<Cell> clicked = Optional.empty();
            for (Cell c : s.cellOverlay) {
            if (c.contains(x, y)) {
                clicked = Optional.of(c);
            }
            }
            if (clicked.isPresent() && s.actorInAction.isPresent()) {
            s.cellOverlay = new ArrayList<Cell>();
            Actor theActor = s.actorInAction.get();
            if(theActor instanceof Rogue) {
                theActor.loc = clicked.get();
            } else {
                theActor.setLocation(clicked.get());
            }

            theActor.turnTaken();
            s.menuOverlay.add(new MenuItem("Fire", x, y, () -> {
                s.cellOverlay = s.grid.getRadius(theActor.location(), theActor.getRange(), false);
                s.cellOverlay.remove(theActor.location());
                s.currentState = new SelectingTarget();
            }));    
            s.currentState = new SelectingMenuItem();

            //implementation of gradual color transition within 2 
            //seconds
            Timer countdown = new Timer(2 * 1000);
            while(!countdown.completed()) {
                float proportion = (float) countdown.remaining() / countdown.jiffies;
                float hue = (proportion * (theActor.color.YELLOW - theActor.color.GREEN) + theActor.color.YELLOW) / 360.0f;
                theActor.color.setHue(hue);
                theActor.isTransition = true;
            }
            theActor.isTransition = false;
            }
        }
        }).start();
    }

* In Actor.java a new instance variable isTransition has been added 
(in Actor abstract class). The purpose of this feild is to let the 
paint method of actor know if the particular actor is undergoing a 
color transition. Therefore, the paint method can paint the colors 
of the actor character appropriately. The isTransition has been 
initially set to false using eager initialization. The eager 
initialization has been made as, at the start of the game, actors are 
not changing their location. Additions made in the instance variable 
list:
    protected boolean isTransition = false;
Addtions made in paint method of Actor.java:
    void paint(Graphics g) {
        for (Polygon p : display) {
        if(!isTransition) {
            //set normal color according to redness value
            g.setColor(color.mix(redness));
        } else {
            //undergoing a color transition
            g.setColor(color.getStepColor());
        }
        g.fillPolygon(p);
        g.setColor(Color.GRAY);
        g.drawPolygon(p);
        }
    }
The getStepColor() method will be discussed next.

* In ColorMixer.java, a new method called getStepColor() has been
created. The method basically returns the Color object based on the
HSB values of the object instance. This method has been used in the
paint() method of Actor class to paint the transitioning colors after
making an actor move. The reason why this is particularly useful is
because the hue instance has been changed for the purpose of color
transition and this method returns the Color object elegantly. The
additions made in ColorMixer.java:
    public Color getStepColor() {
        return Color.getHSBColor(hue, intensity, brightness);
    }

* A minor modification has been made in the Stage.java (stage class)
constructor. Initially at the start of the game, the currentState is
set to new ChoosingActor() (currentState = new ChoosingActor()). What
this essentially means is the blue team actors (the AI controlled 
actors) would not start to play in parallel untill the human player
have started the game. To eliminate this, currentState has been set 
to CPUMoving instead (currentState = new CPUMoving()). This change
makes the game more visually appealing and logically sound as well.
Modification made in Stage.java constructor:
    currentState = new CPUMoving(); 

Reasons for chosing the solution:
* I chose threading blocks of code using synchronized keyword as
this gave the ability to only synchronize segments of code that
needed to be synchronized over synchronizing the enitre method.
* The threading of actors has been done within the for loop in
paint method of CPUMoving class. This not only keeps actors in
threads, but also implements the functionality of parallelism
in the movement of actors where the actors in both red and blue
teams move in parallel.
* The two second pause for the AI moved actors has been done
with help of Timer object that times the two second interval. A 
while loop that runs until the timer is completed is implemented. 
Within the loop appropriate transitioning logic has been implemented 
that sets the hue property of color instance of the respective actor
object. Once the two second period is over, the while loops stops 
executing and thread is put to sleep for a second to make the pause
more visually appealing as well.
* The two seconds pause for the human moved characters has been 
implemented in a similar way to AI controlled actors. The only 
major difference is that the thread is not put to sleep.

Problems encountered in the solution:
* There are no major problems with the current solution.
* The actors of Rogue types do not transition when location changes 
to Yellow/Green based on human/AI controlled. However, all the other 
actors transition gradually as per the specification.


