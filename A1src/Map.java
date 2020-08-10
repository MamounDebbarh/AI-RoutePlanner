class Map {

    Map() {
    }

    /**
     * This Class creates the grid map representation.
     * we will use here a char 2D array [x][y].
     * [x] represents the parallels and
     * [y] represents the angle.
     */

    char[][] createMap(int parallels, int startPosx, int startPosy, int goalPosx, int goalPosy){

        // create map
        int angle = 8;
        char[][] map = new char[parallels][angle];
        int iStartY = startPosy/45;
        int iGoalY = goalPosy/45;

        // create start and goal
        map[startPosx][iStartY] = 's';
        map[goalPosx][iGoalY] = 'g';
//        map[1][7] = 'X';

        return map;
    }




}
