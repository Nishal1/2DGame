import java.awt.Color;

public class ColorMixer {
  public static final float RED = 0 * 60;
  public static final float YELLOW = 1 * 60;
  public static final float GREEN = 2 * 60;
  public static final float CYAN = 3 * 60;
  public static final float BLUE = 4 * 60;
  public static final float MAGENTA = 5 * 60;
  protected float hue;
  protected float intensity;
  protected float brightness;

  public ColorMixer() {
    intensity = 1.0f;
    brightness = 1.0f;
  }

  public Color mix(float val) {
    float tint = (val * (RED - BLUE + 360.0f) + BLUE) / 360.0f;
    return Color.getHSBColor(tint, intensity, brightness);
  }

  public void setHue(float newHue) {
    hue = newHue;
  }

  public void setIntensity(float newIntensity) {
    intensity = newIntensity;
  }

  public void setBrightness(float newBrightness) {
    brightness = newBrightness;
  }

  public Color getStepColor() {
    return Color.getHSBColor(hue, intensity, brightness);
  }
}
