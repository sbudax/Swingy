package View.Console;

import Controller.CreateHero;
import Main.Main;
import View.GUI.NewHeroGui;
import View.Interface.CreateHeroView;

import java.util.Scanner;

public class NewHeroView extends NewHeroGui implements CreateHeroView{

    private CreateHero controller;

    @Override
    public void start(){
        controller = new CreateHero(this);
        getUserInput();
    }

    @Override
    public void getUserInput(){
        Scanner scanner = Main.getScanner();

        System.out.println("Enter hero name and class to create a new hero.");
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();
        System.out.println("Classes: attack  Defence   hp\n" +
                "Giant    40    10    100\n" +
                "Human    25    30    90\n" +
                "Elf      35    20    90\n" +
                "Fairy    15    35    60\n" +
                "Dwarf    20    20    80\n" +
                "Dragon   50    50    120\n" +
                "Enter Class Name: ");
        String heroClass = scanner.nextLine();

        System.out.println("Confirm - to create hero");
        System.out.println("Command (Yes):");
        while(scanner.hasNext()){
            String input = scanner.nextLine();

            if("Yes".equalsIgnoreCase(input)) {
                controller.onCreate(name, heroClass);
                break;
            }else {
                System.out.println("Invalid Command");
            }
        }
    }

    @Override
    public void showErrorMessage(String message){
        System.out.println("Error: " + message);
    }

    @Override
    public void openGame(){
        new GameViewConsole().start();
    }
}
