import java.util.ArrayList;
import java.util.Objects;

public class PositionNode {
    // Variables
    int x;
    int y;
    private double pathcost;
    private int gcost;
    private char nodeState;
    private PositionNode parent;
    private int[] goalCoords;
    private static char[][] map;
    private static int mapXSize;
    private static int mapYSize;
    private static char obstacleIdentifier;

    // Constructor, calling this generates the PositionNode's children nodes
    PositionNode(int x, int y, PositionNode parent, int[] goalCoords) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.nodeState = map[this.x][this.y];
        if (!(this.parent == null)) {
            // Higher parallels have higher costs
            this.pathcost = this.parent.pathcost + 1;


        }
        this.goalCoords = goalCoords;
        // Heuristic
        this.gcost = Math.abs(this.x - goalCoords[0]) + Math.abs(this.y - goalCoords[1]) + this.x;
        // Distance
    }

    // -----------------------
    // Methods
    // -----------------------
    ArrayList<PositionNode> retrieveAdjacentNodes() {
        // Children nodes need to be reachable from parent node by movement of one unit on one coordinate axis.
        ArrayList<PositionNode> children = new ArrayList<>(4);
        // Add x-axis reachable nodes.
        for (int xDelta = -1; xDelta <= 1; xDelta += 2) {
            int nextX = this.x + xDelta;
            // Ensure the boundaries of the map are respected.
            if (nextX >= 0 && nextX < mapXSize) {
                PositionNode child = new PositionNode(nextX, this.y, this, this.goalCoords);
                if (child.isValid()) {
                    children.add(child);
                }
            }
        }

        /**
         * Since we have a circle as our flight space,
         * we must be able to loop from angle 0 to angle 315.
         * That means that when we our adjacent nodes must check the surrounding of -1 of the angle index as index 7.
         */
        for (int yDelta = -1; yDelta <= 1; yDelta += 2) {
            int nextY = this.y + yDelta;
            if (nextY >= 0 && nextY < mapYSize) {
                PositionNode child = new PositionNode(this.x, nextY, this, this.goalCoords);
                if (child.isValid()) {
                    children.add(child);
                }
            } else if (nextY == mapYSize) {
                nextY = 0;
                PositionNode child = new PositionNode(this.x, nextY, this, this.goalCoords);
                if (child.isValid()) {
                    children.add(child);
                }
            } else if (nextY == -1) {
//                System.out.println("mapYsize : " + mapYSize + " map[0].length : " + map[0].length + " MapXsize : " + mapXSize + " map.length : " + map.length);
                nextY = 7;
                PositionNode child = new PositionNode(this.x, nextY, this, this.goalCoords);
                if (child.isValid()) {
                    children.add(child);
                }
            }
        }

        return children;
    }

    static void setMap(char[][] newMap) {
        // Set map related variables.
        map = newMap;
        mapXSize = map.length;
        mapYSize = map[0].length;
        obstacleIdentifier = 'X';
    }

    @Override
    public String toString() {
        return ("(" + this.getX() + ", " + this.getY() + ")");
    }

    @Override
    public boolean equals(Object obj) {
        // Needed to make checking for existance in frontier and explored set work.
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PositionNode)) {
            return false;
        }
        PositionNode other = (PositionNode) obj;
        // Attributes to uniquely identify a node: x- and y-coordinates and nodeState.
        return this.getX() == other.getX() && this.getY() == other.getY() && this.getNodeState() == other.getNodeState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getNodeState());
    }

    // -----------------------
    // Getter Methods
    // -----------------------

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    char getNodeState() {
        return this.nodeState;
    }

    PositionNode getParent() {
        return this.parent;
    }

    boolean isGoal() {
        return this.nodeState == 'g';
    }

    boolean isValid() {
        return this.nodeState != obstacleIdentifier;
    }

    double getHeuristicCost() {
        return this.pathcost + this.gcost;
    }

    int getBestFirstCost() {
        return this.gcost;
    }


    // -----------------------
    // Subclasses
    // -----------------------


}

