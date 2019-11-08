package Model.Character;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character{

    @NotNull(message = "Name Cannot Be Null")
    @Size(min = 2, max = 16, message = "Name Should Be Between 2 and 16 Characters Long")
    protected String name;

    @Min(value = 0, message = "Attack Cannot Be Less Than 0")
    protected int attack;

    @Min(value = 0, message = "Defence Cannot Be Less Than 0")
    protected int defence;

    @Min(value = 1, message = "Hit Cannot Be Less Than 1")
    protected int hitPoints;

    public Character(String name, int attack, int defence, int hitPoints){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
    }

    private void attack(Character opponent){
        if (this.attack > opponent.defence){
            opponent.setHitPoints(opponent.getHitPoints() - (this.attack - opponent.defence));
        } else if(ThreadLocalRandom.current().nextInt(0,10) <= 2){
            opponent.setHitPoints(opponent.getHitPoints() - this.attack);
        }
    }

    public boolean fight(Character opponent){
        while(opponent.getHitPoints() > 0 && this.getHitPoints() > 0){
            this.attack(opponent);
            opponent.attack(this);
        }
        return this.getHitPoints() > 0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefense(int defense){
        this.defence = defence;
    }

    public void setHitPoints(int hitPoints){
        this.defence = defence;
    }

    public String getName(){
        return name;
    }


    public int getAttack(){
        return attack;
    }

    public int getDefence(){
        return defence;
    }


    public int getHitPoints(){
        return hitPoints;
    }


}
