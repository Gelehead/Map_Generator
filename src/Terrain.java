public class Terrain{
    // Still to be defined but there surely will be rivers and mountains
    boolean seeThrough;
    String mapRepr;

    public Terrain(boolean seeThrough, String mapRepr){
        this.seeThrough = seeThrough;
        this. mapRepr = mapRepr;
    }

    @Override
    public String toString(){
        return this.mapRepr;
    }
}