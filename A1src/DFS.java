import java.util.ArrayList;
import java.util.LinkedList;

public class DFS extends Algorithm {
    // Last-in-first-out queue.
//    private Stack<PositionNode> frontier;
    private LinkedList<PositionNode> frontier;
//    private ArrayList<Stack<PositionNode>> paths =
//    new ArrayList<>();

    DFS(PositionNode start, char goalState, char[][] map) {
        // Initialise variables
        super(start, goalState, map);
//        this.frontier = new Stack<>();
        this.frontier = new LinkedList<>();
        this.frontier.add(this.start);

        // Conduct search and obtain performance
        this.doSearch();
    }


//    private Stack<PositionNode> deepCopy(Stack<PositionNode> explored) {
//        Stack<PositionNode> copy = new Stack<>();
//        for (int i = 0; i < explored.size(); i++) {
//            copy.push(explored.pop());
//        }
//        Collections.reverse(copy);
//        return copy;
//    }
//
//
//    private void generateAllPaths(PositionNode currentNode, Stack<PositionNode> explored) {
//        if (explored.peek().isGoal() && explored.size()!=0) {
//            paths.add(explored);
//            return;
//        }
//        if (currentNode == null) currentNode = start;
//        explored = deepCopy(explored);
//        explored.push(currentNode);
//        ArrayList<PositionNode> adjacentNodes = currentNode.retrieveAdjacentNodes();
//        for (PositionNode adjacentNode : adjacentNodes) if (!explored.contains(adjacentNode))
//            generateAllPaths(currentNode, explored);
//    }

//    @Override
//    public ArrayList<PositionNode> searchPath() {
//        generateAllPaths(null, new Stack<>());
//        Stack<PositionNode> shortestPath = paths.get(0);
//        for (Stack path : paths) if (path != null && shortestPath.size() < path.size()) shortestPath = path;
//        return new ArrayList<>(shortestPath);
//    }


    @Override
    public ArrayList<PositionNode> searchPath() {
        // Depth-first search implementation.
        PositionNode currentNode;
        while (true) {
            currentNode = this.frontier.pop();
//            currentNode = this.frontier.pollFirst();
            System.out.println("Currently looking at node: (" + currentNode.getX() + ", " + currentNode.getY() + ")");

            // Add current node to explored set
            this.exploredSet.put(currentNode, currentNode.getParent());

            // Check for goal state
            if (currentNode.isGoal()) {
                System.out.println("Solution found!");
                // Return ArrayList of Nodes that make up the found path.
                return reconstructPath(currentNode);
            }

            // Generate children nodes from current node and add them to frontier if not previously examined.
            for (PositionNode adjacentNode: currentNode.retrieveAdjacentNodes()) {
                if(!this.exploredSet.containsKey(adjacentNode) && !this.frontier.contains(adjacentNode)) {
                    this.frontier.push(adjacentNode);
//                    this.frontier.addLast(adjacentNode);
                }

            }

            // If the frontier is empty after expanding the current node, no solution has been found
            if (this.frontier.isEmpty()) {
                System.out.println("No solution found!");
                return new ArrayList<>();
            }

            System.out.println("Adjacent: " + this.frontier);
            System.out.println("Explored Set: " + this.exploredSet);
            System.out.println("------------------------------------------------------------");
        }
    }
}
