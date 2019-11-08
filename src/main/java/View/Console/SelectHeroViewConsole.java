package View.Console;


import Controller.SelectHero;
import View.Interface.SelectHeroView;
import Main.*;

import java.util.Scanner;

public class SelectHeroViewConsole implements SelectHeroView{
    private SelectHero controller;
    private int lastIdx = -1;

    @Override
    public void start(){
        controller = new SelectHero(this);
        getInput();
    }

    private void getInput(){
        Scanner scanner = Main.getScanner();
        System.out.println("Available Heroes: ");
        printHeroes(controller.getListData());

        System.out.println();
        System.out.println("Enter Commands: Number | Select");
        while(scanner.hasNext()){
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)) {
                controller.onCreate();
                break;
            } if (isValidNumericString(input, controller.getListData().length)) {
                lastIdx = Integer.parseInt(input) - 1;
                controller.SelectedHero(lastIdx);
            } else if ("select".equalsIgnoreCase(input) && lastIdx != -1) {
                controller.onSelect(lastIdx);
                break;
            } else {
                System.out.println("Invalid command");
            }
        }
    }
    private boolean isValidNumericString(String str, int max) {
        try {
            int n = Integer.parseInt(str);
            if (n <= 0 || n > max)
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void printHeroes(String[] heroes) {
        if (heroes.length == 0)
            System.out.println("No saved heroes");
        for (String hero : heroes) {
            System.out.println(hero);
        }
    }

    @Override
    public void updateInfo(String info) {
        System.out.println(info);
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
        getInput();
    }

    @Override
    public void openGame() {
        new GameViewConsole().start();
    }

    @Override
    public void openCreateHero() {
        new NewHeroView().start();
    }
}
