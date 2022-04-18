import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

class Main extends JFrame {
    
  class App extends JPanel implements MouseListener {
    Stage stage;
    boolean stageBuilt = false;

    public App() {
      setPreferredSize(new Dimension(1024, 720));
      this.addMouseListener(this);
      stage = StageReader.readStage(3);
      stageBuilt = true;
    }

    @Override
    public void paint(Graphics g) {
      if (stageBuilt && isVisible()) {
        stage.paint(g, getMousePosition());
      }
      if(stage.getIsMissionCompleted()) {
        Font displayFont = new Font("Full Pack 2025", Font.PLAIN, 20);
        g.setColor(new Color(50,205,50));
        g.fillRect(230, 250, 500, 300);
        g.setColor(new Color(0,0,0));
        g.setFont(displayFont);
        g.drawString("CONGRATULATION ", 400, 360);
        g.drawString("RED WINS!!!", 410, 380);
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      stage.mouseClicked(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
  }

  public static void main(String[] args) throws Exception {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    File fp2025 = new File("data/full_pack_2025.ttf");
    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fp2025));
    Main window = new Main();
    window.run();
  }

  private Main() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    App canvas = new App();
    this.setContentPane(canvas);
    this.pack();
    this.setVisible(true);
  }

  public void run() {
    while (true) {
      Instant startTime = Instant.now();
      this.repaint();
      Instant endTime = Instant.now();
      long howLong = Duration.between(startTime, endTime).toMillis();
      try {
        Thread.sleep(20L - howLong);
      } catch (InterruptedException e) {
        System.out.println("thread was interrupted, but who cares?");
      } catch (IllegalArgumentException e) {
        System.out.println("application can't keep up with framerate");
      }
    }
  }
}