import java.util.ArrayList;

// Could be extended to be "Pathfinding" bc we actually explore instead of finding a precise path at first
public class Pathfinding {
    int[] source, end;
    ArrayList<Tile> closedTiles = new ArrayList<>();
    ArrayList<Tile> openTiles = new ArrayList<>();
    Tile[][] table;

    
    static ArrayList<Tile> findPath(Tile[][] table ,Tile source, Tile end){
        ArrayList<Tile> openNodes = new ArrayList<>();
        if (source.equals(end)) {
            openNodes.add(source);
            return openNodes;
        }
        explore(table, source, end, source);
        // missing some things
        return openNodes;
    }

    public static void explore(Tile[][] table, Tile tile, Tile endTile, Tile source){
        // array would be [bot,top,left,right]
        double[] nodefScores = new double[4];
        nodefScores[0] = 
            (calchScore(tile.getBotTile(), endTile) + 
            calcgScore(tile, source));
    }




// ---------------- calculation of scores ----------------- //
    public static double calchScore(Tile tile, Tile endTile){
        // Manhattan distance from tile to objective
        //    --> h(n)=∣n.x−goal.x∣+∣n.y−goal.y∣
        return ((Utils.abs((tile.getPosx()-endTile.getPosx())+(Utils.abs(tile.getPosy()-endTile.getPosy())))));
    }

    public static double calcgScore(Tile tile, Tile source){
        // manhattan distance from source to tile (easier in this setup that classic gscore formula )
        // g(n)=g(n.parent)+cost(n.parent,n)
        return ((Utils.abs((source.getPosx()-tile.getPosx())+(Utils.abs(source.getPosy()-tile.getPosy())))));
    }

}