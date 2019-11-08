package Model.Character;

import Model.Artifacts.Artifacts;

public class Villain extends Character{

    private Artifacts artifacts;

    public Villain(String name, int attack, int defence, int hitPoints, Artifacts artifacts){
        super(name, attack, defence, hitPoints);
        this.artifacts = artifacts;
    }

    public Artifacts getArtifacts(){
        return artifacts;
    }
}
