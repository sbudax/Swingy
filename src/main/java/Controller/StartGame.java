package Controller;

import View.Interface.StartView;

public class StartGame {
    private StartView view;

    public StartGame(StartView view){
        this.view = view;
    }

    public void onCreateHero(){
        view.openCreateHero();
    }

    public void onSwitch(){
        view.switchView();
    }

    public void onSelectHero(){
        view.openSelectHero();
    }

    public void onExit(){
        System.exit(0);
    }
}
