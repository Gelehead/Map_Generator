import java.util.Random;
import enums.tileStatus;

public class SearchAlg 
{
    public interface Algorythm 
    {
        public Tile[] givePath(Map map, int[] start, int[] end);
    }

    public class Random_bfs implements Algorythm 
    {
        // Here we give coordinates for the start and end tile, 
        // would it be better to give the starting and end tile right away? 
        public Tile[] givePath(Map map, int[] start, int[] end) 
        {
            Tile[] queue = {map.getMap()[start[0]][start[1]]};
            map.getMap()[end[0]][end[1]].getState().setStatus(tileStatus.ENDNODE);
            return rand_rec_bfs(map, queue, map.getMap()[end[0]][end[1]]);
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
                // graphical representation / makes tile appear
            Tile[] chosenTile = {queue[randNeighbour]};
            chosenTile[0].getState().cycleMazeRepr();
            if (Utils.contains(Map.getNeighbours(map, chosenTile[0]),endTile)){ return null; }
            Tile[] neighbours = Map.getNeighbours(map, chosenTile[0]);
            Tile[] result = Utils.concatenate(chosenTile, rand_rec_bfs(map, neighbours, endTile));

            return result ;
    }
    }

    public static void init(Map map){
        for (int height = 0; height < map.getWidth(); height++) {
            for (int width = 0; width < map.getHeight(); width++) {
                //really ineficient : must create a TileState object for each tile of the array 
                // ■
                TileState state = new TileState(" ", null, 1, 0);
                map.getMap()[height][width].setState(state);
            }
        }
    }

    public static Tile[] getPath(Algorythm alg, Map map, int[] start, int[] end) 
    {
        return alg.givePath(map, start, end);
    }
}
