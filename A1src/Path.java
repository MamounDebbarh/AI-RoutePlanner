import java.util.Stack;

public class Path {
    private boolean failed;
    private boolean atGoal;
    private Stack<PositionNode> explored = new Stack<>();

    public Path(PositionNode start) {
        explored.push(start);
    }

    public void exploreAdjacent(int index) {
        if (atGoal) return;
        PositionNode nextNode = explored.peek().retrieveAdjacentNodes().get(index);
        atGoal = nextNode.isGoal();
        if (!explored.contains(nextNode) && !failed) explored.push(nextNode);
        else failed = true;
    }

    public Stack<PositionNode> getExplored() {
        if (failed || !atGoal) return null;
        return explored;
    }
}
