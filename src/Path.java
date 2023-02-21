import java.util.ArrayList;


public class Path {
    private Tile[] path;
    private ArrayList<Tile> listPath;
    private boolean exists;

    // Path can exist as an array or a list
    public Path( Tile[] path, boolean exists ){
        this.path = path;
        this.exists = exists;
    }
    public Path(){}
    public Path( ArrayList<Tile> path, boolean exists ){
        this.listPath = path;
        this.exists = exists;
    }

    public void addToPath(Tile tile){
        this.listPath.add(0, tile);
    }

    public static Path toPath(ArrayList<Tile> list){
        Path path = new Path(list, true);
        return path;
    }


    public ArrayList<Tile> getListPath() {return listPath;}
    public Tile[] getPath() {return path;}
    public boolean getExists(){ return this.exists; }
}

