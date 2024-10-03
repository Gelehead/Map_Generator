// Tile[xCoordinate, yCoordinate, currentState, futureState]
public class Tile {
    private int posx; 
    private int posy;
    // state is supposed to contain an ennemy / terrain / traps / nothing(null)(which means that it is walkable)
    TileState state, nextState;

    // neigbours ( currently being reworked )
    Tile topTile, botTile, rightTile, leftTile;
    Tile[] neighbours = new Tile[4];
    

    public Tile(int posx , int posy, TileState state, TileState nextState){
        this.posx = posx;
        this.posy = posy;
        this.state = state;
        this.nextState = nextState;
    }

    public void setState(TileState newState){ this.state = newState; }
    public void setNextState(TileState newNextState){ this.nextState = newNextState; };

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
    public TileState getState() {return state;}
    public Tile getBotTile() {return botTile;}
    public Tile getLeftTile() {return leftTile;}
    public Tile getRightTile() {return rightTile;}
    public Tile getTopTile() {return topTile;}
}