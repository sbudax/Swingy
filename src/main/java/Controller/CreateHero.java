package Controller;

import Exception.HeroValidationException;
import Model.Character.Hero;
import Model.Character.HeroFactory;
import Model.Game;
import Utils.DataBase;
import View.Interface.CreateHeroView;

public class CreateHero{
    private CreateHeroView view;
    private Game game;

    public CreateHero(CreateHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void onCreate(String name, String heroClass) {
        Hero hero;

        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | HeroValidationException e) {
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
            return;
        }

        int id = DataBase.insert(hero.getName(), hero.getHeroClass(), hero.getLevel(), hero.getExperience(), hero.getAttack(), hero.getDefence(), hero.getHitPoints());
        hero.setId(id);
        game.initGame(hero);
        view.openGame();
    }
}
