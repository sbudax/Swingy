package Controller;

import Model.Artifacts.Armor;
import Model.Artifacts.Artifacts;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;
import Model.Character.Hero;
import Model.Character.Villain;
import Model.Game;
import Utils.DataBase;
import Utils.Movement;
import View.Interface.GameView;

import java.util.Random;

public class GamePlay {
    private GameView view;
    private Game game;
    private Movement previousPosition;

    public GamePlay(GameView view) {
        this.view = view;
        game = Game.getInstance();
        previousPosition = new Movement(0, 0);
    }

    public void onStart(){
        view.update(game);
    }

    public void onPrintMap(){
        view.printMap(game.getMap(), game.getHeroCoordinates());
        view.update(game);
    }

    public void onMove(String direction){
        int x = game.getHeroCoordinates().getX();
        int y = game.getHeroCoordinates().getY();
        previousPosition.setX(x);
        previousPosition.setY(y);

        if ("NORTH".equals(direction.toUpperCase())) {
            y--;
        } else if ("EAST".equals(direction.toUpperCase())) {
            x++;
        } else if ("SOUTH".equals(direction.toUpperCase())) {
            y++;
        } else if ("WEST".equals(direction.toUpperCase())) {
            x--;
        }
        if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
            win();
            return;
        }

        if (game.getMap()[y][x]) {
            villainEncounter();
        }

        if (game.getHero().getHitPoints() > 0)
            view.update(game);
    }

    private void win() {
        view.showMessage("You win! And got additional " + game.getMapSize() * 100 + "xp!");
        addExperience(game.getMapSize() * 100);
        updateDataBase();
        view.gameFinished();
    }

    private void updateDataBase() {
        Hero hero = game.getHero();
        DataBase.updateHero(hero);
    }

    private void villainEncounter() {
        view.getVillainEncounterInput();
    }

    public void onRun() {
        if (new Random().nextBoolean()) {
            view.showMessage("Villian avoided!");
            game.getHeroCoordinates().setX(previousPosition.getX());
            game.getHeroCoordinates().setY(previousPosition.getY());
        } else {
            view.showMessage("Villian encountered you have to fight!");
            onFight();
        }
    }

    private void setArtifact(Artifacts artifact) {
        if (artifact != null) {
            if (artifact instanceof Weapon) {
                if (game.getHero().getWeapon() == null || view.replaceArtifact("your weapon: " + game.getHero().getWeapon() + ", found: " + artifact)) {
                    game.getHero().equipWeapon((Weapon) artifact);
                    view.showMessage("You equipped new weapon: " + artifact);
                }
            } else if (artifact instanceof Helm) {
                if (game.getHero().getHelm() == null || view.replaceArtifact("your helmet: " + game.getHero().getHelm() + ", found: " + artifact)) {
                    game.getHero().equipHelm((Helm) artifact);
                    view.showMessage("You equipped new helm: " + artifact);
                }
            } else if (artifact instanceof Armor) {
                if (game.getHero().getArmor() == null || view.replaceArtifact("your armor: " + game.getHero().getArmor() + ", found: " + artifact)) {
                    game.getHero().equipArmor((Armor) artifact);
                    view.showMessage("You equipped new armor: " + artifact);
                }
            }
        }
    }

    public void onFight() {
        Villain villain = game.generateVillain();
        int xp = game.fightResult(villain);

        if (xp >= 0) {
            view.showMessage("You win, and got " + xp + "xp.");
            addExperience(xp);
            game.getMap()[game.getHeroCoordinates().getY()][game.getHeroCoordinates().getX()] = false;
            setArtifact(villain.getArtifacts());
        } else {
            view.showMessage("Model.Game over :(");
            view.gameFinished();
        }
    }

    private void addExperience(int addXP) {
        int level = game.getHero().getLevel();
        game.getHero().addExperience(addXP);
        if (level != game.getHero().getLevel())
            view.showMessage("Level UP!\nHP, attack and defence were increased!");
    }

    public void onSwitchButtonPressed() {
        view.switchView();
    }
}

