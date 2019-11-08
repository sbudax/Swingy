package Model.Artifacts;

public abstract class Artifacts{

    private int points;
    protected String name;

    public Artifacts(String name, int points){

        this.name = name;
        this.points = points;
    }

    public int getPoints(){
        return points;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return  name + "(+" + points + ")";
    }
}
