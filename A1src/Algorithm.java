import java.util.*;

abstract public class Algorithm {
    // Search Components
    HashMap<PositionNode, PositionNode> exploredSet;
    private ArrayList foundPath;
    private ArrayList<String> directions = new ArrayList<>();


    // Inputs
    PositionNode start;

    abstract public ArrayList searchPath();

    private int nodesExpanded;
    private static String newline = System.getProperty("line.separator");


    // -----------------------
    // Constructor
    // -----------------------

    Algorithm(PositionNode start, char goalState, char[][] map) {
        this.start = start;
        this.exploredSet = new HashMap<>();
        // The first entry in the explored set is the start state, which does not have a parent node.
        this.exploredSet.put(start, null);
    }


    // -----------------------
    // Methods
    // -----------------------

    void doSearch() {
        // Start search algorithm
        this.foundPath = searchPath();

        // Set performance measures
        this.nodesExpanded = exploredSet.size();
        // Performance attributes
        System.out.println(this);
    }

    ArrayList<PositionNode> reconstructPath(PositionNode node) {
        // Going back through the explored set to reconstruct path.
//        System.out.println("STARTING PATH RECONSTRUCTION");
        ArrayList<PositionNode> foundPath = new ArrayList<>();
        do {
//            System.out.println("Node added : " + node);
//            System.out.println("Explored set :" + this.exploredSet);
//            System.out.println("Explored set node we get:" + this.exploredSet.get(node));
            if (node.y == 7 && this.exploredSet.get(node).y == 0) {
                this.directions.add("H270");
            } else if (node.y == 0 && this.exploredSet.get(node).y == 7) {
                this.directions.add("H90");
            } else if (node.x < this.exploredSet.get(node).x) {
                this.directions.add("H360");
            } else if (node.x > this.exploredSet.get(node).x) {
                this.directions.add("H180");
            } else if (node.y < this.exploredSet.get(node).y) {
                this.directions.add("H270");
            } else if (node.y > this.exploredSet.get(node).y) {
                this.directions.add("H90");
            }
            foundPath.add(node);
            node = this.exploredSet.get(node);
        } while (node.getParent() != null);

        // Add initial node to output
        foundPath.add(node);
        Collections.reverse(this.directions);
        Collections.reverse(foundPath);
        return foundPath;
    }


    @Override
    public String toString() {
        return "Path Direction: " + directions + newline +
                "Path found: " + foundPath + newline +
                "Search states visited: " + nodesExpanded + newline +
                "Path length: " + (foundPath.size() - 1);
    }
}


