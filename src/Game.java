import java.util.Random;

// this is supposed to concentrate all the instructions to make the game run
public class Game {
    public static void run(){
        byte mapHeight = 10;
        byte mapWidth = 51;

        Map map = new Map(mapHeight, mapWidth);
        //map.showMap();       

        //map.showMap();

        Random rand = new Random();
        int[] start = {rand.nextInt(mapWidth),rand.nextInt(mapHeight
            )};
        int[] end = {rand.nextInt(mapWidth),rand.nextInt(mapHeight)};   
        System.out.println(start[0] + " " + start[1]);
        System.out.println(end[0] + " " + end[1]);

        Tile leStart = map.getMap()[start[0]][start[1]];
        Tile leEnd = map.getMap()[end[0]][end[1]];
        Lab.makePath(map, leStart, leEnd);
        map.showMap();

        Lab.makeCluster(map, "^", 6);
        map.showMap();


        /* Saerch algorithm (WIP), make Astar work

        leStart.getState().setRepr("▓");
        leEnd.getState().setRepr("░");
        Lab.showPath(Lab.AStar(idklolbcimacommentary));
        leStart.getState().setRepr("▓");
        leEnd.getState().setRepr("░");
        map.showMap();

        */
        
    }
}
