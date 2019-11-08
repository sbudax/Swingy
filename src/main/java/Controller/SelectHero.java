package Controller;

import Model.Character.Hero;
import Model.Game;
import Utils.DataBase;
import View.Interface.SelectHeroView;
import Exception.HeroValidationException;

import java.util.ArrayList;

public class SelectHero {
    private SelectHeroView view;
    private Game game;

    public SelectHero(SelectHeroView view) {
        this.view = view;
        game = Game.getInstance();
    }

    public void SelectedHero(int idx) {
        Hero hero = DataBase.selectHeroById(idx + 1);
        view.updateInfo(hero.toString());
    }

    public String[] getListData() {
        ArrayList<String> list = DataBase.selectAll();
        String[] listArr = new String[list.size()];
        listArr = list.toArray(listArr);
        return listArr;
    }

    public void onSelect(int idx) {
        Hero hero;
        try {
            hero = DataBase.selectHeroById(idx + 1);
            hero.validateHero();
        } catch (HeroValidationException e) {
            view.showErrorMessage(e.getMessage());
            return;
        }

        game.initGame(hero);
        view.openGame();
    }

    public void onCreate() {
        view.openCreateHero();
    }
}


