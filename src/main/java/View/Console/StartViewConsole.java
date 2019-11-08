package View.Console;

import Controller.StartGame;
import Main.Main;
import View.GUI.StartViewGui;
import View.Interface.StartView;

import java.util.Scanner;

public class StartViewConsole implements StartView{
    private StartGame controller;

    @Override
    public void start() {
        controller = new StartGame(this);
        System.out.println("You are in console RPG game, enter available commands to play.");

        Scanner scanner = Main.getScanner();
        System.out.println("List of commands: ");
        System.out.println("(Create) to create a new hero");
        System.out.println("Movement - East, West, South, North.");
        System.out.println("(Switch) to change to gui view.");
        System.out.println("(MAP) to show map while playing.");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)) {
                controller.onCreateHero();
                break;
            } else if ("select".equalsIgnoreCase(input)) {
                controller.onSelectHero();
                break;
            } else if ("switch".equalsIgnoreCase(input)) {
                controller.onSwitch();
                break;
            } else if ("exit".equalsIgnoreCase(input)){
                controller.onExit();
                break;
            }
            else {
                System.out.println("Unknown command");
            }
        }
    }

    @Override
    public void openCreateHero() {
        new NewHeroView().start();
    }

    @Override
    public void switchView() {
        new StartViewGui().start();
    }

    @Override
    public void openSelectHero() {
        new SelectHeroViewConsole().start();
    }
}
