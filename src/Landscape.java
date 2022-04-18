abstract class Landscape extends Cell {
  protected int height;
  protected float shade;

  public Landscape(char col, int row, int x, int y, int z) {
    super(col, row, x, y);
    // we could have used an exception here, however
    // as they were not a requirement of the assignment
    // we'll just force the floor and ceiling for now
    if (z < -2000) {
      z = -2000;
    }
    if (z > 8000) {
      z = 8000;
    }
    height = z;
    // in order to avoid shades that are almost black or white
    // we will user shades between 35% and 85%
    shade = ((float) height + 2000) / 10000; // * 0.5f + 0.35f;
  }

  public int elevation() {
    return height;
  }
}
