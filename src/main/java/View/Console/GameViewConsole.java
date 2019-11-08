package View.Console;

import Controller.GamePlay;
import Main.Main;
import Model.Game;
import Utils.Movement;
import View.GUI.GameViewGui;
import View.Interface.GameView;

import java.util.Scanner;

public class GameViewConsole implements GameView {

    private GamePlay controller;

    @Override
    public void start(){
        controller = new GamePlay(this);
        controller.onStart();
    }

    @Override
    public void update(Game game){
        System.out.println("*-*-*-*-*-GAME-INFO-*-*-*-*-*");
        System.out.println(game.getHero().toString() +
                "Position: " + "(" + game.getHeroCoordinates().getX() +
                "," + game.getHeroCoordinates().getY() + ")");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        getUserInput();
    }

    private void getUserInput(){
        Scanner scanner = Main.getScanner();

        System.out.println("List of commands: ");
        System.out.println("Movement - East, West, South, North.");
        System.out.println("(Switch) to change to gui view.");
        System.out.println("(MAP) to show map while playing.");
        while(scanner.hasNext()){
            String input = scanner.nextLine();

            if("map".equalsIgnoreCase(input)){
                controller.onPrintMap();
                break;
            } else if ("north".equalsIgnoreCase(input) ||
                    "east".equalsIgnoreCase(input) ||
                    "south".equalsIgnoreCase(input) ||
                    "west".equalsIgnoreCase(input)) {
                controller.onMove(input);
                break;
            } else if("switch".equalsIgnoreCase(input)){
                controller.onSwitchButtonPressed();
                break;
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    @Override
    public void printMap(boolean[][] map, Movement heroCoordinates){
        System.out.printf("MAP %dx%d", map.length, map.length);
        System.out.println();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if (heroCoordinates.getX() == j && heroCoordinates.getY() == i)
                    System.out.print("H ");
                else if (map[i][j])
                    System.out.print("E ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    @Override
    public void gameFinished(){
        System.out.println("Model.Game Over!");
        Main.getFrame().dispose();
        Main.closeConnections();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }


    @Override
    public void getVillainEncounterInput(){
        Scanner scanner = Main.getScanner();

        System.out.println();
        System.out.println("Villain Encountered!");
        System.out.println("Enter Command:   Fight | Run .");
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if("fight".equalsIgnoreCase(input)){
                controller.onFight();
                break;
            } else if("Run".equalsIgnoreCase(input)){
                controller.onRun();
                break;
            } else {
                System.out.println("Invalid Command");
            }
        }
    }
    @Override
    public boolean replaceArtifact(String replaceMessage){
        Scanner scanner = Main.getScanner();

        System.out.println();
        System.out.println("Would you like to change " + replaceMessage + "?");
        System.out.println("Enter Command:  Yes | No");
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if("No".equalsIgnoreCase(input)){
                return false;
            } else if ("Yes".equalsIgnoreCase(input)){
                return true;
            } else{
                System.out.println("Invalid Command");
            }
        }
        return false;
    }
    @Override
    public void switchView(){
        new GameViewGui().start();
    }
}
