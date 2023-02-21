// this is supposed to concentrate all the instructions to make the game run
public class Game {
    
    public static void run(){
        byte mapHeight = 5;
        byte mapWidth = 20;

        Map newMap = new Map(mapHeight, mapWidth);
        newMap.addCluster();
        newMap.assignNeighbours();
        newMap.showMap();
        Tile[][] map = newMap.getMap();
        System.out.println(map[15][2].getLeftTile().toString());
    }

    public static void pathTest(){

    }


}
