// NPC stands for non playable Character
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