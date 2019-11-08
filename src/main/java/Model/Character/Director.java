package Model.Character;

public class Director{

    private static HeroBuilder buildNew(String name){
        HeroBuilder builder = new HeroBuilder();
        builder.setName(name);
        builder.setLevel(0);
        builder.setExperience(0);
        return builder;
    }

    public static Hero createGiant(String name){
        HeroBuilder builder = buildNew(name);
        builder.setAttack(40);
        builder.setDefence(10);
        builder.setHitPoints(100);
        builder.setHeroClass("Giant");
        return builder.getHero();
    }

    public static Hero createHuman(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(25);
        builder.setDefence(30);
        builder.setHitPoints(90);
        builder.setHeroClass("Human");
        return builder.getHero();
    }

    public static Hero createElf(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(35);
        builder.setDefence(20);
        builder.setHitPoints(90);
        builder.setHeroClass("Elf");
        return builder.getHero();
    }

    public static Hero createFairy(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(15);
        builder.setDefence(35);
        builder.setHitPoints(60);
        builder.setHeroClass("Fairy");
        return builder.getHero();
    }

    public static Hero createDwarf(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(20);
        builder.setDefence(20);
        builder.setHitPoints(80);
        builder.setHeroClass("Dwarf");
        return builder.getHero();
    }

    public static Hero createDragon(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(50);
        builder.setDefence(50);
        builder.setHitPoints(120);
        builder.setHeroClass("Dragon");
        return builder.getHero();
    }

}
