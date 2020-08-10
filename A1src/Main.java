import java.util.NoSuchElementException;

public class Main {
    private PositionNode start;
    private char goalState;
    private char[][] map;

    private Main(char[][] grid, char startState, char goalState) {
        this.map = grid;
        PositionNode.setMap(this.map);
        int[] startCoords = findUnitPosition(startState, this.map);
        this.start = new PositionNode(startCoords[0], startCoords[1], null, findUnitPosition(goalState, this.map));
        System.out.println("Found start position at: " + this.start);
        this.goalState = goalState;
    }
    private char[][] getMap() {
        return this.map;
    }

    private char getGoalState() {
        return this.goalState;
    }


    private static int[] findUnitPosition(char uniqueState, char[][] map) throws NoSuchElementException {
        // Search through map to find e.g. robot start position and goal positions

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (uniqueState == map[x][y]) {
                    return new int[]{x, y};
                }
            }
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {

        try {
            String algo = args[0];
            int parallels = Integer.parseInt(args[1]);

            String[] startPos = args[2].split(",");
            int startPosx = Integer.parseInt(startPos[0]);
            int startPosy = Integer.parseInt(startPos[1]);

            String[] goalPos = args[3].split(",");
            int goalPosx = Integer.parseInt(goalPos[0]);
            int goalPosy = Integer.parseInt(goalPos[1]);

            Map map = new Map();
            char[][] grid = map.createMap(parallels - 1, startPosx - 1, startPosy, goalPosx - 1, goalPosy);

            Main pathfinder1 = new Main(grid, 's', 'g');

            for (int i = 0; i < parallels - 1; i++) {
                for (int j = 0; j < 8; j++) System.out.print(grid[i][j]);
                System.out.println();
            }

            switch (algo) {
                case "DFS":
                    new DFS(pathfinder1.start, pathfinder1.getGoalState(), pathfinder1.getMap());
                    break;
                case "BFS":
                    new BFS(pathfinder1.start, pathfinder1.getGoalState(), pathfinder1.getMap());
                    break;
                case "BestF":
                    new BestF(pathfinder1.start, pathfinder1.getGoalState(), pathfinder1.getMap());

                    break;
                case "AStar":
                    new AStar(pathfinder1.start, pathfinder1.getGoalState(), pathfinder1.getMap());
                    break;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("usage: Algorithm | Parallels | Start | Goal");
        }


    }



}
