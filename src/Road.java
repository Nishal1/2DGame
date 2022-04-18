import java.awt.Color;

class Road extends Landscape {
  public Road(char col, int row, int x, int y, int z) {
    super(col, row, x, y, z);
    description = "Road";
    color = Color.getHSBColor(0.0f, 0.0f, 0.35f + 0.65f * shade);
  }
}
