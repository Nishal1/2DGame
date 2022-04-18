import java.util.List;

class LeftMostMove implements MoveStrategy {
  @Override
  public Cell chooseNextLoc(Cell currentLoc, List<Cell> possibleLocs) {
    Cell currLeftMost;
    if (possibleLocs.size() > 0) {
      currLeftMost = possibleLocs.get(0);
      for (Cell c : possibleLocs) {
        if (c.leftOfComparison(currLeftMost) < 0) {
          currLeftMost = c;
        }
      }
    } else {
      currLeftMost = currentLoc;
    }
    return currLeftMost;
  }

  public String toString() {
    return "left-most movement strategy";
  }
}
