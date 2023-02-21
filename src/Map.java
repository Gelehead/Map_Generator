import java.util.*;
public class Map{
    ArrayList<Tile> mostRecentCluster = new ArrayList<>();
    int height, width, nbOfRivers;
    Tile[][] map;

    public Map(int height, int width){
        this.height = height;
        this.width = width;
        this.nbOfRivers = 0;
        this.map = introMap((byte) 1, this.height, this.width);
    }




// ------------- Tile manipulation ------------ //

    // FIND A BETTER WAY TO DISTRIBUTE FUNCTIONS
    //  getNeighbours being static isn t a good idea
    public static Tile[] getNeighbours(Tile[][] map, Tile tile){
        Tile[] listOfNeighbours = new Tile[4];
        int[] neededValuesx = {0,-1,0,1};
        int[] neededValuesy = {-1,0,1,0};
        for (int i = 0; i < 4; i++) { 
            listOfNeighbours[(i)] = map[tile.getPosx()+neededValuesx[i]][tile.getPosy()+neededValuesy[i]];
        }
        return listOfNeighbours;
    }



    public void assignNeighbours(){
        for (int i = 0; i < this.height-1; i++) {
            for (int j = 0; j < this.width-1; j++) {
                Tile currentTile = map[j][i];
                // hauteur i
                currentTile.setTopTile(i == 0 ? null : map[j][i-1]);
                currentTile.setBotTile(i == height ? null : map[j][i+1]);
                // note : topTile is the tile with y index -1 because y increments the "lower" we go

                // largeur j 
                currentTile.setLeftTile(j == 0 ? null : map[j-1][i]);
                currentTile.setRightTile(j == width ? null : map[j+1][i]);
            }
        }
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

        // ---- put this as a comm to make code work
        // replace this with try catch or exceptions or idk how it works
        // like try catch testpath(... , ...).getExists() != true
        System.out.println(clusterY/2);
        System.out.println(map[15][0].toString());
        Tile testStart = map[0][clusterY/2];
        Tile testEnd = map[width-1][clusterY/2];
        while (testPath(testStart,testEnd).getExists() == false){
            int randomX = rand.nextInt(0,width+1); 
            int randomY = rand.nextInt(0,height+1);
            Terrain.deleteCluster(map, mostRecentCluster);
            Terrain.addCluster(this.map, this.width, this.height, randomX, randomY, rangeOfBigness);
        }

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

    public Tile[][] introMap(byte difficulty, int height, int width){
        return createArray(height, width);
        //do smth with dificulty
    }
    public Tile[][] introMap(byte difficulty){
        return createArray(5, 10);
        //do smth with dificulty
    }

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





// ----------- Pathfinding Bridge ------------- \\

    public Path testPath(Tile start, Tile end){
        // return Pathfinding.findPath(map, start, end)
        return Path.toPath(Pathfinding.findPath(this.map, this.map[4][4],this.map[15][4]));
    }

    public double calcgScore(Tile node, Tile endNode){
        // g(n)=g(n.parent)+cost(n.parent,n)
        return Pathfinding.calcgScore(node, endNode);
    }

    public double calchScore(Tile node, Tile endNode){
        //h(n)=c⋅max(∣n.x−goal.x∣,∣n.y−goal.y∣)
        // actually went for the manhattan distance
        return Pathfinding.calchScore(node, endNode);
    }

    public Tile getBestNeighbour(Tile tile){
        
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
    public Tile[][] getMap() { return map; }

}
/* --------------------------------------------- */





/*    EXEMPLES DE MAP

Que faire dans ce cas la 
(modifier le spawn d ennemis pourqu ils soient accessibles par le joueur)
  0 1 2 3 4 5 6 7 8 9 
A   ^
B ^ ^
C ^ ^
D
E
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
