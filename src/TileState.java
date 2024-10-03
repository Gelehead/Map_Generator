import enums.*;
import java.util.*;

public class TileState {


    private String repr;
    private tileStatus status;
    private int tentDist;

    // let -1 = unexploitable value
    private int noiseVal;

    //"■", "◙", "♦", "o", "δ", "¢", "÷", "-", "·" 
    //"■", "o", "-", "·" 
    private LinkedList<String> mazeRepr = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));

    //missing practical algorithmical usage, will probably add later when needed
    // will probably be startingTile / goalTile / obstacle / fruits
    // ideally we would like to design an algorythm to go through all "fruits" with outmost efficiency
    public TileState(String repr, tileStatus status, int tentDist, int noiseVal){
        this.repr= repr;
        this.status = status;
        this.tentDist = tentDist;
        this.noiseVal = noiseVal;
    }

    public boolean isObstacle(){
        return (this.status == tileStatus.OBSTACLE);
    }

    //returns 
    public void cycleMazeRepr(){
        if (this.repr == " ") {this.repr = mazeRepr.get(0); }
        else {this.repr = mazeRepr.get((mazeRepr.indexOf(this.repr)+1)%mazeRepr.size());}
    }

    @Override
    public String toString(){
        return this.repr;
    }


    public String getRepr() {
        return repr;
    }
    public tileStatus getStatus() {
        return status;
    }
    public int getTentDist() {
        return tentDist;
    }
    public int getNoiseVal() {
        return noiseVal;
    }

    public void setRepr(String repr) {
        this.repr = repr;
    }
    public void setStatus(tileStatus status) {
        this.status = status;
    }
    public void setTentDist(int tentDist) {
        this.tentDist = tentDist;
    }
    public void setNoiseVal(int noiseVal) {
        this.noiseVal = noiseVal;
    }
}
