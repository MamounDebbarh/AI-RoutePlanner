import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends Algorithm {
    private PriorityQueue<PositionNode> frontier;

    AStar(PositionNode start, char goalState, char[][] map) {
        // Search for PositionNode with goal state in map
        super(start, goalState, map);

        Comparator<PositionNode> aStarNodeComparator = new NodeComparator();

        this.frontier = new PriorityQueue<>(1, aStarNodeComparator);
        this.frontier.add(start);

        this.doSearch();
    }

    @Override
    public ArrayList<PositionNode> searchPath() {

        // AStar Search implementation

        PositionNode currentNode;
        while (true) {
            //sort the data structure to allow lower parallels to have priority.

            // Always expand nodes in the order they have been generated.
            currentNode = this.frontier.poll();

            // If the frontier is empty after expanding the current node, no solution has been found
            if (currentNode == null) {
                System.out.println("No solution found!");
                return new ArrayList<>();
            }

//            System.out.println("Currently looking at node: (" + currentNode.getX() + ", " + currentNode.getY() + ")");

            // Add current node to explored set
            this.exploredSet.put(currentNode, currentNode.getParent());

            // Check for goal state
            if (currentNode.isGoal()) {
                System.out.println("Solution found!");
                // Return ArrayList of Nodes that make up the found path.
                return reconstructPath(currentNode);
            }

            // Generate children nodes from current node and add them to frontier if not previously examined.
            ArrayList<PositionNode> adder = currentNode.retrieveAdjacentNodes();
            // Always expand nodes in the order they have been generated.
            adder.sort(Comparator.comparingInt(o -> o.x));

            for (PositionNode child : adder) {
                if (!this.exploredSet.containsKey(child) && !this.frontier.contains(child)) {
                    this.frontier.add(child);
                }
            }

//            System.out.println("Frontier: " + this.frontier);
//            System.out.println("Explored Set: " + this.exploredSet);
//            System.out.println("------------------------------------------------------------");
        }
    }


    public class NodeComparator implements Comparator<PositionNode> {
        // Used to sort nodes into the priority queue.
        public int compare(PositionNode o1, PositionNode o2) {
            if (o1.getHeuristicCost() < o2.getHeuristicCost()) {
                return -1;
            }

            if (o1.getHeuristicCost() > o2.getHeuristicCost()) {
                return 1;
            }
            return 0;
        }
    }
}
