import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;
import java.util.function.Predicate;

abstract class Actor {
  protected Cell loc;
  protected float redness;
  protected int initialTurns;
  protected int turns;
  protected int moves;
  protected int range;
  protected MoveStrategy strat;
  protected List<Polygon> display;
  protected Predicate<Cell> filter;
  protected ColorMixer color;
  protected boolean isTransition = false;

  public Actor(Cell inLoc, float inRedness, int inTurns, int inMoves, int inRange) {
    setLocation(inLoc);
    redness = inRedness;
    initialTurns = inTurns;
    turns = initialTurns;
    moves = inMoves;
    range = inRange;
    strat = new RandomMove();
    color = new ColorMixer();
    color.setHue((1.0f - redness) * ColorMixer.BLUE);
  }

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

  protected abstract void setPoly();

  boolean isTeamRed() {
    return redness >= 0.5;
  }

  void setLocation(Cell inLoc) {
    loc = inLoc;
    setPoly();
  }

  void makeRedder(float amt) {
    redness = redness + amt;
    if (redness > 1.0f) {
      redness = 1.0f;
    }
  }

  public Cell location() {
    return loc;
  }

  public MoveStrategy strategy() {
    return strat;
  }

  public float getRedness() {
    return redness;
  }

  public int turnsLeft() {
    return turns;
  }

  public int maxTurns() {
    return initialTurns;
  }

  public void resetTurns() {
    turns = initialTurns;
  }

  public void turnTaken() {
    turns--;
  }

  public int getMoves() {
    return moves;
  }

  public int getRange() {
    return range;
  }

  public String toString() {
    return this.getClass().getName().toString();
  }
}
