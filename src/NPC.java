// NPC stands for non playable Character

// | To Do | - add a move queue (current, next turn, 2nd next turn)
// Would be dynamic --> current will obviouslsy not change, neither will next turn
// 2nd next turn will be updated every start/end? of turn
// 

// maybe should be abstract
public class NPC {
    private boolean spannable;
     int hp, power;

    public NPC(int HP, int power){
        this.hp = HP;
        this.power = power;
    }

    public boolean canBeCrossed(){
        return this.spannable;
    }
}