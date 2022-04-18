public class AnimationBeat {
  private long started;
  private long phiA; // length of phase a
  private long phiB; // length of phase b
  private long phiC; // length of phase c

  private static AnimationBeat instance;
  
  private AnimationBeat() {
    started = System.currentTimeMillis();
    phiA = 5000;
    phiB = 500;
    phiC = 500;
  }

  static AnimationBeat getInstance() {
    if (instance == null) {
      instance = new AnimationBeat();
    }
    return instance;
  }

  // returns which phase the animation is currently in
  char inPhase() {
    long currTime = System.currentTimeMillis();
    long rem = (currTime - started) % (phiA + phiB + phiC);
    if (rem > phiA + phiB) {
      return 'c';
    } else if (rem > phiA) {
      return 'b';
    } else {
      return 'a';
    }
  }

  // returns a number (out of 100) showing the percentage completion of this phase
  long phaseCompletion() { 
    long currTime = System.currentTimeMillis();
    long rem = (currTime - started) % (phiA + phiB + phiC);
    if (rem > phiA + phiB) {
      return ((rem - phiA - phiB) * 100) / phiC;
    } else if (rem > phiA) {
      return ((rem - phiA) * 100) / phiB;
    } else {
      return rem * 100 / phiA;
    }
  }
}