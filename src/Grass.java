import java.awt.Color;

class Grass extends Landscape {
  public Grass(char col, int row, int x, int y, int z) {
    super(col, row, x, y, z);
    description = "Grass";
    color = Color.getHSBColor(120.0f / 360.0f, 1.0f - shade, 0.35f + 0.65f * shade);
  }
}
