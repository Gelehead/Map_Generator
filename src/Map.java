import java.util.*;
public class Map{
    int height;
    int width;
    int nbOfRivers;
    Tile[][] map;
    public Map(int height, int width){
        this.height = height;
        this.width = width;
        this.nbOfRivers = 0;
        this.map = introMap((byte) 1, this.height, this.width);
    }

        // first of 2 parts of overloading to introduce default settings to map sizing
        // --Note-- Might be out of date with this version of the code
    public Tile[][] introMap(byte difficulty, int height, int width){
        return trueMap(difficulty, height, width);
    }
        // second part
    public Tile[][] introMap(byte difficulty){
        return trueMap(difficulty, 5, 10);
    }

    public Tile[][] trueMap(byte difficulty, int height, int width){
        return createArray(height, width);
    }

    //Create map in Terminal
    public void makeMap(){
        Random rand = new Random();

        int clusterX = rand.nextInt(1,width-1); 
        int clusterY = rand.nextInt(1,height);
        // rangeOfBigness will have {int maxEstrangement, float Dispersion (0-1)}
        float[] rangeOfBigness = {1f, 0.7f};
        addCluster(clusterX, clusterY, rangeOfBigness);

    // -------------------------------------------------------------------------- //
        int adjust = this.width > 9 ? this.width-9 : 0;

        String columnNumbers = "  ";
        for (byte j = 0; j < this.width; j++) { 
            columnNumbers += j + " ";
        }
        System.out.println(columnNumbers);

        for (int y = 0; y < this.height; y++){
            String text = "";
            for (int x = 1; x < this.width; x++) {
                text += " " + this.map[x][y].repr();
            } 
            System.out.println((char) (65+y) + text);
        }
        System.out.println("  " + "· ".repeat(this.width+(adjust/2)) + " ");
    }


    //Add rivers
    public void addRiver(int lengthOfRiver){
        // Random rand = new Random();
        // int[] start = {rand.nextInt(1,this.height), rand.nextInt(1,this.height)};
        // ---To Do--- pathfinding algo (Djovktra / A*(note: shortest path not necessary))
        // to make the river go from a part of an edge to another  
    }


    // Add terrain
    public void addTerrain(int nbOfClusters, float[] rangeOfBigness){
        Random rand = new Random();

        for (int i = 0; i<nbOfClusters; i++){
            //choose random coordinates
            addCluster(rand.nextInt(0,this.height), 
               rand.nextInt(0,this.width), rangeOfBigness);
        }
    }

    // generates "terrain"("^") randomly in a defined area 
    public void addCluster(int xCoordinate, int yCoordinate, float[] rangeOfBigness){
        addTerrain(xCoordinate, yCoordinate, "^");
        Random random = new Random();

        float threshold = 1 - rangeOfBigness[1];
        int size = (int) rangeOfBigness[0];
        int i = 0;

        while (i < (2*size + 1)* (2*size + 1)) {
            float proba = random.nextFloat(); 
            int x = random.nextInt(-size,size + 1); // +1 is used bc in nextInt, the upperbound is excluded
            int y = random.nextInt(-size,size + 1);
            // | ToDo | Rework the threshold system
            if (proba > threshold) {
                System.out.println("x : " + x + "   y : " + y + "      size : " + size);
                threshold += (proba - threshold) * (0.25 * Math.sqrt(x*x+y*y));
                int xCo = ((xCoordinate + x) >= 0 && (xCoordinate + x) < this.width-1) ? xCoordinate + x : 360;
                int yCo = ((yCoordinate + y) >= 0 && (yCoordinate + y) < this.height) ? yCoordinate + y : 360;
                addTerrain(xCo, yCo, "^");
            }
            if ((x != 0 ) && (y != 0)){ i++; } // increment counter if mountain placed was not on (xCoordinates,yCoordinates)
        }
    }

    public void addTerrain(int xCoordinate, int yCoordinate, String mapRepr){
        if ((xCoordinate != 360) && (yCoordinate != 360)){
            Terrain mountain = new Terrain(false, mapRepr);
            this.map[xCoordinate][yCoordinate].setNextState(mountain);
            this.map[xCoordinate][yCoordinate].setState(mountain);
        }
        else {System.out.println("Out Of Bounds");} // will be "OOB" from now
    }

    // ---------- Array part ------------ (map)

    // Creates the 2 dimensions Tile arrays representing the map
    public static Tile[][] createArray(int height, int width){
        Tile[][] arr = new Tile[width][height];
        for (byte x = 0; x < width; x++){
            for (byte y = 0; y < height; y++){
                arr[x][y] = new Tile(x, y, null, null);
            }
        }
        return arr;
    }

// --------------- Utilities ------------------ //
    //Need to do Fisher-Yates algo smwhere

    // Pathfinding 
    public static int[] findPath(int[] start, int[] end) {
        int[] a = {1,2};
        return a;
    }


// ---------- "AI" generated Terrain ---------- //
    // rivers  - must come from a border of the map and go to another


    // mountains - come in a cluster of 3 or more, each adjacet with 2 or more


    // ToString Override
    @Override
    public String toString() {
        String text = "";
        for (int y = 0; y < this.height ; y++){
            for (int x = 0; x < this.width ; x++){
                text += this.map[x][y].toString() + ", ";
            }
            text += "\n";
        }
        return text;
    }

}
/* --------------------------------------------- */


