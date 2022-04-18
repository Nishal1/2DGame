import java.awt.Graphics;
import java.util.List;

public class CPUMoving implements GameState {
  @Override
  public void mouseClick(int x, int y, Stage s) {}

  @Override
  public void paint(Graphics g, Stage s) {
    for (Actor a: s.actors) {
        new Thread(() -> { //new thread created
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
                    //implementation of gradual color transition 
                    //within 2 seconds
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

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
