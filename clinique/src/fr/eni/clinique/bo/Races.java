package src.fr.eni.clinique.bo;

public class Races{
    private String Race;
    private String Espece;

    public Races(String race, String espece) {
        Race = race;
        Espece = espece;
    }

    public Races() {
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getEspece() {
        return Espece;
    }

    public void setEspece(String espece) {
        Espece = espece;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Races{" +
                "Race=" + Race +
                ", Espece=" + Espece +
                '}';
    }
}