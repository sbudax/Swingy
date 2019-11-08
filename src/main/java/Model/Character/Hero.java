package Model.Character;

import Exception.*;
import Model.Artifacts.Armor;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.logging.Level;

public class Hero extends Character{

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    @Min(value = 0, message = "Level Cannot Be Less Than 0")
    private int level;

    @Min(value = 0, message = "Experience Cannot Be Less Than 0")
    private int experience;

    @NotNull(message = "Hero  Cannot Be Null")
    private String heroClass;

    private int id;

    public Hero(String name, int attack, int defence, int hitPoints, int id, String heroClass, int level, int experience, Weapon weapon, Armor armor, Helm helm){
        super(name, attack, defence, hitPoints);
        this.id = id;
        this.weapon = weapon;
        this.armor = armor;
        this.helm = helm;
        this.level = level;
        this.experience = experience;
        this.heroClass = heroClass;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void validateHero() throws HeroValidationException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(this);
        if (constraintViolations.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Hero validation error(s): ");
            stringBuilder.append(constraintViolations.size());
            stringBuilder.append("\n");
            for (ConstraintViolation<Hero> cv : constraintViolations) {
                stringBuilder.append("property: [");
                stringBuilder.append(cv.getPropertyPath());
                stringBuilder.append("], value: [");
                stringBuilder.append(cv.getInvalidValue());
                stringBuilder.append("], message: [");
                stringBuilder.append(cv.getMessage());
                stringBuilder.append("]\n");
            }
            throw new HeroValidationException(stringBuilder.toString());
        }
    }

    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.attack -= this.weapon.getPoints();
        }
        this.attack += weapon.getPoints();
        this.weapon = weapon;
    }

    public void equipHelm(Helm helm) {
        if (this.helm != null) {
            this.hitPoints -= this.helm.getPoints();
            if (this.hitPoints + helm.getPoints() <= 0) {
                this.hitPoints += this.helm.getPoints();
                return;
            }
        }
        this.hitPoints += helm.getPoints();
        this.helm = helm;
    }

    public void equipArmor(Armor armor) {
        if (this.armor != null) {
            this.defence -= this.armor.getPoints();
        }
        this.defence += armor.getPoints();
        this.armor = armor;
    }

    public void addExperience(int addXP) {
        int nextLevel = (level + 1) * 1000 + level * level * 450;

        if (experience + addXP >= nextLevel)
            levelUp();
        experience += addXP;
    }

    private void levelUp() {
        level++;
        hitPoints += 50 + level * 10;
        attack += level * 3;
        defence += level * 2;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void setArmor(Armor armor){
        this.armor = armor;
    }

    public void setHelm(Helm helm){
        this.helm = helm;
    }

    public void setExperience(int experience){
        this.experience = experience;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setHeroClass(String heroClass){
        this.heroClass = heroClass;
    }

    public Armor getArmor(){
        return armor;
    }

    public Helm getHelm(){
        return helm;
    }

    public int getLevel(){
        return level;
    }


    public int getExperience(){
        return experience;
    }


    public String getHeroClass(){
        return heroClass;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(name).append("\n");
        stringBuilder.append("Class: ").append(heroClass).append("\n");
        stringBuilder.append("Level: ").append(level).append("\n");
        stringBuilder.append("XP: ").append(experience).append("\n");
        stringBuilder.append("Attack: ").append(attack).append("\n");
        stringBuilder.append("Defence: ").append(defence).append("\n");
        stringBuilder.append("HP: ").append(hitPoints).append("\n");

        stringBuilder.append("Weapon: ");
        if(weapon != null)
            stringBuilder.append(weapon.getName()).append(" (attack +").append(weapon.getPoints()).append(")\n");
        else
            stringBuilder.append(" No Weapon\n");

        stringBuilder.append("Helm: ");
        if(weapon != null)
            stringBuilder.append(helm.getName()).append(" (hp +").append(helm.getPoints()).append(")\n");
        else
            stringBuilder.append(" No Helm\n");

        stringBuilder.append("Armor: ");
        if(weapon != null)
            stringBuilder.append(armor.getName()).append(" (defence +").append(armor.getPoints()).append(")\n");
        else
            stringBuilder.append(" No Armor\n");
        return stringBuilder.toString();
    }
}
