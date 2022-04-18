import java.awt.Color;

class Track extends Landscape {
  public Track(char col, int row, int x, int y, int z) {
    super(col, row, x, y, z);
    description = "Track";
    // shade at the lighter end of the spectrum to avoid confusion with the building color
    color = Color.getHSBColor(30.0f / 360.0f, 1.0f - shade, 0.55f + 0.45f * shade);
  }
}
