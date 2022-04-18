import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Stage {
  Grid grid;
  List<Actor> actors;
  List<Cell> cellOverlay;
  List<MenuItem> menuOverlay;
  Optional<Actor> actorInAction;

  GameState currentState;
  InfoPanel sidePanel;

  boolean isMissionCompleted;

  Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    cellOverlay = new ArrayList<Cell>();
    menuOverlay = new ArrayList<MenuItem>();
    actorInAction = Optional.empty();
    currentState = new CPUMoving();
    sidePanel = new SidePanel();
    sidePanel.add(new StageInfo(this));
    sidePanel.add(new ActorInfo(this));
    isMissionCompleted = false;
  }

  void paint(Graphics g, Point mouseLoc) {
    // do we have AI moves to make
    currentState.paint(g, this);

    grid.paint(g, mouseLoc);
    grid.paintOverlay(g, cellOverlay, new Color(0f, 0f, 1f, 0.5f));
    
    actors.forEach(a -> a.paint(g));

    int yloc = 20;
    sidePanel.paint(g, yloc, mouseLoc);

    // menu overlay (on top of everything else)
    menuOverlay.forEach(mi -> mi.paint(g));

    isMissionCompleted = isAllActorsRed();
  }

  List<Cell> getClearRadius(Cell from, int size, boolean considerElevation) {
    List<Cell> clearCells = grid.getRadius(from, size, considerElevation);
    for (Actor a : actors) {
      clearCells.remove(a.location());
    }
    return clearCells;
  }

  void mouseClicked(int x, int y) {
    currentState.mouseClick(x, y, this);
  }

  Optional<Actor> actorAt(Cell c) {
    for (Actor a : actors) {
      if (a.location() == c) {
        return Optional.of(a);
      }
    }
    return Optional.empty();
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
}
