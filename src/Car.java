import java.awt.Polygon;
import java.util.ArrayList;

class Car extends Actor {
  public Car(Cell inLoc, float inRedness) {
    super(inLoc, inRedness, 1, 1, 3);
    filter = (Cell c) -> { return ! Road.class.isInstance(c); };
    setPoly();
  }

  protected void setPoly() {
    display = new ArrayList<Polygon>();
    int sides = 20;
    int angle;
    double circleX;
    double circleY;
    Polygon rearWheel = new Polygon();
    Polygon frontWheel = new Polygon();
    angle = 360 / sides;
    for (int s = 0; s <= sides; s++) {
      circleX = (4.0 * Math.sin(Math.toRadians(s * angle)));
      circleY = (4.0 * Math.cos(Math.toRadians(s * angle)));
      rearWheel.addPoint(location().x + 11 + (int) circleX, location().y + 25 + (int) circleY);
      frontWheel.addPoint(location().x + 24 + (int) circleX, location().y + 25 + (int) circleY);
    }
    Polygon body = new Polygon();
    body.addPoint(location().x + 6, location().y + 14);
    body.addPoint(location().x + 29, location().y + 14);
    body.addPoint(location().x + 29, location().y + 20);
    body.addPoint(location().x + 6, location().y + 20);
    Polygon top = new Polygon();
    top.addPoint(location().x + 11, location().y + 7);
    top.addPoint(location().x + 20, location().y + 7);
    top.addPoint(location().x + 24, location().y + 14);
    top.addPoint(location().x + 11, location().y + 14);
    display.add(rearWheel);
    display.add(frontWheel);
    display.add(body);
    display.add(top);
  }
}
