import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class StageInfo extends InfoPanel {
  Stage stage;

  public StageInfo(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void paint(Graphics g, int yloc, Point mouseLoc) {
    g.setColor(Color.DARK_GRAY);
    g.drawString(stage.currentState.toString(), margin, yloc);
    yloc = yloc + blockVtab;
    Optional<Cell> underMouse = stage.grid.cellAtPoint(mouseLoc);
    if (underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      String coord = String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row);
      g.drawString(coord, margin, yloc);
      g.drawString(hoverCell.description, margin + 3 * blockVtab, yloc);
      if (hoverCell instanceof Landscape) {
        Landscape land = (Landscape) hoverCell;
        g.drawString("elevation", margin, yloc + vTab);
        g.drawString(String.valueOf(land.elevation()), margin + 3 * blockVtab, yloc + vTab);
      }
    }
  }
}
