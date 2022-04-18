import java.awt.Polygon;
import java.util.ArrayList;

class Train extends Actor {
  public Train(Cell inLoc, float inRedness) {
    super(inLoc, inRedness, 2, 2, 2);
    filter = (Cell c) -> { return ! Track.class.isInstance(c); };
    setPoly();
  }

  protected void setPoly() {
    display = new ArrayList<Polygon>();
    int sides = 20;
    int angle;
    double circleX;
    double circleY;
    Polygon rearWheel = new Polygon();
    Polygon midWheel = new Polygon();
    Polygon frontWheel = new Polygon();
    angle = 360 / sides;
    for (int s = 0; s <= sides; s++) {
      circleX = (3.0 * Math.sin(Math.toRadians(s * angle)));
      circleY = (3.0 * Math.cos(Math.toRadians(s * angle)));
      rearWheel.addPoint(location().x + 9 + (int) circleX, location().y + 25 + (int) circleY);
      midWheel.addPoint(location().x + 17 + (int) circleX, location().y + 25 + (int) circleY);
      frontWheel.addPoint(location().x + 23 + (int) circleX, location().y + 25 + (int) circleY);
    }
    Polygon cab = new Polygon();
    cab.addPoint(location().x + 6, location().y + 7);
    cab.addPoint(location().x + 11, location().y + 7);
    cab.addPoint(location().x + 11, location().y + 20);
    cab.addPoint(location().x + 6, location().y + 20);
    Polygon body = new Polygon();
    body.addPoint(location().x + 11, location().y + 14);
    body.addPoint(location().x + 24, location().y + 14);
    body.addPoint(location().x + 29, location().y + 20);
    body.addPoint(location().x + 11, location().y + 20);
    display.add(rearWheel);
    display.add(midWheel);
    display.add(frontWheel);
    display.add(cab);
    display.add(body);
  }
}
