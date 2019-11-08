package Model;

import Model.Artifacts.Armor;
import Model.Artifacts.Artifacts;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;
import Model.Character.Hero;
import Model.Character.Character;
import Model.Character.Villain;
import Utils.Movement;

import java.util.concurrent.ThreadLocalRandom;

public class Game{
    private static Game instance = null;

    private Hero hero;
    private Movement heroCoordinates;
    private int mapSize;
    private boolean[][] map;

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void initGame(Hero hero) {
        this.hero = hero;
        generateMap();
        generateVillains();
        putHero();
    }

    private void generateMap() {
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];
    }

    private void generateVillains() {
        int rand;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                rand = ThreadLocalRandom.current().nextInt(0, 101);
                if ((level + 1) * 10 >= rand)
                    map[i][j] = true;
            }
        }
    }

    public Villain generateVillain() {
        int attack = ThreadLocalRandom.current().nextInt(hero.getAttack() - 20, hero.getAttack() + 2 + hero.getLevel());
        int defence = ThreadLocalRandom.current().nextInt(hero.getDefence() - 20, hero.getDefence() + 2 + hero.getLevel());
        int hitPoints = ThreadLocalRandom.current().nextInt(hero.getHitPoints() - 50, hero.getHitPoints() + 20 + hero.getLevel());

        attack = attack < 0 ? -attack : attack;
        defence = defence < 0 ? -defence : defence;
        hitPoints = hitPoints < 0 ? -hitPoints : hitPoints;
        Artifacts artifact = generateArtifact();

        return new Villain("Villain", attack, defence, hitPoints, artifact);
    }

    private Artifacts generateArtifact() {
        int rand = ThreadLocalRandom.current().nextInt(0, 10);

        Artifacts artifact = null;
        if (rand == 0)
            artifact = new Weapon("Sword", ThreadLocalRandom.current().nextInt(1, 5 * (hero.getLevel() + 1)));
        else if (rand == 1)
            artifact = new Helm("Pot", ThreadLocalRandom.current().nextInt(1, 3 * (hero.getLevel() + 1)));
        else if (rand == 2)
            artifact = new Armor("Shield", ThreadLocalRandom.current().nextInt(1, 4 * (hero.getLevel() + 1)));
        return artifact;
    }

    public int fightResult(Character villain) {
        int xp = villain.getAttack() + villain.getDefence() + villain.getHitPoints();
        int rand = ThreadLocalRandom.current().nextInt(0, 101);

        if (rand < 3)
            return xp;
        else if (rand > 98)
            return -1;

        return hero.fight(villain) ? xp : -1;
    }

    private void putHero() {
        heroCoordinates = new Movement(mapSize / 2, mapSize / 2);
        map[heroCoordinates.getY()][heroCoordinates.getX()] = false;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Movement getHeroCoordinates() {
        return heroCoordinates;
    }

    public void setHeroCoordinates(Movement heroCoordinates) {
        this.heroCoordinates = heroCoordinates;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }
}
