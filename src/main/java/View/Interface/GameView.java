package View.Interface;

import Model.Game;
import Utils.Movement;

public interface GameView{
    void start();

    void printMap(boolean[][] map, Movement heroCoordinates);

    void update(Game game);

    void gameFinished();

    void showMessage(String message);

    void getVillainEncounterInput();

    boolean replaceArtifact(String replaceMessage);

    void switchView();

}
