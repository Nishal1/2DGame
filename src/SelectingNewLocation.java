import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Optional;

public class SelectingNewLocation implements GameState {
  @Override
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
            theActor.isTransition = true;//color transition in process
          }
          theActor.isTransition = false; //color transition complete
        }
      }
    }).start();
  }

  @Override
  public void paint(Graphics g, Stage s) {}

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
