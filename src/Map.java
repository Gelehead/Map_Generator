import java.util.*;
// good to know : height is width and width is height
public class Map{
    ArrayList<Tile> mostRecentCluster = new ArrayList<>();
    private int height, width, nbOfRivers;
    Tile[][] map;

    public Map(int height, int width){
        this.height = height;
        this.width = width;
        this.nbOfRivers = 0;
        this.map = createArray(this.height, this.width);
        init();
    }




// ------------- Tile manipulation ------------ //

public static Tile[] getNeighbours(Map map, Tile tile){
        Tile[] listOfNeighbours = new Tile[4];
        int[] berengena = {0,1,0,-1};
        for (int i = 0; i < 4; i++) { 
            int berengenaX = berengena[(i+3)%4] + tile.getPosx();
            int berengenaY = berengena[i] + tile.getPosy();
            if (((berengenaX <= map.getWidth()-1) && ( berengenaX >= 0)) && ((berengenaY <= (map.getHeight()-1)) && (berengenaY >= 0))){
                listOfNeighbours[i] = map.getMap()[berengenaX][berengenaY];
            }
            else{
                listOfNeighbours[i] = null;
            }
        }
        return listOfNeighbours;
    }

public static Tile[] getAllNeighbours(Map map, Tile tile){
    System.out.println(tile);
    Tile[] listOfNeighbours = new Tile[8];
        for (int i = 0; i < 8; i++) { 
            int[] co = {0,1,1,1,0,-1,-1,-1};
            int x = co[i] + tile.getPosx();
            int y = co[(i+6)%8] + tile.getPosy();
            // Extremely ugly 
            if (((x <= map.getWidth()-1) && ( x >= 0)) && ((y <= (map.getHeight()-1)) && (y >= 0))){

                listOfNeighbours[i] = map.getMap()[tile.getPosx() + co[i]][tile.getPosy() + co[(i+6)%8]];
                System.out.println(tile);
                System.out.println("...");
                System.out.println(listOfNeighbours[i]);
            }
            else{
                listOfNeighbours[i] = null;
                System.out.println("null");
            }
            //System.out.println(berengena[i] + " + " + berengenaX);
            //System.out.println(berengena[(i+6)%8] + " + " + berengenaY);
        }
        /*
        for (Tile tile2 : listOfNeighbours) {
            System.out.println(tile2);
        }
        */
        return listOfNeighbours;
}

    

    //Create cluster in memory
    public void addCluster(){
        ArrayList<Tile> cluster = new ArrayList<>(); // make the add mechanic in Terrain.addCluster() |ToDo|

        Random rand = new Random();

        int clusterX = rand.nextInt(0,width); 
        int clusterY = rand.nextInt(0,height);
        // rangeOfBigness will have {int maxEstrangement, float Dispersion (0-1)}
        float[] rangeOfBigness = {2f, 1f};
        cluster = Terrain.addCluster(this.map, this.width, this.height, clusterX, clusterY, rangeOfBigness);
        this.mostRecentCluster = cluster;
    }

    //deletes the most recently creaeted Cluster of mountains
    public void deleteMostRecentCluster(){
        Terrain.deleteCluster(this.map, this.mostRecentCluster);
    }





// ------------------ Visual part ---------------- //

    // Shows map in terminal
    public void showMap() {
        int adjust = this.width > 9 ? this.width-9 : 0;

        String columnNumbers = "  ";
        for (byte j = 0; j < this.width; j++) { 
            columnNumbers += j + " ";
        }
        System.out.println(columnNumbers);

        for (int y = 0; y < this.height; y++){
            String text = "";
            for (int x = 0; x < this.width; x++) {
                text += " " + this.map[x][y].repr();
            } 
            System.out.println((char) (65+y) + text + "·");
        }
        System.out.println("  " + "· ".repeat(this.width+(adjust/2)) + " ");
    }








// ----------------- Array part ------------------ //

    // Creates the 2 dimensions Tile arrays representing the map in memory
    public static Tile[][] createArray(int height, int width){
        Tile[][] arr = new Tile[width][height];
        for (byte x = 0; x < width; x++){
            for (byte y = 0; y < height; y++){
                arr[x][y] = new Tile(x, y, null, null);
            }
        }
        return arr;
    }

    public void init(){
        for (int height = 0; height < this.width; height++) {
            for (int width = 0; width < this.height ; width++) {
                //really ineficient : must create a TileState object for each tile of the array 
                // ■
                map[height][width].setState(new TileState(" ", null, 1, 0));
            }
        }
    }


// ---------- "AI" generated Terrain ---------- //
    // rivers  - must come from a border of the map and go to another
    // -- can go from a random number from a random border to another random number to a random perpendicular border
    // -- the path generated will change based on the pathfinding algorithm used (choosed randomly from an array of algos)
    // ┤ ╡ ╢ ╖ ╕ ╣ ║ ╗ ╝ ╜╞ ╟ ╛ ┐ └ ┴ ┬ ├ ─ ┼ ╞ ╟ ╚ ╔ ╩ ╦ ╠ ═ ╬ ╧ ╨ ╤ ╥ ╙ ╘ ╒ ╓ ╫ ╪ ┘ ┌ 


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

// getters - setters
    public Tile[][] getMap() { 
        return map; 
    }
    public int getHeight() {
        return height;
    }
    public ArrayList<Tile> getMostRecentCluster() {
        return mostRecentCluster;
    }
    public int getNbOfRivers() {
        return nbOfRivers;
    }
    public int getWidth() {
        return width;
    }

}
/* --------------------------------------------- */





/*    EXEMPLES DE MAP

Que faire dans ce cas la 
(modifier le spawn d ennemis pourqu ils soient accessibles par le joueur)
  0 1 2 3 4 5 6 7 8 9 
A   ^                ·
B ^ ^                ·
C ^ ^                ·
D                    ·
E                    ·
  · · · · · · · · · ·

---------------------------------------
|     Bonne map pour un niveau

  0 1 2 3 4 5 6 7 8 9 
A               ^ ^  ·
B                    ·
C             ^   ^  ·
D           ^ ^      ·
E         ^ ^     ^  ·
  · · · · · · · · · ·
*/
