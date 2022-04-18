import java.awt.Color;

class Water extends Landscape {
  public Water(char col, int row, int x, int y, int z) {
    super(col, row, x, y, z);
    description = "Water";
    color = Color.getHSBColor(240.0f / 360.0f, 1.0f - shade, 0.35f + 0.65f * shade);
  }
}
