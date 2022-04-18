import java.util.List;

interface MoveStrategy {
  public Cell chooseNextLoc(Cell currentLoc, List<Cell> possibleLocs);
}
