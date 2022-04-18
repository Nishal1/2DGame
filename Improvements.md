Further improvements made to the application:

The game as per the current stands do not show a congratulatory 
message. Therefore, I have made changes to the code such that once
all the actors of the game is part of the red team, the game displays
a congratulatory message that simply says "CONGRATULATION RED WINS 
!!!".

Files modified to implement the change:
1. Main.java
2. Stage.java

Descriptions of changes made:
* In Stage.java (stage class) an additional instance variable called
'isMissionCompleted' is added. This variable is of boolean type. It is
set to true if all actors in the grid is part of red team and false 
otherwise. It has been instantiated to false at the start in the stage
class as in the start there are actors from blue team as well. 
Further, a method called isAllActorsRed() has been added. This method
checks for team status of actors and based on it sets the 
isMissionCompleted variable to true/false. A getter function for
isMissionCompleted has also been added. Following are codes added
in Stage.java:
    boolean isMissionCompleted; //add instance variable
    Stage() {
        ..
        ..
        isMissionCompleted = false; //set it to false
    }
    void paint(Graphics g, Point mouseLoc) {
        ..
        isMissionCompleted = isAllActorsRed(); //check status of team
    }
    boolean isAllActorsRed() {
        for(Actor a: actors) {
        if(!a.isTeamRed()) {
            //if one actor is not team red, return false
            return false;
        }
        }
        //at this point all actors are team red
        return true;
    }

    boolean getIsMissionCompleted() {
        return isMissionCompleted;
    }
* The paint method of the App class (nested inside the Main class in
Main.java) has been modified. Inside this particular paint method, 
an if block has been added. The if block only gets executed if all the
actors are in red team. Inside the if block, code has been written so
that a green box with the congratulation message get displayed on top
of the game board on the screen. Following is the paint method after
adding the code:
    public void paint(Graphics g) {
        if (stageBuilt && isVisible()) {
            stage.paint(g, getMousePosition());
        }
        //new code start:
        if(stage.getIsMissionCompleted()) {
            Font displayFont = new Font("Full Pack 2025", Font.PLAIN, 20);
            g.setColor(new Color(50,205,50));
            g.fillRect(230, 250, 500, 300);
            g.setColor(new Color(0,0,0));
            g.setFont(displayFont);
            g.drawString("CONGRATULATION ", 400, 360);
            g.drawString("RED WINS!!!", 410, 380);
        }
        //new code end
    }