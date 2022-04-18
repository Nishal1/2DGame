import java.awt.Color;

class Mountain extends Landscape {
  public Mountain(char col, int row, int x, int y, int z) {
    super(col, row, x, y, z);
    description = "Mountain";
    color = Color.getHSBColor(60.0f / 360.0f, 1.0f - shade, 0.35f + 0.65f * shade);
  }
}
