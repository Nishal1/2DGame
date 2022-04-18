import java.awt.Graphics;

public interface GameState {
  public void mouseClick(int x, int y, Stage s);

  public void paint(Graphics g, Stage s);
}
