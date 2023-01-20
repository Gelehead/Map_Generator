// Oscar Lavolet - 20242868


// Clarifications / To - do - List
// Turn based strategy game bsaed on "Into the Breach". Will take place in the Terminal
// Ennemies
// - Idk, maybe do a first attempt to pseudo AI with punishment/reward system
// - Different types, different goals, different patterns

// utiliser Locale.setDefault(Locale.CANADA); dans le main


public class Strat {
    public static class game{
        Tile[][] map;
        public game(){

        }

        public static void run(){
            int doSmthPls;
        }

        public static class level{
            int levelNum;
            public level(int levelNum){
                this.levelNum = levelNum;
            }
        }


// --------------- Map Part ------------------ //

    // first of 2 parts of overloading to introduce default settings to map sizing
        public static void introMap(byte difficulty, byte height, byte width){
            trueMap(difficulty, height, width);
        }
    // second part
        public static void introMap(byte difficulty){
            trueMap(difficulty, (byte) 5, (byte) 10);
        }


        // --To Do-- maybe replace int height, width by byte
        // --- Done, is it optimised tho?
        public static void trueMap(byte difficulty, byte height, byte width){
            game.makeMap(height, width);

            Tile[][] mpa = game.createArray((byte) height, (byte) width);

            
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
            // state is supposed to contain an ennemy, terrain, traps, nothing(null)
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


        // mountains - come in a clusterof 3 or more, each adjacet with 2 or more


/* --------------------------------------------- */
    }

    public static void main(String[] args){
        //game.run();
        game.introMap((byte) 1);
    }
}
