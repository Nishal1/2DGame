import java.awt.Polygon;
import java.util.ArrayList;

class Boat extends Actor {
  public Boat(Cell inLoc, float inRedness) {
    super(inLoc, inRedness, 3, 1, 4);
    filter = (Cell c) -> { return ! Water.class.isInstance(c); };
    setPoly();
  }

  protected void setPoly() {
    display = new ArrayList<Polygon>();
    Polygon leftSail = new Polygon();
    leftSail.addPoint(location().x + 16, location().y + 11);
    leftSail.addPoint(location().x + 11, location().y + 24);
    leftSail.addPoint(location().x + 16, location().y + 24);
    Polygon rightSail = new Polygon();
    rightSail.addPoint(location().x + 18, location().y + 7);
    rightSail.addPoint(location().x + 24, location().y + 24);
    rightSail.addPoint(location().x + 18, location().y + 24);
    Polygon body = new Polygon();
    body.addPoint(location().x + 6, location().y + 24);
    body.addPoint(location().x + 29, location().y + 24);
    body.addPoint(location().x + 24, location().y + 29);
    body.addPoint(location().x + 11, location().y + 29);
    display.add(leftSail);
    display.add(rightSail);
    display.add(body);
  }
}
