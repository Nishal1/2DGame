import java.util.List;
import java.util.Random;

public class RandomMove implements MoveStrategy {
  @Override
  public Cell chooseNextLoc(Cell currentLoc, List<Cell> possibleLocs) {
    Cell newLoc;
    if (possibleLocs.size() > 0) {
      int i = (new Random()).nextInt(possibleLocs.size());
      newLoc = possibleLocs.get(i);
    } else {
      newLoc = currentLoc;
    }
    return newLoc;
  }

  public String toString() {
    return "random movement strategy";
  }
}
