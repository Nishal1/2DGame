import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Timer {
  long jiffies;
  Instant started;

  public Timer(long ms) {
    jiffies = ms;
    started = Instant.now();
  }

  public boolean completed() {
    return started.isBefore(Instant.now().minusMillis(jiffies));
  }

  long remaining() {
    if (completed()) {
      return 0;
    } else {
      return jiffies - ChronoUnit.MILLIS.between(started, Instant.now());
    }
  }
}
