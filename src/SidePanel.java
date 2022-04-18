import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class SidePanel extends InfoPanel {
  ArrayList<InfoPanel> infoPanels = new ArrayList<InfoPanel>();

  @Override
  public void add(InfoPanel panel) {
    infoPanels.add(panel);
  }

  @Override
  public void remove(InfoPanel panel) {
    infoPanels.remove(panel);
  }

  @Override
  public InfoPanel getChild(int i) {
    return infoPanels.get(i);
  }

  @Override
  public void paint(Graphics g, int yloc, Point mouseLoc) {
    for (InfoPanel panel : infoPanels) {
      panel.paint(g, yloc, mouseLoc);
    }
  }
}
