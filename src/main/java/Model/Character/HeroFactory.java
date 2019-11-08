package Model.Character;

public abstract class HeroFactory {

    public static Hero newHero(String name, String heroClass) {
        switch (heroClass.toUpperCase()) {
            case "GIANT":
                return Director.createGiant(name);
            case "HUMAN":
                return Director.createHuman(name);
            case "ELF":
                return Director.createElf(name);
            case "FAIRY":
                return Director.createFairy(name);
            case "DWARF":
                return Director.createDwarf(name);
            case "DRAGON":
                return Director.createDragon(name);
            default:
                throw new IllegalArgumentException("Invalid hero class: " + heroClass);
        }
    }
}
