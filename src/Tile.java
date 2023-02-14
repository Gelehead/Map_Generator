// Tile[xCoordinate, yCoordinate, currentState, futureState]
public class Tile {
    private int posx; 
    private int posy;
    // state is supposed to contain an ennemy / terrain / traps / nothing(null)(which means that it is walkable)
    Object state, nextState;

    // neigbours ( currently being reworked )
    private Tile topTile, botTile, rightTile, leftTile;

    public Tile(int posx , int posy, Object state, Object nextState){
        this.posx = posx;
        this.posy = posy;
        this.state = state;
        this.nextState = nextState;
    }

    public void setState(Object newState){ this.state = newState; }
    public void setNextState(Object newNextState){ this.nextState = newNextState; };

    //ajouter .toString() a this.state et this.nextState
    @Override
    public String toString() {
        return "[" + this.posx + ", " + (char) (65+this.posy) + ", " 
        + this.state + ", " + this.nextState + "]" ;
    }

    public String repr(){
        String adjust = this.posx > 9 ? " " : "";
        return this.state != null ? this.state.toString() + adjust : " " + adjust;
    }

    public boolean isObstacle(){
        return this.state != null;
    }

    //setters
    public void setBotTile(Tile botTile) {this.botTile = botTile;}
    public void setLeftTile(Tile leftTile) {this.leftTile = leftTile;}
    public void setTopTile(Tile topTile) {this.topTile = topTile;}
    public void setRightTile(Tile rightTile) {this.rightTile = rightTile;}


    // getters
    public int getPosx() { return posx; }
    public int getPosy() { return posy; }
    public Tile getBotTile() {return botTile;}
    public Tile getLeftTile() {return leftTile;}
    public Tile getRightTile() {return rightTile;}
    public Tile getTopTile() {return topTile;}
}