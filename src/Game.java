// this is supposed to concentrate all the instructions to make the game run
public class Game {
    
    public static void run(){
        byte mapHeight = 5;
        byte mapWidth = 20;

        Map newMap = new Map(mapHeight, mapWidth);
        newMap.addCluster();
        //newMap.assignNeighbours();
        newMap.showMap();
        newMap.deleteMostRecentCluster();
        newMap.showMap();
    }

    public static void pathTest(){

    }


}
