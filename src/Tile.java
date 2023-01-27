// Tile[xCoordinate, yCoordinate, currentState, futureState]
public class Tile {
    int posx; 
    int posy;
    // state is supposed to contain an ennemy / terrain / traps / nothing(null)
    Object state;
    Object nextState;

    public Tile(int posx , int posy, Object state, Object nextState){
        this.posx = posx;
        this.posy = posy;
        this.state = state;
        this.nextState = nextState;
    }

    public void setState(Object newState){
        this.state = newState;
    }

    public void setNextState(Object newNextState){
        this.nextState = newNextState;
    }

    //ajouter .toString() a this.state et this.nextState
    @Override
    public String toString() {
        return "[" + this.posx + ", " + this.posy + ", " 
        + this.state + ", " + this.nextState + "]" ;
    }

    public String repr(){
        return this.state != null ? this.state.toString() : " ";
    }
}