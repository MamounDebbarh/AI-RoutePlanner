import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class BFS extends Algorithm {
    // First-in-first-out queue.
    private LinkedList<PositionNode> frontier;

    BFS(PositionNode start, char goalState, char[][] map) {
        // Initialise variables
        super(start, goalState, map);
        this.frontier = new LinkedList<>();
        this.frontier.add(this.start);

        // Conduct search and obtain performance
        this.doSearch();
    }


    @Override
    public ArrayList<PositionNode> searchPath() {
        // Depth-first search implementation.
        PositionNode currentNode;
        while (true) {

//            System.out.println("Frontier: " + this.frontier);
            currentNode = this.frontier.pollFirst();
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
//            System.out.println(" Adding frontier : " + adder);
            for (PositionNode child: adder) {
                if(!this.exploredSet.containsKey(child) && !this.frontier.contains(child)) {
                    // Add nodes with priority to those who are on lower parallel
                    this.frontier.addLast(child);
                }
            }

            // If the frontier is empty after expanding the current node, no solution has been found
            if (this.frontier.isEmpty()) {
                System.out.println("No solution found!");
                return new ArrayList<>();
            }

//            System.out.println("new Frontier: " + this.frontier);
//            System.out.println("Explored Set: " + this.exploredSet);
//            System.out.println("------------------------------------------------------------");
        }
    }
}
