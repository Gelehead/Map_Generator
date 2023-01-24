import java.util.Random;
import java.util.random.*;
// Oscar Lavolet - 20242868


// Clarifications / To - do - List
// Turn based strategy game bsaed on "Into the Breach". Will take place in the Terminal
// Ennemies
// - Idk, maybe do a first attempt to pseudo AI with punishment/reward system
// - Different types, different goals, different patterns

// utiliser Locale.setDefault(Locale.CANADA); dans le main


public class Main {
    public static void main(String[] args){
        byte mapHeight = 5;
        byte mapWidth = 10;

        Map newMap = new Map(mapHeight, mapWidth);

    }

// --------------- Map Part ------------------ //

    public static class Map{
        byte height;
        byte width;
        byte nbOfRivers;
        Tile[][] map;
        public Map(byte height, byte width){
            this.height = height;
            this.width = width;
            this.nbOfRivers = 0;
            this.map = introMap((byte) 1, this.height, this.width);
        }

    // first of 2 parts of overloading to introduce default settings to map sizing
    // --Note-- Might be out of date with this version of the code
        public static Tile[][] introMap(byte difficulty, byte height, byte width){
            return trueMap(difficulty, height, width);
        }
    // second part
        public static Tile[][] introMap(byte difficulty){
            return trueMap(difficulty, (byte) 5, (byte) 10);
        }


        // --To Do-- maybe replace int height, width by byte
        // --- Done, is it optimised tho?
        public static Tile[][] trueMap(byte difficulty, byte height, byte width){
            makeMap(height, width);

            return createArray((byte) height, (byte) width);
        }
    
        //Create map in Terminal
        public static void makeMap(byte height, byte width){
            int adjust = width > 9 ? width-9 : 0;

            String oneToWidth = "  ";
            for (byte j = 1; j < width+1; j++) { 
                oneToWidth += j + " ";
            }
            System.out.println(oneToWidth);

            for (int i = 0; i<height; i++){
                System.out.println((char) (65+i) + " ".repeat((2*width)+adjust) + "·");
            }
            System.out.println("  " + "· ".repeat(width+(adjust/2)) + " ");
        }


        //Add rivers
        public static void addRiver(int lengthOfRiver){
            Random rand = new Random();
            byte yOfRiver = rand.nextInt(1,this.height);
            // ---To Do--- pathfinding algo (Djovktra / A*(note: shortest path not necessary))
            // to make the river go from a part of an edge to another  
        }


        // Add terrain
        public static void addTerrain(int nbOfClusters, int rangeOfBigness){
            Random random = new Random();

            for (int i = 0; i<nbOfClusters; i++){
                //choose random coordinates
                byte y = random.nextInt(0,this.height);
                byte x = random.nextInt(0,this.width);

            }

        }

        // ---------- Array part ------------ (map)

        public static Tile[][] createArray(byte height, byte width){
            Tile[][] arr = new Tile[height][width];
            for (byte x = 0; x < height; x++){
                for (byte y = 0; y < width; y++){
                    arr[x][y] = new Tile(x, y, null, null);
                }
            }
            return arr;
        }

        public static class Tile {
            byte posx; 
            byte posy;
            // state is supposed to contain an ennemy / terrain / traps / nothing(null)
            Object state;
            Object nextState;

            public Tile(byte posx , byte posy, Object state, Object nextState){
                this.posx = posx;
                this.posy = posy;
                this.state = state;
                this.nextState = nextState;
            }
        }


    // --------------- Utilities ------------------ //
        //Need to do Fisher-Yates algo smwhere


    // ---------- "AI" generated Terrain ---------- //
        // rivers  - must come from a border of the map and go to another


        // mountains - come in a cluster of 3 or more, each adjacet with 2 or more

    }
/* --------------------------------------------- */
}

