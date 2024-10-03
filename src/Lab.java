import java.util.Random;
import enums.tileStatus;

public class Lab {

    public static void makePath(Map map, Tile start, Tile end){
        end.getState().setStatus(tileStatus.ENDNODE);
        random_bfs(map, start, end);
        start.getState().setNoiseVal(0);
        end.getState().setNoiseVal(0);
    }

    public static Tile[] Dijkstra(Map map, Tile start, Tile end){
        Tile[] placeholder = {new Tile(0, 0, null, null)};
        return placeholder;
    }
    

        // Here we give coordinates for the start and end tile, 
        // would it be better to give the starting and end tile right away? 
    public static Tile[] random_bfs(Map map, Tile start, Tile end){
        Tile[] queue = {start};
        return rand_rec_bfs(map, queue, end);
    }

    /*gets a random tile within the queue
    gets its neighbours
    puts them into a queue
    calls rand_rec_bfs on the new queue
    */
    public static Tile[] rand_rec_bfs(Map map, Tile[] queue, Tile endTile){
        Random rand = new Random();
        // safety net to not have a null chosen tile
        int randNeighbour = rand.nextInt(queue.length);
        while (queue[randNeighbour] == null){
            randNeighbour = (randNeighbour + 1) % queue.length;
        }
        Tile[] chosenTile = {queue[randNeighbour]};
        chosenTile[0].getState().cycleMazeRepr();
        chosenTile[0].getState().setNoiseVal(Integer.parseInt(chosenTile[0].getState().getRepr()));
        Tile[] neighbours = Map.getNeighbours(map, chosenTile[0]);
        if ((Utils.contains(neighbours,endTile)) || (Utils.contains(neighbours, null))){ return null; }
        Tile[] result = Utils.concatenate(chosenTile, rand_rec_bfs(map, neighbours, endTile));
        return result ;
    }

    // TODO
    public static Tile[] AStar(Map map, Tile start, Tile tile, Tile end){
        System.out.println("leTile = " + tile);
        Tile[] neighbours = Map.getAllNeighbours(map, start);
        if (Utils.contains(neighbours, end)){ return null;}
        return Utils.concatenate(start, AStar(map, start, Lab.getBestTile(map, start, neighbours, end), end));
    }

    public static Tile getBestTile(Map map, Tile start, Tile[] neighbours, Tile end){
        Tile bestTile = neighbours[0];
        for (Tile tile : neighbours) {
            bestTile = Utils.EuclDist(bestTile, end) + Utils.EuclDist(start, bestTile) + bestTile.getState().getTentDist() > 
            Utils.EuclDist(tile, end) + Utils.EuclDist(start, tile) + tile.getState().getTentDist()
            ? tile : bestTile;    
        }
        bestTile.getState().setRepr("■");
        map.showMap();
        System.out.println(bestTile);
        return bestTile;
    }

    public static void showPath(Tile[] path){
        for (Tile tile : path) {tile.getState().setRepr("■");}
    }
    
    public static void makeCluster(Map map, String repr, int threshold){
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getMap()[i][j].getState().getNoiseVal() > threshold){
                    map.getMap()[i][j].getState().setRepr(repr);
                    map.getMap()[i][j].getState().setStatus(tileStatus.OBSTACLE);
                    map.getMap()[i][j].getState().setTentDist(Integer.MAX_VALUE);
                }
                else{
                    map.getMap()[i][j].getState().setRepr(" ");
                }
            }
            
        }
    }
}
