import java.util.Random;

import enums.tileStatus;

import java.util.ArrayList;

public class Terrain{
    // Still to be defined but there surely will be rivers and mountains
    boolean seeThrough;
    String mapRepr;

    public Terrain(boolean seeThrough, String mapRepr){
        this.seeThrough = seeThrough;
        this. mapRepr = mapRepr;
    }

    //Add rivers
    public void addRiver(int lengthOfRiver){
        // Random rand = new Random();
        // int[] start = {rand.nextInt(1,this.height), rand.nextInt(1,this.height)};
        // ---To Do--- pathfinding algo (Dijkstra / A*(note: shortest path not necessary))
        // to make the river go from a part of an edge to another  
    }


    // Add terrain
    public static void addTerrain(Tile[][] map, int height, int width, int nbOfClusters, float[] rangeOfBigness){
        Random rand = new Random();

        for (int i = 0; i<nbOfClusters; i++){
            //choose random coordinates
            addCluster(map, width, height, rand.nextInt(0,height), 
               rand.nextInt(0,width), rangeOfBigness);
        }
    }

    // generates "terrain"("^") randomly in a defined area 
    public static ArrayList<Tile> addCluster(Tile[][] map, int width, int height, int xCoordinate, int yCoordinate, float[] rangeOfBigness){
        ArrayList<Tile> recentCluster = new ArrayList<>();

        Random random = new Random();

        float threshold = 1 - rangeOfBigness[1];
        int size = (int) rangeOfBigness[0];
        int i = 0;

        while (i < (2*size + 1)* (2*size + 1)) {
            float proba = random.nextFloat(); 
            int x = random.nextInt(-(size+1),size + 1); // +1 is used bc in nextInt, the upperbound is excluded
            int y = random.nextInt(-(size+1),size + 1);
            // | ToDo | Rework the threshold system
            if (proba > threshold) {
                //System.out.println("x : " + x + "   y : " + y + "      size : " + size);
                threshold += (proba - threshold) * (0.25 * Math.sqrt(x*x+y*y));
                int xCo = ((xCoordinate + x) >= 0 && (xCoordinate + x) < width-1) ? xCoordinate + x : 360;
                int yCo = ((yCoordinate + y) >= 0 && (yCoordinate + y) < height) ? yCoordinate + y : 360;
                addTerrain(map, xCo, yCo, "^");
                if ((xCo != 360) && (yCo != 360)){
                    recentCluster.add(map[xCo][yCo]);
                }
            }
            if ((x != 0 ) && (y != 0)){ i++; } // increment counter if mountain placed was not on (xCoordinates,yCoordinates)
        }
        return recentCluster;
    }

    public static void addTerrain(Tile[][] map, int xCoordinate, int yCoordinate, String mapRepr){
        if ((xCoordinate != 360) && (yCoordinate != 360)){
            TileState mountain = new TileState(mapRepr, tileStatus.OBSTACLE, 999, -1);
            //Terrain mountain = new Terrain(false, mapRepr);
            map[xCoordinate][yCoordinate].setNextState(mountain);
            map[xCoordinate][yCoordinate].setState(mountain);
        }
        //else {System.out.println("Out Of Bounds");} // will be "OOB" from now
    }

    public static void deleteCluster(Tile[][] map, ArrayList<Tile> recentCluster){
        int i = 0;
        while ( i < recentCluster.size() ) {
            Tile currentTile = map[recentCluster.get(i).getPosx()][recentCluster.get(i).getPosy()];
            currentTile.setState(null); currentTile.setNextState(null);
            i++ ;
        }
    }

    @Override
    public String toString(){
        return this.mapRepr;
    }

}